package odwsi.bank.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Clients")
@NotNull(message = "The client must not be null.")
public class Client implements UserDetails {
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
    private String email;

    @Getter
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Password> passwords;

    @NotNull
    @Size(min = 11, max = 11, message = "PESEL must be 11 characters.")
    private String pesel;

    @NotNull(message = "Phone number must not be null.")
    @Size(min = 9, max = 9, message = "Phone number must be 9 characters.")
    private String phoneNum;

    @NotNull(message = "The account must not be null.")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public String getPassword(){
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

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