package odwsi.bank.dtos;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class MakeTranfer {
    private String title;
    private String sum;
    private String toAccountNumber;
}
