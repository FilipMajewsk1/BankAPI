package odwsi.bank.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PasswordService {

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

    public static List<String> createThreeCharCombinations(String str) {
        if (str.length() < 8) {
            throw new IllegalArgumentException("String must be at least 8 characters long.");
        }

        List<String> combinations = new ArrayList<>();
        int maxCombinations = 20;

        for (int i = 0; i < maxCombinations; i++) {
            List<Integer> indices = pickThreeRandomNumbers(str);
            String combination = indices.stream()
                    .sorted()
                    .map(str::charAt)
                    .map(Object::toString)
                    .collect(Collectors.joining());

            if (!combinations.contains(combination)) {
                combinations.add(combination);
            }

            if (combinations.size() == maxCombinations) {
                break;
            }
        }

        return combinations;
    }


}
