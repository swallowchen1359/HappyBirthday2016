package com.studio.swallowcharchar.happybirthday2016.photopage.controller;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.photopage.model.PhotoModel;
import com.studio.swallowcharchar.happybirthday2016.photopage.view.PhotoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment implements PhotoModel.TaskCallbacks {
    private static final int VIEW_RES_ID = R.layout.fragment_photo;
    private static final int VIEW_ALBUM_RES_ID = R.id.photo_view;
    private static final int STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID = R.string.bundle_album_clicked_position;

    private static final int URL_LOADER = 0;

    private PhotoView mPhotoView;
    private PhotoModel mModel;

    private int mAlbumOnClickPosition;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /** Inflate the layout for this fragment */
        View mainView = inflater.inflate(VIEW_RES_ID, container, false);
        /** Use bundleStr to get argument passed from AlbumActivity */
        String bundleStr = getResources().getString(STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID);
        mAlbumOnClickPosition = getArguments().getInt(bundleStr, 0);

        /** find PhotoView */
        mPhotoView = (PhotoView) mainView.findViewById(VIEW_ALBUM_RES_ID);
        /** PhotoModel should initialize when onAttach */
        if (mModel != null && mPhotoView != null) {
            /** After create and load done, callback will be awaken to do some jobs */
            mModel.createPhotoLoader();
        } else {
            Log.d("PhotoFragment", "Model or View is null");
        }
        return mainView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mModel = new PhotoModel(activity);
        mModel.setTaskCallbacks(this);
        /** After model init done (loading Json file), callback onInitModelDone will be awaken to do some jobs */
        mModel.initModel();
    }

    /******************************* PhotoModel.TaskCallbacks ************************************/
    @Override
    public void onInitModelDone() {

    }

    @Override
    public void onLoadDone() {
        mPhotoView.setTags(mModel.getPhotoTagList(mAlbumOnClickPosition));
        mPhotoView.setCover(mModel.getPhotoCoverBitmap(mAlbumOnClickPosition));
        /**
         * Trigger PhotoView to set photo after cover setting. The model will keep returning
         * bitmap by callback function onBitmapCreateDone
         * */
        mModel.getPhotoPictureBitmap(mAlbumOnClickPosition);
    }

    @Override
    public void onBitmapCreateDone(Bitmap bitmap) {
        mPhotoView.setPhoto(bitmap);
    }
}
