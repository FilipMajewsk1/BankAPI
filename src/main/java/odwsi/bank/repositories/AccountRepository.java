package odwsi.bank.repositories;

import odwsi.bank.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByAccountNumber(String accountNumber);
}
