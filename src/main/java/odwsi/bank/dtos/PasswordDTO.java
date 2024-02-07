package odwsi.bank.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PasswordDTO {
    private UUID uuid;
    private String positions;
}