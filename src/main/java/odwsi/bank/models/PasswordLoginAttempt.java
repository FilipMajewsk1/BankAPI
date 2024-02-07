package odwsi.bank.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordLoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull
    private int passwordId;

    private boolean used = false;
}
