package com.github.williams.matt.routemaster.ui.recyclerview.model;

import com.github.williams.matt.routemaster.ui.common.recyclerview.RecyclerViewItem;

import uk.co.alt236.bluetoothlelib.device.beacon.ibeacon.IBeaconDevice;

public class IBeaconItem implements RecyclerViewItem {

    private final IBeaconDevice device;

    public IBeaconItem(final IBeaconDevice device) {
        this.device = device;
    }

    public IBeaconDevice getDevice() {
        return device;
    }

}
