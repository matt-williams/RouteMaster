package com.github.williams.matt.routemaster.ui.recyclerview.binder;

import android.content.Context;
import android.view.View;

import com.github.williams.matt.routemaster.ui.common.Navigation;
import com.github.williams.matt.routemaster.ui.common.recyclerview.BaseViewBinder;
import com.github.williams.matt.routemaster.ui.common.recyclerview.BaseViewHolder;
import com.github.williams.matt.routemaster.ui.common.recyclerview.RecyclerViewItem;
import com.github.williams.matt.routemaster.ui.recyclerview.holder.LeDeviceHolder;
import com.github.williams.matt.routemaster.ui.recyclerview.model.LeDeviceItem;

import uk.co.alt236.bluetoothlelib.device.BluetoothLeDevice;

public class LeDeviceBinder extends BaseViewBinder<LeDeviceItem> {

    private final Navigation navigation;

    public LeDeviceBinder(Context context, Navigation navigation) {
        super(context);
        this.navigation = navigation;
    }

    @Override
    public void bind(BaseViewHolder<LeDeviceItem> holder, LeDeviceItem item) {

        final LeDeviceHolder actualHolder = (LeDeviceHolder) holder;
        final BluetoothLeDevice device = item.getDevice();

        CommonBinding.bind(getContext(), actualHolder, device);
        actualHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // navigation.openDetailsActivity(device);
            }
        });
    }

    @Override
    public boolean canBind(RecyclerViewItem item) {
        return item instanceof LeDeviceItem;
    }
}
