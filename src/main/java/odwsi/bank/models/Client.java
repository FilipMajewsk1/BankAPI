package odwsi.bank.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "Clients")
@NotNull(message = "The client must not be null.")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_id")
    private int id;

    @NotNull(message = "Name must not be null.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;

    @NotNull(message = "Surname must not be null.")
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters.")
    private String surname;

    @NotNull(message = "Email must not be null.")
    @Size(min = 7, max = 50, message = "Email must be between 7 and 50 characters.")
    @Email(message = "Email must be in a valid format.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "Phone number must not be null.")
    @Size(min = 9, max = 9, message = "Phone number must be 9 characters.")
    @Column(unique = true, nullable = false)
    private String phoneNum;

    @NotNull(message = "The account must not be null.")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}