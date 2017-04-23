package com.github.williams.matt.routemaster;

import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.mobile.AWSMobileClient;
import com.mobile.push.PushManager;

/**
 * RouteApplication class responsible for initializing singletons and other common components.
 */
public class RouteApplication extends MultiDexApplication {
    private static final String LOG_TAG = RouteApplication.class.getSimpleName();
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "RouteApplication.onCreate - Initializing application...");
        super.onCreate();
        initializeApplication();
        sharedPreferences = getSharedPreferences("Route", MODE_PRIVATE);
        Log.d(LOG_TAG, "RouteApplication.onCreate - RouteApplication initialized OK");
    }

    private void initializeApplication() {
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());

        // Set a listener for changes in push notification state
        PushManager.setPushStateListener(new PushManager.PushStateListener() {
            @Override
            public void onPushStateChange(final PushManager pushManager, boolean isEnabled) {
                Log.d(LOG_TAG, "Push Notifications Enabled = " + isEnabled);
          }
        });
    }
}
