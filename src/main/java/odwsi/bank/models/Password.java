package odwsi.bank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="password_id")
    private int id;

    private String password;

    private String positions;

    @ManyToOne
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
