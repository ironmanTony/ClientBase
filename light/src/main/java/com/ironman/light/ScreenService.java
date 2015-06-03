package com.ironman.light;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.os.IBinder;
import android.util.Log;

public class ScreenService extends Service {

    private final String tag = ScreenService.class.getName()+"flashLight";
    private long startTime = 0;
    private int pressCount = 0;
    private final int totalCount = 3;
    private long gapTime = 1500;
    private boolean isLightOn = false;

    private BroadcastReceiver receiverScreenOn = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(tag, "screen on");
            count();
        }
    };
    private BroadcastReceiver receiverScreenOff = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(tag, "screen off");
            count();
        }
    };

    public ScreenService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(tag, "service created");
        startListen();
    }


    public void startListen(){
        IntentFilter mIntentFilter=new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiverScreenOff, mIntentFilter);
        IntentFilter onIntent = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiverScreenOn, onIntent);
    }

    private synchronized void count(){
        if(isLightOn){
            pressCount = 0;
            trunLight(false);
            startTime = 0;
            return;
        }
        if(startTime == 0){
            startTime = System.currentTimeMillis();
            pressCount = 0;
        }else if(pressCount < totalCount -1){

        }else if(pressCount == totalCount-1){
            if(System.currentTimeMillis() - startTime < gapTime){
                trunLight(true);
                startTime = 0;
            }else{
                startTime = 0;
            }
        }else{
            startTime = 0;
        }
        Log.d(tag + "count", "-------" + pressCount);
        pressCount ++;
    }

    private void trunLight(boolean b) {
        Log.d(tag+"turn", ""+b);
        Camera camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        if(parameters!=null){
            if(b){
                camera.startPreview();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            }else{
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            }
            camera.setParameters(parameters);
            isLightOn = b;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverScreenOn);
        unregisterReceiver(receiverScreenOff);
    }
}
