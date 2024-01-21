package odwsi.bank.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordDTO {
    private int id;
    private String positions;
}