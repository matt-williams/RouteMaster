package com.github.williams.matt.routemaster.ui;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.mobilehelper.auth.IdentityManager;
import com.github.williams.matt.routemaster.PushListenerService;
import com.github.williams.matt.routemaster.R;
import com.github.williams.matt.routemaster.SplashActivity;
import com.github.williams.matt.routemaster.containers.BluetoothLeDeviceStore;
import com.github.williams.matt.routemaster.network.ApiClient;
import com.github.williams.matt.routemaster.ui.common.recyclerview.RecyclerViewBinderCore;
import com.github.williams.matt.routemaster.ui.common.recyclerview.RecyclerViewItem;
import com.github.williams.matt.routemaster.ui.fragments.AddViewContractsFragment;
import com.github.williams.matt.routemaster.ui.fragments.StartUpFragment;
import com.github.williams.matt.routemaster.ui.recyclerview.model.IBeaconItem;
import com.github.williams.matt.routemaster.ui.recyclerview.model.LeDeviceItem;
import com.github.williams.matt.routemaster.util.BluetoothLeScanner;
import com.github.williams.matt.routemaster.util.BluetoothUtils;
import com.mobile.AWSMobileClient;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;
import rx.schedulers.Schedulers;
import uk.co.alt236.bluetoothlelib.device.BluetoothLeDevice;
import uk.co.alt236.bluetoothlelib.device.beacon.BeaconType;
import uk.co.alt236.bluetoothlelib.device.beacon.BeaconUtils;
import uk.co.alt236.bluetoothlelib.device.beacon.ibeacon.IBeaconDevice;

;

public class StartUpActivity extends AppCompatActivity implements StartUpFragment.StartUpListener, AddViewContractsFragment.ViewContractListener {
    private static final String LOG_TAG = StartUpActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 99;


    /**
     * The identity manager used to keep track of the current user account.
     */
    private IdentityManager identityManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDeviceStore = new BluetoothLeDeviceStore();

        // Obtain a reference to the mobile client. It is created in the Application class,
        // but in case a custom Application class is not used, we initialize it here if necessary.
        AWSMobileClient.initializeMobileClientIfNecessary(this);

        // Obtain a reference to the mobile client. It is created in the Application class.
        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();

        // Obtain a reference to the identity manager.
        identityManager = awsMobileClient.getIdentityManager();
        mBluetoothUtils = new BluetoothUtils(this);
        mScanner = new BluetoothLeScanner(mLeScanCallback, mBluetoothUtils);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, StartUpFragment.newInstance())
                .commit();

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                startScan();
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            startScan();
        }
    }
    private RecyclerViewBinderCore mCore;
    private BluetoothUtils mBluetoothUtils;
    private BluetoothLeScanner mScanner;
    private BluetoothLeDeviceStore mDeviceStore;

    public static List<RecyclerViewItem> itemList = new ArrayList<>();
    public static List<IBeaconItem> beaconItemList = new ArrayList<>();
    public static List<LeDeviceItem> devices = new ArrayList<>();
    private final BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
            final BluetoothLeDevice deviceLe = new BluetoothLeDevice(device, rssi, scanRecord, System.currentTimeMillis());
            mDeviceStore.addDevice(deviceLe);

            for (final BluetoothLeDevice leDevice : mDeviceStore.getDeviceList()) {
                if (BeaconUtils.getBeaconType(leDevice) == BeaconType.IBEACON) {
                    IBeaconItem iBeaconItem = new IBeaconItem(new IBeaconDevice(leDevice));
                    beaconItemList.add(iBeaconItem);
                    itemList.add(iBeaconItem);
                } else {
                    devices.add(new LeDeviceItem(leDevice));
                    itemList.add(new LeDeviceItem(leDevice));
                }
            }

        }
    };

    //0x184fc661f5ad3ccd17fb0b9a3b0861057aff712b


    @Override
    protected void onPause() {
        super.onPause();
        mScanner.scanLeDevice(-1, false);

        // unregister notification receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(notificationReceiver);

    }


    private void startScan() {
        final boolean isBluetoothOn = mBluetoothUtils.isBluetoothOn();
        final boolean isBluetoothLePresent = mBluetoothUtils.isBluetoothLeSupported();

        mBluetoothUtils.askUserToEnableBluetoothIfNeeded();
        if (isBluetoothOn && isBluetoothLePresent) {
            mScanner.scanLeDevice(-1, true);
            invalidateOptionsMenu();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    startScan();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!AWSMobileClient.defaultMobileClient().getIdentityManager().isUserSignedIn()) {
            // In the case that the activity is restarted by the OS after the application
            // is killed we must redirect to the splash activity to handle the sign-in flow.
            Intent intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return;
        }


        // register notification receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(notificationReceiver,
                new IntentFilter(PushListenerService.ACTION_SNS_NOTIFICATION));
    }

    private final BroadcastReceiver notificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LOG_TAG, "Received notification from local broadcast. Display it in a dialog.");

            Bundle data = intent.getBundleExtra(PushListenerService.INTENT_SNS_NOTIFICATION_DATA);
            String message = PushListenerService.getMessage(data);

            new AlertDialog.Builder(StartUpActivity.this)
                    .setTitle(R.string.push_demo_title)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    };


    @Override
    public void onViewLocations() {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("main")
                .replace(R.id.container, AddViewContractsFragment.newInstance())
                .commit();
    }

    @Override
    public void onAddContract() {

                // convert iP address
//http://10.1.104.78:8080/contracts/0x184fc661f5ad3ccdfb0b9a3b0861057aff712b?account=0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c
//
                new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] params) {
                        new ApiClient("http://10.1.106.128:8080").userCall("0xbb297ff77c642fd73a8b21010d58eb8d6f12b51f", "0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c")
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
    public void onSuspend() {

    }

    @Override
    public void onMakeTransaction() {

    }

    @Override
    public void onAdd(Uri uri) {

    }
}
