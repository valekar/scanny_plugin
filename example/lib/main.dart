import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:scanny/scanny.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  String _imageURI = 'No Image Uri yet';

  @override
  void initState() {
    super.initState();
    initPlatformState();
    scanDocument();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await Scanny.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }



    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }


  Future<void> scanDocument() async {
    String uri;
    try {
      uri = await Scanny.scanForUri;
    }
    catch(error) {
      uri = "Error: Could not get uri";
    }

  setState(() {
    _imageURI = uri;
  });

  }


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
            Text('Running on: $_platformVersion\n'),
            Text("Scanned doc URI is : $_imageURI\n"),
          ],),
        ),
      ),
    );
  }
}
