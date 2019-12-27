import 'dart:async';

import 'package:flutter/services.dart';

class Scanny {
  static const MethodChannel _channel =
      const MethodChannel('scanny');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
