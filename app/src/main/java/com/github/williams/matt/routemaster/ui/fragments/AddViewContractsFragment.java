package com.github.williams.matt.routemaster.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.williams.matt.routemaster.R;
import com.github.williams.matt.routemaster.network.ApiClient;
import com.github.williams.matt.routemaster.ui.StartUpActivity;
import com.github.williams.matt.routemaster.ui.common.recyclerview.RecyclerViewItem;
import com.github.williams.matt.routemaster.ui.recyclerview.model.IBeaconItem;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import uk.co.alt236.bluetoothlelib.device.beacon.ibeacon.IBeaconDevice;

/**
 *  UI to show that we can connect to a BLE Beacon
 */
public class AddViewContractsFragment extends Fragment {

    private ViewContractListener mListener;

    public AddViewContractsFragment() {
        // Required empty public constructor
    }

    public static AddViewContractsFragment newInstance() {
        AddViewContractsFragment fragment = new AddViewContractsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_view_contracts, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

/*
        for (IBeaconItem iBeaconItem : StartUpActivity.beaconItemList) {
            IBeaconDevice device = iBeaconItem.getDevice();
            if (iBeaconItem.getDevice().getName().contains("Route")) {
*/

                // convert iP address
//http://10.1.104.78:8080/contracts/0x184fc661f5ad3ccdfb0b9a3b0861057aff712b?account=0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c
//
                new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] params) {
                        new ApiClient("http://10.1.104.78:8080").userCall("0xbb297ff77c642fd73a8b21010d58eb8d6f12b51f", "0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c")
                                .observeOn(Schedulers.newThread())
                                .doOnError(new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
                                })
                                .doOnNext(new Action1() {
                                    @Override
                                    public void call(Object o) {
                                        // Parse the Object o


                                        // App would send update to Ethereum
                                    }
                                }).subscribe();
                        return null;
                    }
                }.execute();



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ViewContractListener) {
            mListener = (ViewContractListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement StartUpListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface ViewContractListener {

        void onAdd(Uri uri);
    }
}
