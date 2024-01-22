package odwsi.bank.dtos;

import lombok.Data;

@Data
public class PasswordLoginRequest {
    private int id;
    private String email;
    private String password;
}
