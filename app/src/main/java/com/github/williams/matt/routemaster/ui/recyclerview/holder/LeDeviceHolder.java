package com.github.williams.matt.routemaster.ui.recyclerview.holder;

import android.view.View;
import android.widget.TextView;

import com.github.williams.matt.routemaster.R;
import com.github.williams.matt.routemaster.ui.common.recyclerview.BaseViewHolder;
import com.github.williams.matt.routemaster.ui.recyclerview.model.LeDeviceItem;

public class LeDeviceHolder extends BaseViewHolder<LeDeviceItem> implements CommonDeviceHolder {

    private final TextView deviceName;
    private final TextView deviceAddress;
    private final TextView deviceRssi;
    private final TextView deviceLastUpdated;

    public LeDeviceHolder(View itemView) {
        super(itemView);

        this.deviceAddress = (TextView) itemView.findViewById(R.id.device_address);
        this.deviceName = (TextView) itemView.findViewById(R.id.device_name);
        this.deviceRssi = (TextView) itemView.findViewById(R.id.device_rssi);
        this.deviceLastUpdated = (TextView) itemView.findViewById(R.id.device_last_update);
    }

    @Override
    public TextView getDeviceName() {
        return deviceName;
    }

    @Override
    public TextView getDeviceAddress() {
        return deviceAddress;
    }

    @Override
    public TextView getDeviceRssi() {
        return deviceRssi;
    }

    @Override
    public TextView getDeviceLastUpdated() {
        return deviceLastUpdated;
    }
}
