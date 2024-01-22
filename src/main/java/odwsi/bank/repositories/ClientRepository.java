package odwsi.bank.repositories;

import odwsi.bank.models.Client;
import odwsi.bank.models.Password;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    Client findByEmail(String email);
}