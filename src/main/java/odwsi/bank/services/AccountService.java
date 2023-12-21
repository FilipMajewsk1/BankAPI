package odwsi.bank.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import odwsi.bank.dtos.AccountDTO;
import odwsi.bank.models.Account;
import odwsi.bank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AccountService {
    private final AccountRepository repository;
    private final Validator validator;

    @Autowired
    public AccountService(AccountRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Account getAccount(int id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<Account> getAllAccounts() {
        return repository.findAll();
    }

    public Account createAccount(Account account) {
        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(account);
    }

    public void deleteAccount(int id) {
        repository.deleteById(id);
    }

    public Account updateAccount(int id, Account account) {
        Account accountToUpdate = repository.findById(id).orElse(null);

        if (accountToUpdate == null) {
            throw new IllegalArgumentException(String.format("The account with ID %d was not found - failed to update.", id));
        }

        accountToUpdate.setAccountNumber(account.getAccountNumber());
        accountToUpdate.setBalance(account.getBalance());

        Set<ConstraintViolation<Account>> violations = validator.validate(accountToUpdate);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(accountToUpdate);
    }

    public Account mapFromDto(AccountDTO dto) {
        return dto != null ? new Account(
                dto.getId(),
                dto.getAccountNumber(),
                dto.getBalance()
        ) : null;
    }
}