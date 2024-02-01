import 'dart:convert';

import 'package:bank_web_app/models/Account.dart';
import 'package:bank_web_app/models/Client.dart';
import 'package:bank_web_app/models/Password.dart';
import 'package:bank_web_app/tools/DataValidation.dart';
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

  static Future<dynamic> getClient()async{
    var response = await Requests.get(baseURL+"norm/clients", withCredentials: true);
    if(response.statusCode == 200) {
      final bodyByte = utf8.decode(response.bodyBytes);
      final body = json.decode(bodyByte);
      final client = Client.fromJson(body);
      return client;
    }
    else{
      return "error with getting client";
    }
  }

  static Future<dynamic> getAccount()async{
    var response = await Requests.get(baseURL+"norm/accounts", withCredentials: true);
    if(response.statusCode == 200) {
      final bodyByte = utf8.decode(response.bodyBytes);
      final body = json.decode(bodyByte);
      final client = Account.fromJson(body);
      return client;
    }
    else{
      return "Error with getting account";
    }
  }

  static Future<dynamic> getPassword(String email)async{
    if(DataValidator.isValidEmail(email)== true) {
      var response = await Requests.get(baseURL + "login?email=" + email);
      if (response.statusCode == 200) {
        final bodyByte = utf8.decode(response.bodyBytes);
        final body = json.decode(bodyByte);
        final password = Password.fromJson(body);
        return password;
      }
      else {
        return "Cant find email";
      }
    }
    else {
      return "Invalid data";
    }
  }

  static Future<dynamic> logIn(int pId, String email, String password)async{
    if(DataValidator.isValidEmail(email) == true &&
        DataValidator.isLengthThree(password) && pId is int) {
      var response = await Requests.post(
          baseURL + "login",
          json: Login(pId: pId, email: email, password: password).toJson(),
          withCredentials: true
      );
      if (response.statusCode == 200) {
        final bodyByte = utf8.decode(response.bodyBytes);
        final body = json.decode(bodyByte);
        final client = Client.fromJson(body);
        return client;
      }
      else {
        return "Incorrect password";
      }
    }
    else {
      return "Invalid data";
    }
  }

  static Future<List<dynamic>> getAllTransfersForClient()async{
    var response = await Requests.get(
        baseURL+"norm/transfers",
        withCredentials: true
    );
    if(response.statusCode == 200) {
      final body = json.decode(response.body);
      return body.map<GetTransfer>((jsonItem) =>
          GetTransfer.fromJson(jsonItem as Map<String, dynamic>)).toList();
    } else{
      List<String> list = [];
      list.add("error");
      return list;
    }
  }

  static Future<dynamic> makeTransfer(String title, String sum, String toAccountNumber)async{
    if(DataValidator.isAlphabetic(title) == true &&
        DataValidator.isNumericWithTwoDecimals(sum) ==true &&
        DataValidator.isLengthTwentySixDigits(toAccountNumber)== true) {
      var response = await Requests.post(
          baseURL + "norm/transfers",
          json: MakeTransfer(
              title: title, sum: sum, toAccountNumber: toAccountNumber)
              .toJson(),
          withCredentials: true
      );
      if(response.statusCode == 200) {

        final bodyByte = utf8.decode(response.bodyBytes);
        final body = json.decode(bodyByte);
        final transfer = MakeTransfer.fromJson(body);
        return transfer;
      }
      else{
        return "Invalid data";
      }
    }
    else{
      return "Invalid data";
    }
  }
}