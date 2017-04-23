package com.github.williams.matt.routemaster.ui.recyclerview.model;

import com.github.williams.matt.routemaster.ui.common.recyclerview.RecyclerViewItem;

import uk.co.alt236.bluetoothlelib.device.BluetoothLeDevice;

public class LeDeviceItem implements RecyclerViewItem {

    private final BluetoothLeDevice device;

    public LeDeviceItem(final BluetoothLeDevice device) {
        this.device = device;
    }

    public BluetoothLeDevice getDevice() {
        return device;
    }
}
