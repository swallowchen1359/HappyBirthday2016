package com.studio.swallowcharchar.happybirthday2016.albumpage.controller;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.albumpage.AlbumActivity;
import com.studio.swallowcharchar.happybirthday2016.albumpage.model.AlbumModel;
import com.studio.swallowcharchar.happybirthday2016.albumpage.view.AlbumView;
import com.studio.swallowcharchar.happybirthday2016.photopage.PhotoActivity;
import com.studio.swallowcharchar.happybirthday2016.widget.ImageUtility;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment implements AlbumModel.TaskCallbacks, AlbumView.OnCardClickListener, AlbumView.OnCardChangeListener {
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
            mAlbumView.setOnCardClickListener(this);
            mAlbumView.setOnCardChangeListener(this);
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
        mModel.setTaskCallbacks(this);
        mModel.initModel();
        Log.d("AlbumFragment", "getImageRotation Test " + ImageUtility.getImageRotation(getResources(), 0));
    }

    @Override
    public void onCardImageClick(int position, ImageView sharedImageView) {
        ((AlbumActivity) getActivity()).goActivity(PhotoActivity.class, sharedImageView, position);
    }

    /******************************* PhotoModel.TaskCallbacks ************************************/
    @Override
    public void onInitModelDone() {
        /**
         * After AlbumModel init model done, get album picture bitmaps, after bitmap ready,
         * onBitmapCreateDone will be called
         * */
        mModel.getAlbumPictureBitmaps();
    }

    @Override
    public void onLoadDone() {

    }

    @Override
    public void onBitmapCreateDone(AlbumModel.BitmapWithIndex bitmapWithIndex) {
        Bitmap bitmap = bitmapWithIndex.getBitmap();
        int index = bitmapWithIndex.getIndex();
        int type = bitmapWithIndex.getType();
        /**
         * index < 0, for onCardChangeUsing
         * */
        if (type == AlbumModel.TYPE_CHG) {
            mAlbumView.changeAlbumImage(index, bitmap);
        } else {
            Log.d("AlbumFragment", "onBitmapCreateDone " + index);
            mAlbumView.setAlbum(index, bitmap, mModel.getAlbumTitle(index), mModel.getAlbumDescription(index),
                    mModel.getAlbumPhotos(index), mModel.getAlbumPlace(index),
                    mModel.getAlbumTime(index), mModel.getAlbumTags(index));
        }
    }

    @Override
    public void onAllBitmapCreateDone() {

    }

    @Override
    public void onCardChange(int index) {
        mModel.getAlbumRandomPhotoBitmap(index);
    }
}
