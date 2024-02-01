class Password{
  int id;
  List<int> positions;

  Password({
    required this.id,
    required this.positions
  });

  factory Password.fromJson(Map<String, dynamic> json) {
    return Password(
      id: json['id'],
      positions: json['positions'].toString()
          .split('/')
          .where((element) => element.isNotEmpty)
          .map(int.parse)
          .toList(),
    );
  }
}