package com.github.williams.matt.routemaster.ui.common;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

public class Navigation {

    private final Activity mActivity;

    public Navigation(final Activity activity) {
        mActivity = activity;
    }

    private void startActivity(final Intent intent) {
        ActivityCompat.startActivity(mActivity, intent, null);
    }
}
