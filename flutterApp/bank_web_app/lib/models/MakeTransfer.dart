class MakeTransfer{
  String title;
  String sum;
  String fromAccountNumber;
  String toAccountNumber;

  MakeTransfer({
    required this.title,
    required this.sum,
    required this.fromAccountNumber,
    required this.toAccountNumber,
  });

  factory MakeTransfer.fromJson(Map<String, dynamic> json){
    return MakeTransfer(
        title: json['title'],
        sum: json['sum'],
        fromAccountNumber: json['fromAccountNumber'],
        toAccountNumber: json['toAccountNumber'],
    );
  }
}