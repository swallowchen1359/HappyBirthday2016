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
import com.studio.swallowcharchar.happybirthday2016.albumpage.view.AlbumView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {
    private static final int VIEW_RES_ID = R.layout.fragment_album;
    private static final int VIEW_ALBUM_RES_ID = R.id.album_view;

    private AlbumModel mModel;
    private AlbumView mAlbumView;

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
        mAlbumView = (AlbumView) mainView.findViewById(VIEW_ALBUM_RES_ID);
        if (mModel != null && mAlbumView != null) {
            mAlbumView.setAlbums(mModel.getAlbumPictureList());
        }
        return mainView;
    }

}
