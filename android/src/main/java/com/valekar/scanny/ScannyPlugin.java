package com.valekar.scanny;

import android.app.Activity;

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** ScannyPlugin */
public class ScannyPlugin implements FlutterPlugin, ActivityAware {


  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    final MethodChannel channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "scanny");
    channel.setMethodCallHandler(ScannyPlugin.scannyMethodHandler());

  }


  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "scanny");
    channel.setMethodCallHandler(ScannyPlugin.scannyMethodHandler());
  }



  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
  }

  private static ScannyMethodHandler scannyMethodHandler(){
    return new ScannyMethodHandler();
  }

  @Override
  public void onAttachedToActivity(ActivityPluginBinding binding) {
    //binding::addActivityResultListener;
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }

  @Override
  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {
    onAttachedToActivity(binding);

  }

  @Override
  public void onDetachedFromActivity() {

  }
}
