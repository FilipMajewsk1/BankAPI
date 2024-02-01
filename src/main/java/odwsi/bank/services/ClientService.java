package odwsi.bank.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import odwsi.bank.dtos.ClientDTO;
import odwsi.bank.models.Client;
import odwsi.bank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ClientService {
    private final ClientRepository repository;
    private final AccountService accountService;
    private final Validator validator;

    @Autowired
    public ClientService(ClientRepository repository, AccountService accountService, Validator validator) {
        this.repository = repository;
        this.accountService = accountService;
        this.validator = validator;
    }

    public Client getClient(String email) {
        if(repository.findByEmail(email).getPasswords().isEmpty()) {
            return null;
        }
        return repository.findByEmail(email);
    }

    public Client getClient(Authentication authentication) {
        String email = authentication.getPrincipal().toString();
        return repository.findByEmail(email);
    }

    public Client createClient(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);


        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return repository.save(client);
    }


    public Client mapFromDto(ClientDTO dto) {
        return dto != null ? Client.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .pesel(dto.getPesel())
                .phoneNum(dto.getPhoneNum())
                .account(accountService.getAccount(dto.getAccount_id()))
                .build()
         : null;
    }
}
