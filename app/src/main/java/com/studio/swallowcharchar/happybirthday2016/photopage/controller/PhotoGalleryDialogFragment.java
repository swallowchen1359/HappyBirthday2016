package com.studio.swallowcharchar.happybirthday2016.photopage.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.database.Photo;
import com.studio.swallowcharchar.happybirthday2016.photopage.model.PhotoGalleryDialogModel;
import com.studio.swallowcharchar.happybirthday2016.photopage.view.PhotoGalleryDialogView;

import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Created by Swallow on 7/18/16.
 */
public class PhotoGalleryDialogFragment extends PhotoDialogFragment implements PhotoGalleryDialogModel.TaskCallbacks, PhotoGalleryDialogView.EventListener {

    private static final int STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID = R.string.bundle_album_clicked_position;

    private PhotoGalleryDialogModel mModel;
    private PhotoGalleryDialogView mPhotoGalleryDialogView;
    private int mAlbumOnClickPosition;
    private LinkedList<Integer> mPickedImageIndexLinkedList;
    
    public PhotoGalleryDialogFragment() {
        mPickedImageIndexLinkedList = new LinkedList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPhotoGalleryDialogView = (PhotoGalleryDialogView) super.onCreateView(inflater, container, savedInstanceState);
        /** Use bundleStr to get argument passed from AlbumActivity */
        String bundleStr = getResources().getString(STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID);
        mAlbumOnClickPosition = getArguments().getInt(bundleStr, 0);

        /** PhotoModel should initialize when onAttach */
        if (mModel != null && mPhotoGalleryDialogView != null) {
            mPhotoGalleryDialogView.setEditorMode(PhotoGalleryDialogView.MODE_EDITOR);
            mPhotoGalleryDialogView.setEventListener(this);
            /** After create and load done, callback will be awaken to do some jobs */
            mModel.createPhotoLoader();
        } else {
            Log.d("PhotoFragment", "Model or View is null");
        }
        return mPhotoGalleryDialogView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mModel = new PhotoGalleryDialogModel(activity);
        mModel.setTaskCallbacks(this);
        /** After model init done (loading Json file), callback onInitModelDone will be awaken to do some jobs */
        mModel.initModel();
    }

    @Override
    public void onBackgroundClick() {
        OnDialogFinishListener listener = getOnDialogFinishListener();
        if (listener != null) {
            listener.onDialogFinish(null);
        }
        super.onBackgroundClick();
    }

    /******************************* PhotoModel.TaskCallbacks ************************************/
    @Override
    public void onInitModelDone() {

    }

    @Override
    public void onLoadDone() {
        mModel.getAvailableBitmap();
    }

    @Override
    public void onBitmapCreateDone(Bitmap bitmap) {
        mPhotoGalleryDialogView.setPhoto(bitmap);
    }
    
    /*************************** PhotoGalleryDialoView.EventListener *****************************/
    @Override
    public void onImageClick(boolean picked, int index) {
        if (picked) {
            mPickedImageIndexLinkedList.add(index);
        } else {
            /** find and delete the index */
            if (mPickedImageIndexLinkedList.contains(index)) {
                int linkedListIndex = mPickedImageIndexLinkedList.indexOf(index);
                mPickedImageIndexLinkedList.remove(linkedListIndex);
            } else {
                /** the situation should not happen */
            }
        }
    }
    
    @Override
    public void onConfirmClick() {
        /** When confirm click, using PhotoModel to create a new photo object and save */
        if (mModel == null || true) {
            return;
        }
//        /** Photo LinkedList is used for PhotoFragment to update photoGallery */
//        ArrayList<Photo> photoLinkedList = new ArrayList<>();
//
//        Log.d("mPickedImageLL", "size " + mPickedImageIndexLinkedList.size());
//        for (int i = 0; i < mPickedImageIndexLinkedList.size(); i++) {
//            int index = mPickedImageIndexLinkedList.get(i);
//            Log.d("index", "index " + index);
//            Photo photo = mModel.getPhotoFromCursorByIndex(index);
//            photoLinkedList.add(photo);
//        }
//
//        OnDialogFinishListener listener = getOnDialogFinishListener();
//        if (listener != null) {
//            listener.onDialogFinish(photoLinkedList);
//        }
//        getActivity().onBackPressed();
    }
}
