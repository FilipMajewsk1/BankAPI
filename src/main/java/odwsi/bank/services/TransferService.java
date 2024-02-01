package odwsi.bank.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import odwsi.bank.dtos.TransferDTO;
import odwsi.bank.models.Account;
import odwsi.bank.models.Transfer;
import odwsi.bank.repositories.AccountRepository;
import odwsi.bank.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TransferService {
    private final TransferRepository repository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final ClientService clientService;
    private final Validator validator;

    @Autowired
    public TransferService(TransferRepository repository,ClientService clientService ,AccountService accountService, Validator validator, AccountRepository accountRepository) {
        this.repository = repository;
        this.accountService = accountService;
        this.clientService = clientService;
        this.validator = validator;
        this.accountRepository = accountRepository;
    }

    public Transfer getTransfer(int id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<Transfer> getClientsTransfers(String email) {
        Iterable<Transfer> allTransfers = repository.findAll();
        List<Transfer> selectedTransfers = new ArrayList<>();
        String number = clientService.getClient(email).getAccount().getAccountNumber();

        for(Transfer transfer : allTransfers){
            if(transfer.getFromAccount().getAccountNumber() == number ||
                    transfer.getToAccount().getAccountNumber() == number){
                selectedTransfers.add(transfer);
            }
        }
        return selectedTransfers;
    }

    public Transfer createTransfer(Transfer transfer) {
        Set<ConstraintViolation<Transfer>> violations = validator.validate(transfer);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Account fromAccount = accountRepository.findByAccountNumber(transfer.getFromAccount().getAccountNumber());
        Account toAccount = accountRepository.findByAccountNumber(transfer.getToAccount().getAccountNumber());

        fromAccount.setBalance(fromAccount.getBalance().add(transfer.getSum().negate()));
        toAccount.setBalance(toAccount.getBalance().add(transfer.getSum()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return repository.save(transfer);
    }

    public Transfer mapFromDto(TransferDTO dto) {
        return dto != null ? new Transfer(
                -1,
                dto.getTitle(),
                dto.getSum(),
                accountService.getAccount(accountRepository.findByAccountNumber(dto.getFromAccountNumber()).getId()),
                accountService.getAccount(accountRepository.findByAccountNumber(dto.getToAccountNumber()).getId())
        ) : null;
    }
}

