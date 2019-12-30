/*
package com.valekar.scanny;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import java.io.File;
import java.io.IOException;

import devliving.online.cvscanner.CVScanner;
import devliving.online.cvscanner.util.Util;
import io.flutter.Log;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

import static android.app.Activity.RESULT_OK;

public class ScannyActivityListener {

    final private Activity activity;
    private static final int REQUEST_TAKE_PHOTO = 121;
    private static final int REQUEST_PICK_PHOTO = 123;
    private static final int REQ_CROP_IMAGE = 122;
    private static final int REQ_PERMISSIONS = 120;
    public Uri currentPhotoUri = null;
    final private int REQ_SCAN = 11;

    private String uri;

    ScannyActivityListener(Activity activity){
        this.activity = activity;

    }

    private void startImageCropIntent(){
        Log.d("ACCTIVITYYYY",activity.toString());
       // CVScanner.startManualCropper(activity, this.currentPhotoUri, REQ_CROP_IMAGE);
    }


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
                           setUri(imageUri.toString());
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
        return  false;
    }



    protected void startCameraIntent() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                (   PermissionChecker.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED &&
                        PermissionChecker.checkSelfPermission(activity, Manifest.permission.CAMERA) == PermissionChecker.PERMISSION_GRANTED    )
        ) {
            try {
                Log.d("startCameraIntent", "Start Camera Intent() method called");
                currentPhotoUri = CVScanner.startCameraIntent(activity, REQUEST_TAKE_PHOTO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
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


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
*/
