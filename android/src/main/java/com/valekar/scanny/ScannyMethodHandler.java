
package com.valekar.scanny;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import java.io.File;
import java.io.IOException;

import devliving.online.cvscanner.CVScanner;
import devliving.online.cvscanner.util.Util;
import io.flutter.Log;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

import static android.app.Activity.RESULT_OK;


public class ScannyMethodHandler implements MethodChannel.MethodCallHandler, PluginRegistry.ActivityResultListener {

    private int id;
    private static final int REQUEST_TAKE_PHOTO = 121;
    private static final int REQUEST_PICK_PHOTO = 123;
    private static final int REQ_CROP_IMAGE = 122;
    private static final int REQ_PERMISSIONS = 120;
    private Uri currentPhotoUri = null;
    //
    // private MethodChannel.Result result;
    final private int REQ_SCAN = 11;
    private String uri;

    private Activity activity;
    private BinaryMessenger messenger;
    private MethodChannel channel;


    public ScannyMethodHandler(Activity activity, BinaryMessenger messenger, int id) {
        this.activity = activity;
        this.messenger = messenger;
        this.channel = new MethodChannel(this.messenger, "plugins.flutter.io/scanny");
        this.channel.setMethodCallHandler(this);
        this.id = id;
        //this.currentPhotoUri = new Uri();
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        //this.result = result;
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("callScanner")) {
            getDocumentCrop();
            result.success(currentPhotoUri.toString());
        } else {
            result.notImplemented();
        }
    }


    private void startCameraIntent() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                (PermissionChecker.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED &&
                        PermissionChecker.checkSelfPermission(activity, Manifest.permission.CAMERA) == PermissionChecker.PERMISSION_GRANTED)
        ) {
            try {
                Log.d("startCameraIntent", "Start Camera Intent() method called");
                currentPhotoUri = CVScanner.startCameraIntent(activity, REQUEST_TAKE_PHOTO);
                Log.d("CurrentPhotoUri", currentPhotoUri + "");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (PermissionChecker.checkSelfPermission(activity, Manifest.permission.CAMERA)
                    != PermissionChecker.PERMISSION_GRANTED && PermissionChecker.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_PERMISSIONS);
            }
            if (PermissionChecker.checkSelfPermission(activity, Manifest.permission.CAMERA)
                    != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQ_PERMISSIONS);
            }
            if (PermissionChecker.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_PERMISSIONS);
            }
        }
    }


    private void getDocumentCrop() {
        Log.d("Devliving", "Calling Devliving crop Method");
        startCameraIntent();

    }

    private void startImageCropIntent() {
        Log.d("Activity Scanner", activity.toString());
        CVScanner.startManualCropper(activity, this.currentPhotoUri, REQ_CROP_IMAGE);
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case REQ_SCAN:

                case REQ_CROP_IMAGE:
                    if (data != null && data.getExtras() != null) {
                        String path = data.getStringExtra(CVScanner.RESULT_IMAGE_PATH);
                        File file = new File(path);
                        Uri imageUri = Util.getUriForFile(activity, file);
                        if (imageUri != null) {
                            Log.d("Image URI", imageUri.toString());
                            uri = imageUri.toString();
                            currentPhotoUri = imageUri;
                            //this.result.success(uri);
                        }
                    }
                    break;
                case REQUEST_TAKE_PHOTO:
                    Log.d("Take Photo", "REQUEST TAKE PHOTO");
                    startImageCropIntent();
                    break;
                case REQUEST_PICK_PHOTO:
                    if (data.getData() != null) {
                        startImageCropIntent();
                        currentPhotoUri = data.getData();
                    }
                    break;
            }
            return true;
        }
        return false;
    }


/*
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        ScannyMethodHandler guest = (ScannyMethodHandler) obj;
        return id == guest.id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }*/


}

