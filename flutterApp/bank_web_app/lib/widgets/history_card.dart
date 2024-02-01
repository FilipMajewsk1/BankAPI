import 'package:flutter/material.dart';

class historyCard extends StatefulWidget {

  final String title;
  final String sum;
  final String toAccountNumber;
  final String fromAccountNumber;

  historyCard({required this.title, required this.sum, required this.toAccountNumber, required this.fromAccountNumber});

  @override
  State<historyCard> createState() => _historyCardState(title: title, sum: sum, toAccountNumber: toAccountNumber, fromAccountNumber: fromAccountNumber);
}

class _historyCardState extends State<historyCard> {

  String title;
  String sum;
  String toAccountNumber;
  String fromAccountNumber;
  _historyCardState({required this.title, required this.sum, required this.toAccountNumber, required this.fromAccountNumber});


  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 700,
      height: 200,
      child: Card(
        color: Colors.grey[850],
        elevation: 15,
        child: Row(
          children: [

            Center(
              child: Padding(
                padding: const EdgeInsets.fromLTRB(20, 0, 25, 0),
                child: SizedBox(
                  width: 50,
                  height: 50,
                  child: Icon(
                    Icons.multiple_stop,
                    size: 25,
                    color: Colors.amber
                  )
                ),
              ),
            ),

            Expanded(
              child: Center(
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.fromLTRB(0, 55, 0, 20),
                      child: SizedBox(
                        width: 250,
                        height: 30,
                        child: Center(
                          child: Text(
                            title,
                            style: TextStyle(
                                fontWeight: FontWeight.bold,
                                fontSize: 16,
                                color: Colors.amber
                            ),
                          ),
                        ),
                      ),
                    ),

                    SizedBox(
                      width: 300,
                      height: 20,
                      child: Center(
                        child: Text(
                          "from: "+fromAccountNumber,
                          style: TextStyle(
                              fontWeight: FontWeight.normal,
                              fontSize: 12,
                              color: Colors.amber[100]
                          ),
                        ),
                      ),
                    ),
                    SizedBox(
                      width: 300,
                      height: 20,
                      child: Center(
                        child: Text(
                          "to: " +toAccountNumber,
                          style: TextStyle(
                              fontWeight: FontWeight.normal,
                              fontSize: 12,
                              color: Colors.amber[100]
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),

            Expanded(
              child: Center(
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.fromLTRB(0, 100, 0, 0),
                      child: SizedBox(
                        width: 150,
                        height: 30,
                        child: Center(
                          child: Text(
                            sum + " PLN",
                            style: TextStyle(
                                fontWeight: FontWeight.bold,
                                fontSize: 20,
                                color: Colors.amber
                            ),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),

          ],
        ),
      )
    );
  }
}