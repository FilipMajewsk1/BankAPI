class GetTransfer{
  int id;
  String title;
  String sum;
  String fromAccountNumber;
  String toAccountNumber;

  GetTransfer({
    required this.id,
    required this.title,
    required this.sum,
    required this.fromAccountNumber,
    required this.toAccountNumber,
  });

  factory GetTransfer.fromJson(Map<String, dynamic> json){
    return GetTransfer(
        id: json['id'],
        title: json['title'],
        sum: json['sum'],
        fromAccountNumber: json['fromAccountNumber'],
        toAccountNumber: json['toAccountNumber'],
    );
  }
}