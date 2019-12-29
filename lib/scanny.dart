import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class Scanny {
   static const MethodChannel _channel =
      const MethodChannel('plugins.valekar.io/scanny');

  static const EventChannel _ImageEventChannel = const EventChannel("plugins.valekar.io/image_event");


   void scanForUri() {
     _channel.invokeMethod("callScanner");
  }

   Stream<String> get getFinalUri {
    return _ImageEventChannel.receiveBroadcastStream().map(_uriGetter);
  }


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


