package odwsi.bank.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import odwsi.bank.dtos.TransferDTO;
import odwsi.bank.models.Account;
import odwsi.bank.models.Client;
import odwsi.bank.models.Transfer;
import odwsi.bank.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class TransferService {
    private final TransferRepository repository;
    private final AccountService accountService;
    private final Validator validator;

    @Autowired
    public TransferService(TransferRepository repository, AccountService accountService, Validator validator) {
        this.repository = repository;
        this.accountService = accountService;
        this.validator = validator;
    }

    public Transfer getTransfer(int id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<Transfer> getAllTransfers() {
        return repository.findAll();
    }

    public Transfer createTransfer(Transfer transfer) {
        Set<ConstraintViolation<Transfer>> violations = validator.validate(transfer);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(transfer);
    }

    public void deleteTransfer(int id) {
        repository.deleteById(id);
    }

    public Transfer updateTransfer(int id, Transfer transfer) {
        Transfer transferToUpdate = repository.findById(id).orElse(null);

        if (transferToUpdate == null) {
            throw new IllegalArgumentException(String.format("The transfer with ID %d was not found - failed to update.", id));
        }

        transferToUpdate.setSum(transfer.getSum());
        transferToUpdate.setFromAccount(transfer.getFromAccount());
        transferToUpdate.setToAccount(transfer.getToAccount());

        Set<ConstraintViolation<Transfer>> violations = validator.validate(transferToUpdate);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(transferToUpdate);
    }

    public Transfer mapFromDto(TransferDTO dto) {
        return dto != null ? new Transfer(
                -1,
                dto.getSum(),
                accountService.getAccount(dto.getFrom_id()),
                accountService.getAccount(dto.getTo_id())
        ) : null;
    }
}

