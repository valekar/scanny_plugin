import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class Scanny {
  static const MethodChannel _channel =
      const MethodChannel('plugins.flutter.io/scanny');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<dynamic> get scanForUri async {
    final uri = await _channel.invokeMethod("callScanner");
    //print("URI scanny plugin $uri" );
    return uri;
  }
}
