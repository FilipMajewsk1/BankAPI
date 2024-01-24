import 'dart:convert';

import 'package:bank_web_app/models/Account.dart';
import 'package:bank_web_app/models/Client.dart';
import 'package:bank_web_app/models/Password.dart';
import 'package:bank_web_app/tools/StateControll.dart';
import 'package:provider/provider.dart';
import 'package:requests/requests.dart';

import '../models/GetTransfer.dart';
import '../models/Login.dart';
import '../models/MakeTransfer.dart';

const String baseURL = "http://localhost:8080/api/";


class SharedController{

  final StateControll stateControll = Provider.of<StateControll>(
      NavigationContext.mainNavKey.currentContext!,
      listen: false);

  static Future<Client> getClient()async{
    var response = await Requests.get(baseURL+"norm/clients/", withCredentials: true);
    final bodyByte = utf8.decode(response.bodyBytes);
    final body  = json.decode(bodyByte);
    final client = Client.fromJson(body);
    return client;
  }

  static Future<Account> getAccount()async{
    var response = await Requests.get(baseURL+"norm/accounts/", withCredentials: true);
    final bodyByte = utf8.decode(response.bodyBytes);
    final body  = json.decode(bodyByte);
    final client = Account.fromJson(body);
    return client;
  }

  static Future<Password> getPassword(String email)async{
    var response = await Requests.get(baseURL+"login?email="+email);
    final bodyByte = utf8.decode(response.bodyBytes);
    final body  = json.decode(bodyByte);
    final password = Password.fromJson(body);
    return password;
  }

  static Future<Client?> logIn(int pId, String email, String password)async{
    var response = await Requests.post(
        baseURL+"login",
        json: Login(pId: pId, email: email, password: password).toJson(),
        withCredentials: true
    );
    final bodyByte = utf8.decode(response.bodyBytes);
    final body  = json.decode(bodyByte);
    final client = Client.fromJson(body);
    return client;
  }

  static Future<List<GetTransfer>> getAllTransfersForClient()async{
    var response = await Requests.get(baseURL+"norm/transfers");
    final body  = json.decode(response.body);
    return body.map<GetTransfer>((jsonItem) => MakeTransfer.fromJson(jsonItem as Map<String, dynamic>)).toList();
  }

  static Future<MakeTransfer> makeTransfer(String title, String sum, String toAccountNumber,String fromAccountNumber)async{
    var response = await Requests.post(
      baseURL+"transfers",
      json: MakeTransfer( title: title, sum: sum, toAccountNumber: toAccountNumber,fromAccountNumber: fromAccountNumber),
      withCredentials: true
    );
    final bodyByte = utf8.decode(response.bodyBytes);
    final body  = json.decode(bodyByte);
    final transfer = MakeTransfer.fromJson(body);
    return transfer;
  }
}