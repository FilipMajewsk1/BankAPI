class Client{
  int? id;
  String? name;
  String? surname;
  String? email;
  String? pesel;
  String? phoneNum;
  int? accountId;

  Client({
    this.id,
    this.name,
    this.surname,
    this.email,
    this.pesel,
    this.phoneNum,
    this.accountId
  });

  factory Client.fromJson(Map<String, dynamic> json) {
    return Client(
        id: json['id'],
        name: json['name'],
        surname: json['surname'],
        email: json['email'],
        pesel: json['pesel'],
        phoneNum: json['phoneNum'],
        accountId: json['account_id']
    );
  }
}