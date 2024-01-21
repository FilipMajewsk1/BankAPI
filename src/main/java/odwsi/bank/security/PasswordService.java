package odwsi.bank.security;

import odwsi.bank.dtos.PasswordDTO;
import odwsi.bank.models.Client;
import odwsi.bank.models.Password;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public PasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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

        for (int i = 0; i < maxCombinations; i++) {
            List<Integer> indices = pickThreeRandomNumbers(str);
            String positions = "";
            for (int j = 0; j < 3; j++){
                positions+=indices.get(j).toString();
            }
            String combination = indices.stream()
                    .sorted()
                    .map(str::charAt)
                    .map(Object::toString)
                    .collect(Collectors.joining());


            if (!combinations.contains(combination)) {
                combinations.add(combination);
                Password password = new Password();
                password.setPassword(passwordEncoder.encode(combination));
                password.setPositions(positions);

                passwords.add(password);
            }

            if (combinations.size() == maxCombinations) {
                break;
            }
        }

        return passwords;
    }

    public PasswordDTO getOneOfTheCombinations(Client client){
        List<Password> passwords = client.getPasswords();
        int random = new Random().nextInt(20);
        Password p = passwords.get(random);
        PasswordDTO pp = PasswordDTO.builder().id(p.getId()).positions(p.getPositions()).build();
        return pp;
    }


}
