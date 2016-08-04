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
import com.studio.swallowcharchar.happybirthday2016.database.Photo;
import com.studio.swallowcharchar.happybirthday2016.photopage.PhotoActivity;
import com.studio.swallowcharchar.happybirthday2016.photopage.model.PhotoModel;
import com.studio.swallowcharchar.happybirthday2016.photopage.view.PhotoDialogView;
import com.studio.swallowcharchar.happybirthday2016.photopage.view.PhotoView;
import com.studio.swallowcharchar.happybirthday2016.widget.DialogFragment;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment implements PhotoModel.TaskCallbacks, PhotoView.EventListener {
    private static final int VIEW_RES_ID = R.layout.fragment_photo;
    private static final int VIEW_ALBUM_RES_ID = R.id.photo_view;
    private static final int STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID = R.string.bundle_album_clicked_position;

    private PhotoView mPhotoView;
    private PhotoModel mModel;

    private int mAlbumOnClickPosition;

    /**
     * the parameter mCurrentMode is used to identify current mode of PhotoView
     * MODE_EDITOR or MODE_NORMAL
     * */
    private int mCurrentMode;

    public PhotoFragment() {
        // Required empty public constructor
        mCurrentMode = PhotoView.MODE_NORMAL;
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
            /** register event handler here */
            mPhotoView.setEventListener(this);
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

    public int getCurrentMode() {
        return mCurrentMode;
    }

    /******************************* PhotoModel.TaskCallbacks ************************************/
    @Override
    public void onInitModelDone() {

    }

    @Override
    public void onLoadDone() {
        mPhotoView.setTags(mModel.getPhotoTagList(mAlbumOnClickPosition));
        mPhotoView.setCover(mModel.getPhotoCoverBitmap(mAlbumOnClickPosition));
        mPhotoView.setNum(mModel.getPhotoCount(mAlbumOnClickPosition));
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

    /******************************* PhotoView.EventListener **************************************/
    @Override
    public void onEditorClick(int mode) {
        if (mode == PhotoView.MODE_EDITOR) {
            mPhotoView.exitEditorMode();
        } else {
            mPhotoView.enterEditorMode();
        }
        mCurrentMode = mode;
    }

    @Override
    public void onCoverClick(int mode) {
        /** PhotoTimeDialogFragment is used only for cover */
        PhotoCoverDialogFragment photoCoverDialogFragment = null;
        if (mode == PhotoView.MODE_EDITOR) {
            ((PhotoActivity) getActivity()).attachDialogFragment(PhotoDialogView.IDX_STYLE_CHG_COVER);
        }

        /**
         * The call back function for handling dialog result.
         * Because the call back is dialog only and dialog is activity keeping and handling,
         * so call back implement here, rather than fragment
         * */
        if (photoCoverDialogFragment != null) {
            photoCoverDialogFragment.setOnDialogFinishListener(new DialogFragment.OnDialogFinishListener() {
                @Override
                public void onDialogFinish(Object obj) {
                }
            });
        }
    }

    @Override
    public void onPlaceClick(int mode) {
        /** PhotoTimeDialogFragment is used only for place */
        PhotoPlaceDialogFragment photoPlaceDialogFragment = null;
        if (mode == PhotoView.MODE_EDITOR) {
            ((PhotoActivity) getActivity()).attachDialogFragment(PhotoDialogView.IDX_STYLE_CHG_PLACE);
        }

        /**
         * The call back function for handling dialog result.
         * Because the call back is dialog only and dialog is activity keeping and handling,
         * so call back implement here, rather than fragment
         * */
        if (photoPlaceDialogFragment != null) {
            photoPlaceDialogFragment.setOnDialogFinishListener(new DialogFragment.OnDialogFinishListener() {
                @Override
                public void onDialogFinish(Object obj) {
                }
            });
        }
    }

    @Override
    public void onTimeClick(int mode) {
        /** PhotoTimeDialogFragment is used only for time */
        PhotoTimeDialogFragment photoTimeDialogFragment = null;
        if (mode == PhotoView.MODE_EDITOR) {
            photoTimeDialogFragment = (PhotoTimeDialogFragment) ((PhotoActivity) getActivity()).attachDialogFragment(PhotoDialogView.IDX_STYLE_CHG_TIME);
        }

        /**
         * The call back function for handling dialog result.
         * Because the call back is dialog only and dialog is activity keeping and handling,
         * so call back implement here, rather than fragment
         * */
        if (photoTimeDialogFragment != null) {
            photoTimeDialogFragment.setOnDialogFinishListener(new DialogFragment.OnDialogFinishListener() {
                @Override
                public void onDialogFinish(Object obj) {
                    PhotoTimeDialogFragment.PickedDate pickedDate = (PhotoTimeDialogFragment.PickedDate) obj;
                    mPhotoView.setDate(pickedDate.getYear(), pickedDate.getMonth(), pickedDate.getDay());
                }
            });
        }
    }

    @Override
    public void onAddPhotoClick(int mode) {
        /** PhotoTimeDialogFragment is used only for time */
        PhotoGalleryDialogFragment photoGalleryDialogFragment = null;
        if (mode == PhotoView.MODE_EDITOR) {
            photoGalleryDialogFragment = (PhotoGalleryDialogFragment) ((PhotoActivity) getActivity()).attachDialogFragment(PhotoDialogView.IDX_STYLE_CHG_GALLERY);
        }

        /**
         * The call back function for handling dialog result.
         * Because the call back is dialog only and dialog is activity keeping and handling,
         * so call back implement here, rather than fragment
         * */
        if (photoGalleryDialogFragment != null) {
            photoGalleryDialogFragment.setOnDialogFinishListener(new DialogFragment.OnDialogFinishListener() {
                @Override
                public void onDialogFinish(Object obj) {
                    /** Get the picked photo from PhotoGalleryDialogFragment */
                    ArrayList<Photo> photoArrayList = (ArrayList<Photo>) obj;
                    /** Save the picked image into Database by model */
                    mModel.addPhotoToDatabase(photoArrayList);
                    /** Re-init model (reload database) to reload the images */
                    mModel.initModel();
                }
            });
        }
    }
}
