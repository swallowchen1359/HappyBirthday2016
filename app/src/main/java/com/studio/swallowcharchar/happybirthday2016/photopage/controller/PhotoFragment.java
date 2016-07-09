package com.studio.swallowcharchar.happybirthday2016.photopage.controller;


import android.app.Activity;
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
public class PhotoFragment extends Fragment {
    private static final int VIEW_RES_ID = R.layout.fragment_photo;
    private static final int VIEW_ALBUM_RES_ID = R.id.photo_view;
    private static final int STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID = R.string.bundle_album_clicked_position;

    private PhotoView mPhotoView;
    private PhotoModel mModel;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /** Inflate the layout for this fragment */
        View mainView = inflater.inflate(VIEW_RES_ID, container, false);
        String bundleStr = getResources().getString(STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID);
        final int albumOnClickPosition = getArguments().getInt(bundleStr, 0);
        /** find PhotoView */
        mPhotoView = (PhotoView) mainView.findViewById(VIEW_ALBUM_RES_ID);
        if (mModel != null && mPhotoView != null) {
            /** PhotoView.setPhotos(PhotoPictures) */
            mPhotoView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPhotoView.setPhotos(mModel.getPhotoPictureList(albumOnClickPosition));
                }
            }, 600);
        } else {
            Log.d("PhotoFragment", "Model or View is null");
        }
        return mainView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mModel = new PhotoModel(activity);
        mModel.initModel();
    }
}
