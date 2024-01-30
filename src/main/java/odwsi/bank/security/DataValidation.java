package odwsi.bank.security;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DataValidation {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d.*\\d.*\\d).{10,}$";
    private static final String THREE_LETTER_REGEX = "^.{3}$";
    private static final String TRANSFER_SUM_REGEX = "^\\d+\\.\\d{2}$";
    private static final String ACCOUNT_NUMBER_REGEX = "^\\d{26}$";

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validate3LetterWord(String password) {
        Pattern pattern = Pattern.compile(THREE_LETTER_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validateTransferSum(String data) {
        Pattern pattern = Pattern.compile(TRANSFER_SUM_REGEX);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    public static boolean validateAccountNumber(String data) {
        Pattern pattern = Pattern.compile(ACCOUNT_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }
}
