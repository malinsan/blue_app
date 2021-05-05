package com.batterytooth;
import androidx.annotation.NonNull;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import java.util.Map;
import java.util.HashMap;

public class BGXModule extends ReactContextBaseJavaModule {
    BGXModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    @ReactMethod
    public String getName() {
        return "BGXModule";
    }

    @ReactMethod
    public void getNameReact(Callback callback) {
        Log.d("CalendarModule", "Create event called with name: ");
        //return new Promise();//getName();

        Integer lol = 3;

        callback.invoke(lol);

    }
}
