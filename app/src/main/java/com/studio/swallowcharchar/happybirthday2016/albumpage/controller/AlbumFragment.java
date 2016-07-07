package com.studio.swallowcharchar.happybirthday2016.albumpage.controller;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.albumpage.AlbumActivity;
import com.studio.swallowcharchar.happybirthday2016.albumpage.model.AlbumModel;
import com.studio.swallowcharchar.happybirthday2016.albumpage.view.AlbumView;
import com.studio.swallowcharchar.happybirthday2016.photopage.PhotoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment implements AlbumView.OnCardClickListener {
    private static final int VIEW_RES_ID = R.layout.fragment_album;
    private static final int VIEW_ALBUM_RES_ID = R.id.album_view;

    private AlbumModel mModel;
    private AlbumView mAlbumView;

    public AlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("AlbumFragment", "onCreateView");
        View mainView = inflater.inflate(VIEW_RES_ID, container, false);
        mAlbumView = (AlbumView) mainView.findViewById(VIEW_ALBUM_RES_ID);
        if (mModel != null && mAlbumView != null) {
            /**
             * AlbumView.setAlbums(AlbumPictures, Titles, Descriptions)
             * */
            mAlbumView.setAlbums(mModel.getAlbumPictureList(), mModel.getAlbumTitleList(), mModel.getAlbumDescriptionList());
            mAlbumView.setOnCardClickListener(this);
        } else {
            Log.d("AlbumFragment", "Model or View is null");
        }
        return mainView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("AlbumFragment", "onAttach");
        mModel = new AlbumModel(activity);
        mModel.initModel();
    }

    @Override
    public void onCardClick(int index) {
        ((AlbumActivity) getActivity()).goActivity(PhotoActivity.class);
    }
}
