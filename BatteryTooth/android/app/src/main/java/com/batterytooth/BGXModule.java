package com.batterytooth;
import androidx.annotation.NonNull;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import com.batterytooth.bgx.BGX_CONNECTION_STATUS;
import com.batterytooth.bgx.BGXpressService;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;

public class BGXModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private ReactApplicationContext mContext;
    private String DeviceAddress;
    private String TAG = "battooth";

    private final BroadcastReceiver mBGXScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Second: Receive data from connected device
            if (intent.getAction().equals(BGXpressService.BGX_DATA_RECEIVED)) {
                Log.d(TAG, "DATA RECEIVED" + intent.getStringExtra("data"));
            }

            // First: Find the device and connect to it
            if (intent.getAction().equals(BGXpressService.BGX_SCAN_DEVICE_DISCOVERED)) {
                HashMap deviceRecord = (HashMap) intent.getSerializableExtra("DeviceRecord");
                String deviceAddress = (String) deviceRecord.get("uuid");
                Log.d(TAG, "deviceAddress:" + deviceAddress);
                DeviceAddress = deviceAddress; // DO WE NEED?

                BGXpressService.startActionBGXConnect(mContext, deviceAddress);
            }

            // Second: When connected status changes
            if (intent.getAction().equals(BGXpressService.BGX_CONNECTION_STATUS_CHANGE)) {
                BGX_CONNECTION_STATUS connectionStatus = (BGX_CONNECTION_STATUS) intent.getExtras().get("bgx-connection-status");
                Log.d(TAG, "Connection status:" + String.valueOf(connectionStatus));
            }
        }
    };

    BGXModule(ReactApplicationContext context) {
        super(context);
        Log.d(TAG, "BGXModule()");
        mContext = context;
        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        Log.d(TAG, "registerBroadcastReceiver()");
        IntentFilter filter = new IntentFilter();
        filter.addAction(BGXpressService.BGX_SCAN_DEVICE_DISCOVERED);
        filter.addAction((BGXpressService.BGX_DATA_RECEIVED));
        mContext.registerReceiver(mBGXScanReceiver, filter);
    }

    @NonNull
    @Override
    @ReactMethod
    public String getName() {
        return "BGXModule";
    }

    @ReactMethod
    public void getNameReact(Callback callback) {
        Log.d("BGXModule", "Create event called with name: ");
        //return new Promise();//getName();

        Integer lol = 3;

        callback.invoke(lol);
    }

    @ReactMethod
    public void scanForDevices() {
        Log.d(TAG, "Starting scan");
        Toast.makeText(mContext, "HEJ!", Toast.LENGTH_SHORT).show();
        BGXpressService.startActionStartScan(mContext);
        Log.d(TAG, "Scan started");
    }

    // BELOW ARE ALL BROADCAST RECEIVER STUFF
    @Override
    public void onHostResume() {

    }

    @Override
    public void onHostPause() {

    }

    @Override
    public void onHostDestroy() {
        mContext.unregisterReceiver(mBGXScanReceiver);
    }
}
