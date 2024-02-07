package odwsi.bank.repositories;

import odwsi.bank.models.PasswordLoginAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PasswordLoginAttemptRepository extends CrudRepository<PasswordLoginAttempt, UUID> {

    Optional<PasswordLoginAttempt> findByUuidAndUsedIsFalse(UUID attemptUuid);
}
