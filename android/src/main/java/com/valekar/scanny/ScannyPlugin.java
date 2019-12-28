package com.valekar.scanny;

import android.app.Activity;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * ScannyPlugin
 */
public class ScannyPlugin implements FlutterPlugin, ActivityAware {

    private FlutterPluginBinding flutterPluginBinding;


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        this.flutterPluginBinding = flutterPluginBinding;
    }


    public static void registerWith(Registrar registrar) {
        ScannyPlugin scannyPlugin = new ScannyPlugin();
        scannyPlugin.startListingForOldVersion(registrar.activity(), registrar.messenger(), registrar);
    }


    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }


    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        startListening(binding.getActivity(), flutterPluginBinding.getBinaryMessenger(), binding);

    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        flutterPluginBinding = null;
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        onAttachedToActivity(binding);

    }

    @Override
    public void onDetachedFromActivity() {

    }

    private void startListening(Activity activity, BinaryMessenger messenger, ActivityPluginBinding binding) {
        ScannyMethodHandler scannyMethodHandler = new ScannyMethodHandler(activity, messenger, 1235);
        binding.addActivityResultListener(scannyMethodHandler);

    }


    private void startListingForOldVersion(Activity activity, BinaryMessenger messenger, Registrar registrar) {
        ScannyMethodHandler scannyMethodHandler = new ScannyMethodHandler(activity, messenger, 1235);
        registrar.addActivityResultListener(scannyMethodHandler);

    }


}
