package odwsi.bank.security;

import odwsi.bank.dtos.PasswordDTO;
import odwsi.bank.models.Client;
import odwsi.bank.models.Password;
import odwsi.bank.repositories.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PasswordService {

    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;

    public PasswordService(PasswordEncoder passwordEncoder, ClientRepository clientRepository) {
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
    }

    private static List<Integer> pickThreeRandomNumbers(String str) {
        if (str.length() < 3) {
            throw new IllegalArgumentException("String must be at least 3 characters long.");
        }

        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();

        while (numbers.size() < 3) {
            int randomNumber = random.nextInt(str.length());
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }

        return numbers;
    }

    public List<Password> createThreeCharCombinations(String str) {
        if (str.length() < 8) {
            throw new IllegalArgumentException("String must be at least 8 characters long.");
        }

        List<String> combinations = new ArrayList<>();
        List<Password> passwords = new ArrayList<>();
        int maxCombinations = 20;

        while(maxCombinations>0) {
            List<Integer> indices = pickThreeRandomNumbers(str);
            Collections.sort(indices);
            String positions = "";
            for (int j = 0; j < 3; j++){
                positions+=indices.get(j).toString()+"/";
            }
            String combination = indices.stream()
                    .map(str::charAt)
                    .map(Object::toString)
                    .collect(Collectors.joining());

            if (!combinations.contains(combination)) {
                combinations.add(combination);
                Password password = new Password();
                password.setPassword(passwordEncoder.encode(combination));
                password.setPositions(positions);

                passwords.add(password);
                maxCombinations--;
            }

        }

        return passwords;
    }

    public PasswordDTO getOneOfTheCombinations(Client client){
        List<Password> passwords = client.getPasswords();
        int random = new Random().nextInt(20);
        //Password p = passwords.get(random);
        PasswordDTO pp = PasswordDTO.builder().id(1).positions(String.valueOf(passwords.size())).build();

        return pp;
    }


    public Client getUserIfPasswordCorrect(int id, String email, String password) {
        var client = clientRepository.findByEmail(email);
        var passwordOpt = client.getPasswords().stream().filter(p-> p.getId() == id).findFirst();
        if(passwordOpt.isEmpty()) return null;
        if(passwordEncoder.encode(password).equals(passwordOpt.get().getPassword())){
            return client;
        }
        return null;
    }
}
