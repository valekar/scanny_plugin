import 'package:flutter/material.dart';
import 'dart:async';
import 'dart:io';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:scanny/scanny.dart';
import 'package:shared_preferences/shared_preferences.dart';
void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  String _imageURI;


  @override
  void initState() {
    super.initState();
    initPlatformState();
    Scanny.getFinalUri.listen((result){
      setState(() {
        _imageURI = result;
      });
    });
    //scanDocument();
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
      SharedPreferences prefs = await SharedPreferences.getInstance();
      prefs.setString('customImageFile', uri);
      //prefs.
    }
    catch(error) {
      print(error);
      uri = null;
    }



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
              Container(child:
              _imageURI == null? Text("No Image loaded") : Image.network(_imageURI), height: 100, width: 100,)


          ],),

        ),
        floatingActionButton: FloatingActionButton(child: Icon(Icons.camera_enhance), backgroundColor: Colors.teal, onPressed: scanDocument,),
      ),
    );
  }
}
