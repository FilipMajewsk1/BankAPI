package odwsi.bank.dtos;

import lombok.*;
import odwsi.bank.models.Transfer;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class TransferDTO {
    private int id;
    private String title;
    private BigDecimal sum;
    private String fromAccountNumber;
    private String toAccountNumber;

    public static TransferDTO mapToDto(Transfer transfer) {
        if (transfer == null) return null;

        TransferDTO dto = new TransferDTO();
        dto.id = transfer.getId();
        dto.title = transfer.getTitle();
        dto.sum = transfer.getSum();
        dto.fromAccountNumber = transfer.getFromAccount().getAccountNumber();
        dto.toAccountNumber = transfer.getToAccount().getAccountNumber();

        return dto;
    }
}
