class MakeTransfer{
  String title;
  String sum;
  String toAccountNumber;

  MakeTransfer({
    required this.title,
    required this.sum,
    required this.toAccountNumber,
  });

  factory MakeTransfer.fromJson(Map<String, dynamic> json){
    return MakeTransfer(
        title: json['title'],
        sum: json['sum'],
        toAccountNumber: json['toAccountNumber'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'title': title,
      'sum': sum,
      'toAccountNumber': toAccountNumber,
    };
  }
}