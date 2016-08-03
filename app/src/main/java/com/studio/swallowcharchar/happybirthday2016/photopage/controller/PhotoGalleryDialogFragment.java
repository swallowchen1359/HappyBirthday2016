package com.studio.swallowcharchar.happybirthday2016.photopage.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.photopage.model.PhotoModel;
import com.studio.swallowcharchar.happybirthday2016.photopage.view.PhotoGalleryDialogView;

/**
 * Created by Swallow on 7/18/16.
 */
public class PhotoGalleryDialogFragment extends PhotoDialogFragment implements PhotoModel.TaskCallbacks, PhotoGalleryDialogView.EventListener {

    private static final int STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID = R.string.bundle_album_clicked_position;

    private PhotoModel mModel;
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
        mModel = new PhotoModel(activity);
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
        
    }
}
