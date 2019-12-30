package com.valekar.scanny;

import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;
import java.util.Map;

import static io.flutter.plugin.common.EventChannel.EventSink;
import static io.flutter.plugin.common.EventChannel.StreamHandler;


public class ImageStreamHandler implements StreamHandler {

    private EventSink eventSink = null;

    @Override
    public void onListen(Object arguments, EventSink events) {
        this.eventSink = events;
    }

    @Override
    public void onCancel(Object arguments) {
        this.eventSink = null;
    }

    public void send(final Object data) {

        new Handler(Looper.getMainLooper()).post(
            new Runnable() {
                @Override
                public void run() {
                    if(eventSink!=null){
                        eventSink.success(data);
                    }
                }
            }
        );
    }
}
