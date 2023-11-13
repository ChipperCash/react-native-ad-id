package com.adid;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;


import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@ReactModule(name = AdIdModule.NAME)
public class AdIdModule extends ReactContextBaseJavaModule {
  public static final String NAME = "RNAdvertisingId";

  private final ExecutorService executorService;

  public AdIdModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.executorService = Executors.newSingleThreadExecutor();
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  public void getAdvertisingId(Promise promise) {
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        try {
          Context context = getReactApplicationContext();
          Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
          boolean isLimitAdTrackingEnabled = adInfo.isLimitAdTrackingEnabled();
          String advertisingId = isLimitAdTrackingEnabled ? "" : adInfo.getId();

          WritableMap map = Arguments.createMap();
          map.putString("advertisingId", advertisingId);
          map.putBoolean("isLimitAdTrackingEnabled", isLimitAdTrackingEnabled);

          promise.resolve(map);
        } catch (IOException | GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
          promise.reject("Error", e.getMessage());
        }
      }
    });
  }

  @Override
  public void onCatalystInstanceDestroy() {
    executorService.shutdownNow();
    super.onCatalystInstanceDestroy();
  }
}
