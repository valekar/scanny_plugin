import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class Scanny {
  static const MethodChannel _channel =
      const MethodChannel('plugins.valekar.io/scanny');

  static const EventChannel _UriEventChannel = const EventChannel("plugins.valekar.io/image_uri");

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<dynamic> get scanForUri async {
    final uri = await _channel.invokeMethod("callScanner");
    //print("URI scanny plugin $uri" );
    return uri;
  }

  static Stream<String> get getFinalUri {
   return _UriEventChannel.receiveBroadcastStream().map(_uriGetter);
  }


  static String _uriGetter(dynamic map){
    if( map is Map) {
      return map['data'];
    }

    return null;
  }


}


