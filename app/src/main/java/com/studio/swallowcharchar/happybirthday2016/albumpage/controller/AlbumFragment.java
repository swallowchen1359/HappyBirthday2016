package com.studio.swallowcharchar.happybirthday2016.albumpage.controller;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.albumpage.model.AlbumModel;
import com.studio.swallowcharchar.happybirthday2016.albumpage.view.AlbumBotView;
import com.studio.swallowcharchar.happybirthday2016.albumpage.view.AlbumTopView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {
    private static final int VIEW_RES_ID = R.layout.fragment_album;

    private AlbumModel mModel;

    public AlbumFragment() {
        // Required empty public constructor
        mModel = new AlbumModel();
        mModel.initModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(VIEW_RES_ID, container, false);
        return mainView;
    }

}
