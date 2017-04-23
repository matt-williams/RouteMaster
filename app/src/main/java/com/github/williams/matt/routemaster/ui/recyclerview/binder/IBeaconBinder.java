package com.github.williams.matt.routemaster.ui.recyclerview.binder;

import android.content.Context;
import android.view.View;

import com.github.williams.matt.routemaster.R;
import com.github.williams.matt.routemaster.ui.common.Navigation;
import com.github.williams.matt.routemaster.ui.common.recyclerview.BaseViewBinder;
import com.github.williams.matt.routemaster.ui.common.recyclerview.BaseViewHolder;
import com.github.williams.matt.routemaster.ui.common.recyclerview.RecyclerViewItem;
import com.github.williams.matt.routemaster.ui.recyclerview.holder.IBeaconHolder;
import com.github.williams.matt.routemaster.ui.recyclerview.model.IBeaconItem;
import com.github.williams.matt.routemaster.util.Constants;

import uk.co.alt236.bluetoothlelib.device.beacon.ibeacon.IBeaconDevice;

public class IBeaconBinder extends BaseViewBinder<IBeaconItem> {

    private final Navigation navigation;

    public IBeaconBinder(Context context, Navigation navigation) {
        super(context);
        this.navigation = navigation;
    }

    @Override
    public void bind(BaseViewHolder<IBeaconItem> holder, IBeaconItem item) {

        final IBeaconHolder actualHolder = (IBeaconHolder) holder;
        final IBeaconDevice device = item.getDevice();

        final String accuracy = Constants.DOUBLE_TWO_DIGIT_ACCURACY.format(device.getAccuracy());

        actualHolder.getIbeaconMajor().setText(String.valueOf(device.getMajor()));
        actualHolder.getIbeaconMinor().setText(String.valueOf(device.getMinor()));
        actualHolder.getIbeaconTxPower().setText(String.valueOf(device.getCalibratedTxPower()));
        actualHolder.getIbeaconUUID().setText(device.getUUID());
        actualHolder.getIbeaconDistance().setText(
                getContext().getString(R.string.formatter_meters, accuracy));
        actualHolder.getIbeaconDistanceDescriptor().setText(device.getDistanceDescriptor().toString());

        CommonBinding.bind(getContext(), actualHolder, device);
        actualHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
     //           navigation.openDetailsActivity(device);
            }
        });
    }

    @Override
    public boolean canBind(RecyclerViewItem item) {
        return item instanceof IBeaconItem;
    }
}
