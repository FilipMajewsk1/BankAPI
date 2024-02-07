package odwsi.bank.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class PasswordLoginRequest {
    private UUID uuid;
    private String email;
    private String password;
}
