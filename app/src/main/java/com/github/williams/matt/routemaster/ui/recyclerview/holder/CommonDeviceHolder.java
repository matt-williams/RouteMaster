package com.github.williams.matt.routemaster.ui.recyclerview.holder;

import android.widget.TextView;

public interface CommonDeviceHolder {
    TextView getDeviceName();

    TextView getDeviceAddress();

    TextView getDeviceRssi();

    TextView getDeviceLastUpdated();
}
