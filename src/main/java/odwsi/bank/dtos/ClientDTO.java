package odwsi.bank.dtos;

import lombok.*;
import odwsi.bank.models.Client;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ClientDTO {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phoneNum;
    private int account_id;

    public static ClientDTO mapToDto(Client client) {
        if (client == null) return null;

        ClientDTO dto = new ClientDTO();
        dto.id = client.getId();
        dto.name = client.getName();
        dto.surname = client.getSurname();
        dto.email = client.getEmail();
        dto.phoneNum = client.getPhoneNum();
        dto.account_id = client.getAccount().getId();

        return dto;
    }
}
