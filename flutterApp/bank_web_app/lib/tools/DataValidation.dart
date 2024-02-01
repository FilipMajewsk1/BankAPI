class DataValidator {

  static bool isValidEmail(String email) {
    final emailRegex = RegExp(r'^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$');
    return emailRegex.hasMatch(email);
  }
  static bool isLengthThree(String text) {
    return text.length == 3;
  }
  static bool isLengthTwentySixDigits(String text) {
    final regex = RegExp(r'^\d{26}$');
    return regex.hasMatch(text);
  }
  static bool isNumericWithTwoDecimals(String text) {
    final regex = RegExp(r'^\d+\.\d{2}$');
    return regex.hasMatch(text);
  }
  static bool isAlphabetic(String text) {
    final regex = RegExp(r'^[a-zA-Z ]+$');
    return regex.hasMatch(text) && text.length <= 25;
  }
}