import 'dart:async';

import 'package:bank_web_app/models/Client.dart';
import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:provider/provider.dart';

class StateControll extends ChangeNotifier{

  bool _isAuth = false;

  ValueNotifier<int?> _sessionValidity = ValueNotifier(null);

  set isAuth(bool value) {
    _isAuth = value;
    notifyListeners();
  }

  int? get sessionValidity => _sessionValidity.value;

  ValueNotifier<int?> get sessionValidityValueNotifier => _sessionValidity;

  bool get isAuth => _isAuth;

  Timer _timer = Timer(Duration.zero, () {});

  void refreshValidity() {
    _sessionValidity.value = 5 * 60;
    _timer.cancel();
    _timer = Timer.periodic(Duration(seconds: 1), (timer) {
      if (_sessionValidity.value == null || !_isAuth) {
        _timer.cancel();
        return;
      }
      _sessionValidity.value = _sessionValidity.value! - 1;
      if (_sessionValidity.value == 0) {
        _timer.cancel();
        setStatelogout();
      }
      _sessionValidity.notifyListeners();
    });
  }

  Client? _client;

  Client? get client => _client;

  void setStateLogin(Client client) {
    isAuth = true;
    _client = client;
    refreshValidity();
  }

  void setStatelogout() {
    isAuth = false;
    _client = null;
    _sessionValidity.value = null;
    notifyListeners();
  }

  static StateControll get instance => Provider.of(NavigationContext._mainNavKey.currentContext!, listen: false);
}

class NavigationContext {
  static final _mainNavKey = GlobalKey<NavigatorState>();
  static final _authNavKey = GlobalKey<NavigatorState>();

  static GlobalKey<NavigatorState> get mainNavKey => _mainNavKey;

  static GlobalKey<NavigatorState> get authNavKey => _authNavKey;
}