package odwsi.bank.security;

import odwsi.bank.dtos.PasswordDTO;
import odwsi.bank.models.Client;
import odwsi.bank.models.Password;
import odwsi.bank.models.PasswordLoginAttempt;
import odwsi.bank.repositories.ClientRepository;
import odwsi.bank.repositories.PasswordLoginAttemptRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PasswordService {

    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final PasswordLoginAttemptRepository passwordLoginAttemptRepository;

    public PasswordService(PasswordEncoder passwordEncoder, ClientRepository clientRepository, PasswordLoginAttemptRepository passwordLoginAttemptRepository) {
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
        this.passwordLoginAttemptRepository = passwordLoginAttemptRepository;
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
        if (str.length() < 10) {
            throw new IllegalArgumentException("String must be at least 10 characters long.");
        }

        List<String> combinations = new ArrayList<>();
        List<Password> passwords = new ArrayList<>();
        int maxCombinations = 20;

        while(maxCombinations>0) {
            List<Integer> indices = pickThreeRandomNumbers(str);
            Collections.sort(indices);
            String positions = "";
            String combination="";
            for (int j = 0; j < 3; j++){
                combination+=str.charAt(indices.get(j));
                positions+=indices.get(j).toString()+"/";
            }
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
        int random = new Random().nextInt(19);
        Password password = passwords.get(random);

        var attempt = passwordLoginAttemptRepository.save(PasswordLoginAttempt.builder().passwordId(password.getId()).build());

        PasswordDTO pp = PasswordDTO.builder()
                .uuid(attempt.getUuid())
                .positions(password.getPositions()).build();
        return pp;
    }


    public Client getUserIfPasswordCorrect(int id, String email, String password) {
        Client client = clientRepository.findByEmail(email);
        var passwordOpt = client.getPasswords().stream().filter(p-> p.getId() == id).findFirst();
        String pp = passwordOpt.get().getPassword();
        if(passwordOpt.isEmpty()) return null;
        if(passwordEncoder.matches(password,pp)){
            return client;
        }
        return null;
    }
}
