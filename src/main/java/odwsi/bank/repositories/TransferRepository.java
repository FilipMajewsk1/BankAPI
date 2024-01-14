package odwsi.bank.repositories;

import odwsi.bank.models.Transfer;
import org.springframework.data.repository.CrudRepository;

public interface TransferRepository extends CrudRepository<Transfer, Integer> {
}
