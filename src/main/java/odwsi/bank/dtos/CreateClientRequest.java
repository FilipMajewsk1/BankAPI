package odwsi.bank.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateClientRequest {

    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    @NotNull
    private String pesel;
    @NotNull
    private String phoneNum;
    @NotNull
    private int account_id;

}
