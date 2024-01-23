package odwsi.bank.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import org.springframework.data.annotation.CreatedDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Data
@Table(name = "Transfers")
@NotNull(message = "The transfer must not be null.")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transfer_id")
    private int id;

    @NotNull
    private String title;

    @NotNull(message = "Sum must not be null.")
    private BigDecimal sum;

    @NotNull(message = "Account number must not be null.")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Account fromAccount;

    @NotNull(message = "Account number must not be null.")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Account toAccount;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return id == transfer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
