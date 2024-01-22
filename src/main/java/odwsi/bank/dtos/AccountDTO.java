package odwsi.bank.dtos;

import lombok.*;
import odwsi.bank.models.Account;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class AccountDTO {
    private int id;
    private String accountNumber;
    private BigDecimal balance;

    public static  AccountDTO mapToDto(Account account){
        if (account == null) return null;

        AccountDTO dto = new AccountDTO();
        dto.id = account.getId();
        dto.accountNumber = account.getAccountNumber();
        dto.balance = account.getBalance();

        return dto;
    }
}
