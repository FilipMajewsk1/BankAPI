import 'package:flutter/material.dart';

class historyCard extends StatefulWidget {

  final String title;
  final String date;
  final String sum;
  final String accountNumber;

  historyCard({required this.title, required this.date, required this.sum, required this.accountNumber});

  @override
  State<historyCard> createState() => _historyCardState(title: title, date: date, sum: sum, accountNumber: accountNumber);
}

class _historyCardState extends State<historyCard> {

  String title;
  String date;
  String sum;
  String accountNumber;
  _historyCardState({required this.title, required this.date, required this.sum, required this.accountNumber});


  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 400,
      height: 100,
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
                      padding: const EdgeInsets.fromLTRB(0, 25, 0, 10),
                      child: SizedBox(
                        width: 150,
                        height: 30,
                        child: Center(
                          child: Text(
                            title,
                            style: TextStyle(
                                fontWeight: FontWeight.normal,
                                fontSize: 20,
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
                          accountNumber,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
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
                      padding: const EdgeInsets.fromLTRB(150, 5, 0, 0),
                      child: SizedBox(
                        width: 100,
                        height: 20,
                        child: Center(
                          child: Text(
                            date,
                            style: TextStyle(
                                fontWeight: FontWeight.bold,
                                fontSize: 12,
                                color: Colors.amber[100]
                            ),
                          ),
                        ),
                      ),
                    ),

                    SizedBox(
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