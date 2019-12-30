import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class Scanny {
   static const MethodChannel _channel =
      const MethodChannel('plugins.valekar.io/scanny');

  static const EventChannel _ImageEventChannel = const EventChannel("plugins.valekar.io/image_event");

  // call the scanner activity
   void get callScanner {
     _channel.invokeMethod("callScanner");
  }

  // ask permissions if not granted
  void get askPermissions async {
     await _channel.invokeMethod("askPermissions");
  }

  // use this method if you need image Uri
   Stream<String> get getFinalUri {
    return _ImageEventChannel.receiveBroadcastStream().map(_uriGetter);
  }

  // use this method if you need
  Stream<dynamic> get getImageBytes
  {
    return _ImageEventChannel.receiveBroadcastStream().map(_imageBytesGetter);
  }


   String _uriGetter(dynamic map){
    if( map is Map) {
      return map["image_uri"];
    }
    return null;
  }

  dynamic _imageBytesGetter(dynamic map){
    if( map is Map) {
      return map["image_array"];
    }
    return null;
  }


}


class ImageValues {
  final imageByteArray;
  final imageUri;

  ImageValues(this.imageUri, this.imageByteArray);
}


