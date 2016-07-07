package com.studio.swallowcharchar.happybirthday2016.photopage.controller;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment {
    private static final int VIEW_RES_ID = R.layout.fragment_photo;

    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(VIEW_RES_ID, container, false);
    }

}
