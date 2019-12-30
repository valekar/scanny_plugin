import 'dart:core';
import 'dart:typed_data';
import 'dart:io';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:scanny/scanny.dart';
void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState(new Scanny());
}

class _MyAppState extends State<MyApp> {
  dynamic _imageBytes;
  final Scanny scanny;
  _MyAppState(this.scanny);

  @override
  void initState() {
    super.initState();
    () async {
      //ask permissions
      scanDocument();
    }();

  }


  Future<void> askPermissions() async {
    await scanny.askPermissions;
  }



  Future<void> scanDocument() async {
    try {
      //ask permissions if permissions are not granted yet
      await askPermissions();
      //call the scanner
       scanny.callScanner;
       //listen to the results of activity
       scanny.getImageBytes.listen((imageBytes){
         setState(() {
           _imageBytes = imageBytes;
         });
       });
    }
    catch(error) {
      print(error);
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Scannny Plugin '),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              _imageBytes == null? Text("No Image loaded"):  Image.memory(_imageBytes),

          ],),

        ),
        floatingActionButton: FloatingActionButton(child: Icon(Icons.camera_enhance), backgroundColor: Colors.teal, onPressed: scanDocument, ),
      ),
    );
  }
}
