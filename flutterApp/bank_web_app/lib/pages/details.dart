import 'package:bank_web_app/controllers/SharedController.dart';
import 'package:bank_web_app/models/Account.dart';
import 'package:bank_web_app/widgets/error_window.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../models/Client.dart';
import '../tools/StateControll.dart';

class details extends StatefulWidget {
  const details({Key? key}) : super(key: key);

  @override
  State<details> createState() => _detailsState();
}

class _detailsState extends State<details> {
  var futureClient;
  var futureAccount;

  @override
  void initState() {
    super.initState();
    futureClient = SharedController.getClient();
    futureAccount = SharedController.getAccount();
  }

  @override
  Widget build(BuildContext context){
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
          child: Padding(
            padding: const EdgeInsets.all(40.0),
            child: Column(
              children: [
                Card(
                  color: Colors.grey[850],
                  elevation: 15,
                  child: SizedBox(
                    width: 350,
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      children: <Widget>[
                        Padding(
                          padding: EdgeInsets.all(5.0),
                          child: Text(
                            "Your name:",
                            style: TextStyle(
                                fontWeight: FontWeight.normal,
                                fontSize: 12,
                                color: Colors.amber
                            ),
                          ),
                        ),
                        Padding(
                          padding: EdgeInsets.all(5.0),
                          child: FutureBuilder<dynamic>(
                            future: futureClient,
                            builder: (context, snapshot){
                              if(snapshot.hasData) {
                                if(snapshot.data is String){
                                  showErrorDialog(context, snapshot.data);
                                  return Text("");
                                }else{
                                return Text(
                                  "${snapshot.data!.name} "+"${snapshot.data!.surname}",
                                  style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 18,
                                      color: Colors.amber[100]
                                  ),
                                );
                                }
                              } else{
                                return CircularProgressIndicator();
                              }
                            },
                          )

                        ),
                      ],
                    ),
                  ),
                ),

                Card(
                  color: Colors.grey[850],
                  elevation: 15,
                  child: SizedBox(
                    width: 350,
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
                            child: FutureBuilder<dynamic>(
                          future: futureAccount,
                          builder: (context, snapshot){
                            if(snapshot.hasData) {
                              if(snapshot.data is String){
                                showErrorDialog(context, snapshot.data);
                                return Text("");
                              }else{
                              return Text(
                                "${snapshot.data!.balance}",
                                style: TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 18,
                                    color: Colors.amber[100]
                                ),
                              );}
                            } else{
                              return CircularProgressIndicator();
                            }
                          },
                        )
                        ),
                      ],
                    ),
                  ),
                ),

                Card(
                  color: Colors.grey[850],
                  elevation: 15,
                  child: SizedBox(
                    width: 350,
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      children: <Widget>[
                        Padding(
                          padding: EdgeInsets.all(5.0),
                          child: Text(
                            "Your account number:",
                            style: TextStyle(
                                fontWeight: FontWeight.normal,
                                fontSize: 12,
                                color: Colors.amber
                            ),
                          ),
                        ),
                        Padding(
                          padding: EdgeInsets.all(5.0),
                            child: FutureBuilder<dynamic>(
                              future: futureAccount,
                              builder: (context, snapshot){
                                if(snapshot.hasData) {
                                  if(snapshot.data is String){
                                    showErrorDialog(context, snapshot.data);
                                    return Text("");
                                  }else{
                                  return Text(
                                    "${snapshot.data!.accountNumber}",
                                    style: TextStyle(
                                        fontWeight: FontWeight.bold,
                                        fontSize: 18,
                                        color: Colors.amber[100]
                                    ),
                                  );}
                                } else{
                                  return CircularProgressIndicator();
                                }
                              },
                            )
                        ),
                      ],
                    ),
                  ),
                ),

                Card(
                  color: Colors.grey[850],
                  elevation: 15,
                  child: SizedBox(
                    width: 350,
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      children: <Widget>[
                        Padding(
                          padding: EdgeInsets.all(5.0),
                          child: Text(
                            "Your email:",
                            style: TextStyle(
                                fontWeight: FontWeight.normal,
                                fontSize: 12,
                                color: Colors.amber
                            ),
                          ),
                        ),
                        Padding(
                            padding: EdgeInsets.all(5.0),
                            child: FutureBuilder<dynamic>(
                              future: futureClient,
                              builder: (context, snapshot){
                                if(snapshot.hasData) {
                                  if(snapshot.data is String){
                                    showErrorDialog(context, snapshot.data);
                                    return Text("");
                                  }else{
                                  return Text(
                                    "${snapshot.data!.email}",
                                    style: TextStyle(
                                        fontWeight: FontWeight.bold,
                                        fontSize: 18,
                                        color: Colors.amber[100]
                                    ),
                                  );}
                                } else{
                                  return CircularProgressIndicator();
                                }
                              },
                            )
                        ),
                      ],
                    ),
                  ),
                ),

                Card(
                  color: Colors.grey[850],
                  elevation: 15,
                  child: SizedBox(
                    width: 350,
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      children: <Widget>[
                        Padding(
                          padding: EdgeInsets.all(5.0),
                          child: Text(
                            "Your pesel:",
                            style: TextStyle(
                                fontWeight: FontWeight.normal,
                                fontSize: 12,
                                color: Colors.amber
                            ),
                          ),
                        ),
                        Padding(
                            padding: EdgeInsets.all(5.0),
                            child: FutureBuilder<dynamic>(
                              future: futureClient,
                              builder: (context, snapshot){
                                if(snapshot.hasData) {
                                  if(snapshot.data is String){
                                    showErrorDialog(context, snapshot.data);
                                    return Text("");
                                  }else{
                                  return Text(
                                    "${snapshot.data!.pesel}",
                                    style: TextStyle(
                                        fontWeight: FontWeight.bold,
                                        fontSize: 18,
                                        color: Colors.amber[100]
                                    ),
                                  );}
                                } else{
                                  return CircularProgressIndicator();
                                }
                              },
                            )
                        ),
                      ],
                    ),
                  ),
                ),

                Card(
                  color: Colors.grey[850],
                  elevation: 15,
                  child: SizedBox(
                    width: 350,
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      children: <Widget>[
                        Padding(
                          padding: EdgeInsets.all(5.0),
                          child: Text(
                            "Your phone number:",
                            style: TextStyle(
                                fontWeight: FontWeight.normal,
                                fontSize: 12,
                                color: Colors.amber
                            ),
                          ),
                        ),
                        Padding(
                            padding: EdgeInsets.all(5.0),
                            child: FutureBuilder<dynamic>(
                              future: futureClient,
                              builder: (context, snapshot){
                                if(snapshot.hasData) {
                                  if(snapshot.data is String){
                                    showErrorDialog(context, snapshot.data);
                                    return Text("");
                                  }else{
                                  return Text(
                                    "${snapshot.data!.phoneNum}",
                                    style: TextStyle(
                                        fontWeight: FontWeight.bold,
                                        fontSize: 18,
                                        color: Colors.amber[100]
                                    ),
                                  );}
                                } else{
                                  return CircularProgressIndicator();
                                }
                              },
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
      )
    );
  }

}