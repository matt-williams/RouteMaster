package com.github.williams.matt.routemaster.ui.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.amazonaws.AmazonClientException;
import com.github.williams.matt.routemaster.R;
import com.mobile.AWSMobileClient;
import com.mobile.push.PushManager;
import com.mobile.push.SnsTopic;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartUpFragment extends Fragment {

    private StartUpListener mListener;
    private static final String LOG_TAG = "PUSH";

    @BindView(R.id.checkBox)
    CheckBox enablePushCheckBox;

    @BindView(R.id.btnMaketransaction)
    Button btnTransactions;

    private ArrayAdapter<SnsTopic> topicsAdapter;
    private static final int REQUEST_ENABLE_BT = 22;

    private PushManager pushManager;

    public StartUpFragment() {
        // Required empty public constructor
    }

    public static StartUpFragment newInstance() {
        StartUpFragment fragment = new StartUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private BluetoothAdapter mBluetoothAdapter;

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }

        IntentFilter filter = new IntentFilter(BluetoothDevice.EXTRA_DEVICE);
        filter.addAction(BluetoothDevice.ACTION_FOUND);

        getActivity().registerReceiver(mReceiver, filter);

        mBluetoothAdapter.startDiscovery();
        if (mBluetoothAdapter.isDiscovering()) {
            Toast.makeText(getActivity(), "Discovering", Toast.LENGTH_SHORT).show();
        } else {
            int scanMode = mBluetoothAdapter.getScanMode();
            if (scanMode == BluetoothAdapter.SCAN_MODE_NONE)
            {

            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pushManager = AWSMobileClient.defaultMobileClient().getPushManager();
        ButterKnife.bind(this, getActivity());

        enablePushCheckBox.setChecked(pushManager.isPushEnabled());
        enablePushCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleNotification(enablePushCheckBox.isChecked());
            }
        });

        for (SnsTopic topic : pushManager.getTopics().values()) {
            setSubscription(topic);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof StartUpListener) {
            mListener = (StartUpListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement StartUpListener");
        }
    }


    private void toggleNotification(final boolean enabled) {
        final ProgressDialog dialog = showWaitingDialog(
                R.string.push_demo_wait_message_update_notification);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(final Void... params) {
                // register device first to ensure we have a push endpoint.
                pushManager.registerDevice();

                // if registration succeeded.
                if (pushManager.isRegistered()) {
                    try {
                        pushManager.setPushEnabled(enabled);
                        // Automatically subscribe to the default SNS topic
                        if (enabled) {
                            pushManager.subscribeToTopic(pushManager.getDefaultTopic());
                        }
                        return null;
                    } catch (final AmazonClientException ace) {
                        Log.e("PUSH", "Failed to change push notification status", ace);
                        return ace.getMessage();
                    }
                }
                return "Failed to register for push notifications.";
            }

            @Override
            protected void onPostExecute(final String errorMessage) {
                dialog.dismiss();
                enablePushCheckBox.setChecked(pushManager.isPushEnabled());

                if (errorMessage != null) {
                    showErrorMessage(R.string.push_demo_error_message_update_notification,
                            errorMessage);
                }
            }
        }.execute();
    }

    private void setSubscription(final SnsTopic snsTopic) {


        final ProgressDialog dialog = showWaitingDialog(
                R.string.push_demo_wait_message_update_subscription);

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(final Void... params) {
                try {
                    if (snsTopic.isSubscribed()) {
                        pushManager.unsubscribeFromTopic(snsTopic);
                    } else {
                        pushManager.subscribeToTopic(snsTopic);
                    }
                    return null;
                } catch (final AmazonClientException ace) {
                    Log.e(LOG_TAG, "Error occurred during subscription", ace);
                    return ace.getMessage();
                }
            }

            @Override
            protected void onPostExecute(final String errorMessage) {
                dialog.dismiss();

                if (errorMessage != null) {
                    showErrorMessage(R.string.push_demo_error_message_update_subscription,
                            errorMessage);
                }
            }
        }.execute();
    }

    private AlertDialog showErrorMessage(final int resId, final Object... args) {
        return new AlertDialog.Builder(getActivity()).setMessage(getString(resId, (Object[]) args))
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private ProgressDialog showWaitingDialog(final int resId, final Object... args) {
        return ProgressDialog.show(getActivity(),
                getString(R.string.push_demo_progress_dialog_title),
                getString(resId, (Object[]) args));
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface StartUpListener {
        void onViewLocations();

        void onAddContract();

        void onSuspend();

        void onMakeTransaction();

    }
}
