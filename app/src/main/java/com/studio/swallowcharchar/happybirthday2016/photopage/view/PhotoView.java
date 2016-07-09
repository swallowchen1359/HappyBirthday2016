package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.LinkedList;

/**
 * Created by Swallow on 7/8/16.
 */
public class PhotoView extends ScrollView {

    private static final int VIEW_INTRO_RES_ID = R.id.photo_intro;
    private static final int VIEW_GALLERY_RES_ID = R.id.photo_gallery;

    private PhotoGallery mPhotoGallery;
    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPhotoGallery = (PhotoGallery) findViewById(VIEW_GALLERY_RES_ID);
    }

    public void setPhotos(LinkedList<Integer> resIds) {
        if (mPhotoGallery == null) {
            mPhotoGallery = (PhotoGallery) findViewById(VIEW_GALLERY_RES_ID);
        }
        mPhotoGallery.setGallery(resIds);
    }
}