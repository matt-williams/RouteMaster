package com.github.williams.matt.routemaster.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.williams.matt.routemaster.R;


public class AddBeaconsFragment extends Fragment {


    private BeaconFragmentListener mListener;

    public AddBeaconsFragment() {
        // Required empty public constructor
    }


    public static AddBeaconsFragment newInstance(String param1, String param2) {
        AddBeaconsFragment fragment = new AddBeaconsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_add_beacons, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BeaconFragmentListener) {
            mListener = (BeaconFragmentListener) context;
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


    public interface BeaconFragmentListener {

        void onAdd(Uri uri);
    }
}
