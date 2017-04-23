package com.github.williams.matt.routemaster.ui.recyclerview.binder;

import android.content.Context;

import com.github.williams.matt.routemaster.R;
import com.github.williams.matt.routemaster.ui.recyclerview.holder.CommonDeviceHolder;
import com.github.williams.matt.routemaster.util.Constants;

import uk.co.alt236.bluetoothlelib.device.BluetoothLeDevice;

/*package*/ class CommonBinding {

    public static void bind(final Context context,
                            final CommonDeviceHolder holder,
                            final BluetoothLeDevice device) {

        final String deviceName = device.getName();
        final double rssi = device.getRssi();

        if (deviceName != null && deviceName.length() > 0) {
            holder.getDeviceName().setText(deviceName);
        } else {
            holder.getDeviceName().setText(R.string.unknown_device);
        }

        final String rssiString =
                context.getString(R.string.formatter_db, String.valueOf(rssi));
        final String runningAverageRssiString =
                context.getString(R.string.formatter_db, String.valueOf(device.getRunningAverageRssi()));

        holder.getDeviceLastUpdated().setText(
                android.text.format.DateFormat.format(
                        Constants.TIME_FORMAT, new java.util.Date(device.getTimestamp())));
        holder.getDeviceAddress().setText(device.getAddress());
        holder.getDeviceRssi().setText(rssiString + " / " + runningAverageRssiString);
    }
}
