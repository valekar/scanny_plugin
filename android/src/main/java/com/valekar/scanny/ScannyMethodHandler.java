package com.valekar.scanny;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class ScannyMethodHandler implements   MethodChannel.MethodCallHandler {
    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if(call.method.equals("callScanner")){
            result.success("This is a dummy URI");
        }
            // call the scanner from here
        else {
            result.notImplemented();
        }
    }
}
