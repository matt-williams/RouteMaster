package com.github.williams.matt.routemaster.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.williams.matt.routemaster.R;

public class PauseFragment extends Fragment {

    private PauseListener mListener;

    public PauseFragment() {
        // Required empty public constructor
    }

    public static PauseFragment newInstance() {
        PauseFragment fragment = new PauseFragment();

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
        return inflater.inflate(R.layout.fragment_pause, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PauseListener) {
            mListener = (PauseListener) context;
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

    public interface PauseListener {
        void onPause();
    }
}
