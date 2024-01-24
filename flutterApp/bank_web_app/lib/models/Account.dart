class Account{
  int id;
  String accountNumber;
  double balance;

  Account({
    required this.id,
    required this.accountNumber,
    required this.balance
  });

  factory Account.fromJson(Map<String, dynamic> json) {
    return Account(
        id: json['id'],
        accountNumber: json['accountNumber'],
        balance: json['balance']
    );
  }
}