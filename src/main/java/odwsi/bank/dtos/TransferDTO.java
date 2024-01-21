package odwsi.bank.dtos;

import lombok.*;
import odwsi.bank.models.Transfer;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class TransferDTO {
    private int id;
    private String title;
    private BigDecimal sum;
    private int from_id;
    private int to_id;

    private ZonedDateTime time;

    public static TransferDTO mapToDto(Transfer transfer) {
        if (transfer == null) return null;

        TransferDTO dto = new TransferDTO();
        dto.id = transfer.getId();
        dto.title = transfer.getTitle();
        dto.sum = transfer.getSum();
        dto.from_id = transfer.getFromAccount().getId();
        dto.to_id = transfer.getToAccount().getId();

        return dto;
    }
}
