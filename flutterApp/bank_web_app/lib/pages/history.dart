import 'package:bank_web_app/controllers/SharedController.dart';
import 'package:bank_web_app/widgets/history_card.dart';
import 'package:flutter/material.dart';

import '../models/GetTransfer.dart';

class history extends StatefulWidget {
  const history({Key? key}) : super(key: key);

  @override
  State<history> createState() => _historyState();
}

class _historyState extends State<history> {

  Future<List<GetTransfer>> list = SharedController.getAllTransfersForClient();
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[850],
      appBar: AppBar(
        title: Text(
          "HoneyMoney.pl",
          style: TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: 24,
              color: Colors.grey[850]
          ),
        ),
        backgroundColor: Colors.amber,
        iconTheme: IconThemeData(
          color: Colors.grey[850],
        ),
      ),

      body: SingleChildScrollView(
        child: Center(
          child:SizedBox(
            width: 550,
            height: 1000,
            child: FutureBuilder<List<GetTransfer>>(
                future: list,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    final listt = snapshot.data!;
                    return buildHistory(listt);
                  }
                  else {
                    return (Center(
                        child:CircularProgressIndicator()));
                  }
                }
            ),
          ),
        ),
      ),
    );
  }
  Widget buildHistory(List<GetTransfer> list) =>
    ListView.builder(
      itemCount:  list.length,
      itemBuilder: (context, index){
        final transfer = list[index];
        return historyCard(
          title: transfer.title,
          sum: transfer.sum,
          date: "12.04.2023",
          accountNumber: transfer.fromAccountNumber,
        );
      }
    );

}