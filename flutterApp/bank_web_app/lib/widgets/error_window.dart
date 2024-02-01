import 'package:flutter/material.dart';

class ErrorDialog extends StatelessWidget {
  final String message;

  ErrorDialog({required this.message});

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text(
        "Error",
        style: TextStyle(
            fontSize: 18,
            fontWeight: FontWeight.bold,
            color: Colors.amber
        ),
      ),
      backgroundColor: Colors.grey[850],
      content: Text(
        message,
        style: TextStyle(
            fontSize: 16,
            color: Colors.white
        ),
      ),
      actions: <Widget>[
        OutlinedButton(
          child: Text(
            "OK",
            style: TextStyle(
                fontSize: 18,
                fontWeight: FontWeight.bold,
                color: Colors.amber
            ),
          ),
          style: OutlinedButton.styleFrom(
            side: BorderSide(color: Colors.amber),
          ),
          onPressed: () => Navigator.of(context).pop(),
        ),
      ],
    );
  }
}

void showErrorDialog(BuildContext context, String message) {
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return ErrorDialog(message: message);
    },
  );
}