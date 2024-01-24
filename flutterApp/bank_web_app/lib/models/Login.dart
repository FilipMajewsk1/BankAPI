class Login{
  int pId;
  String email;
  String password;

  Login({
    required this.pId,
    required this.email,
    required this.password
  });

  factory Login.fromJson(Map<String, dynamic> json){
    return Login(
        pId: json['id'],
        email: json['email'],
        password: json['password']
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': pId,
      'email': email,
      'password': password
    };
  }
}