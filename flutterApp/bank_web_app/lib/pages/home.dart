import 'dart:html';

import 'package:bank_web_app/controllers/SharedController.dart';
import 'package:bank_web_app/widgets/error_window.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../models/Account.dart';
import '../tools/StateControll.dart';

class home extends StatefulWidget {
  const home({Key? key}) : super(key: key);

  @override
  State<home> createState() => _homeState();
}

class _homeState extends State<home> {

  var futureAccount;
  late Account account;

  @override
  void initState() {
    super.initState();
    futureAccount = SharedController.getAccount();
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    futureAccount = SharedController.getAccount();
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
        leading: Theme(
          data: Theme.of(context).copyWith(
            popupMenuTheme: PopupMenuThemeData(
              color: Colors.amber[100],
            ),
          ),
          child: PopupMenuButton(
            onSelected: (value) {
              switch (value) {
                case 1:
                  Navigator.pushNamed(context, '/transaction');
                  break;
                case 2:
                  Navigator.pushNamed(context, '/history');
                  break;
                case 3:
                  Navigator.pushNamed(context, '/details');
                  break;
              }
            },
            offset: Offset(-20,60),
            itemBuilder: (BuildContext context) => <PopupMenuEntry>[
              PopupMenuItem(
                child: Center(
                  child: Text(
                    "Make a transaction",
                    style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 16,
                        color: Colors.grey[850]
                    ),
                  ),
                ),
                value: 1,
              ),
              PopupMenuItem(
                child: Center(
                  child: Text(
                    "Show history",
                    style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 16,
                        color: Colors.grey[850]
                    ),
                  ),
                ),
                value: 2,
              ),
              PopupMenuItem(
                child: Center(
                  child: Text(
                    "Show details of your account",
                    style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 16,
                        color: Colors.grey[850]
                    ),
                  ),
                ),
                value: 3,
              ),
            ],
            icon: Icon(Icons.menu),
          ),
        ),
      ),

      body: SingleChildScrollView(
        child: Center(
          child: Column(
            children: [

              Padding(
                padding: EdgeInsets.fromLTRB(0, 100, 0, 30),
                child: Text(
                  "Welcome "+Provider.of<StateControll>(context, listen: false).client!.name!,
                  style: TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 36,
                      color: Colors.amber
                  ),
                ),
              ),

              Card(
                color: Colors.grey[850],
                elevation: 15,
                child: SizedBox(
                  width: 300,
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    children: <Widget>[
                      Padding(
                        padding: EdgeInsets.all(5.0),
                        child: Text(
                          "Your balance:",
                          style: TextStyle(
                              fontWeight: FontWeight.normal,
                              fontSize: 12,
                              color: Colors.amber
                          ),
                        ),
                      ),
                      Padding(
                        padding: EdgeInsets.all(5.0),
                        child:FutureBuilder<dynamic>(
                          future: futureAccount,
                          builder:(context, snapshot) {
                            if(snapshot.hasData){
                              if(snapshot.data is String){
                                showErrorDialog(context, snapshot.data);
                                return Text("");
                              }else {
                                return Text(
                                  "${snapshot.data!.balance}",
                                  style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 24,
                                      color: Colors.amber[100]
                                  ),
                                );
                              }
                            } else{
                              return CircularProgressIndicator();
                            }
                          }
                        )
                      ),
                    ],
                  ),
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}