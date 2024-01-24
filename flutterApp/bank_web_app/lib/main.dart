
import 'package:bank_web_app/pages/details.dart';
import 'package:bank_web_app/pages/history.dart';
import 'package:bank_web_app/pages/home.dart';
import 'package:bank_web_app/pages/login.dart';
import 'package:bank_web_app/pages/transaction.dart';
import 'package:bank_web_app/tools/StateControll.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() => runApp(
  ChangeNotifierProvider<StateControll>(
    create: (context) => StateControll(),
    child: MaterialApp(
      initialRoute: '/login',
      routes: {
        '/login': (context) => login(),
        '/home': (context) => home(),
        '/transaction': (context) => transaction(),
        '/history': (context) => history(),
        '/details': (context) => details(),
      },
    ),
  ),
);