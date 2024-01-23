package odwsi.bank.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Password {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private String positions;

    @Setter
    @ManyToOne()
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password pass = (Password) o;
        return id == pass.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
