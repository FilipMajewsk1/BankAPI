package odwsi.bank.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class MakeTranfer {
    private String title;
    private String sum;
    private String fromAccountNumber;
    private String toAccountNumber;
}
