package odwsi.bank.repositories;

import odwsi.bank.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    Client findByEmail(String email);
}