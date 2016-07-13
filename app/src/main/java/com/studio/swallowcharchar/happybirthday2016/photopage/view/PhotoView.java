package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.LinkedList;

/**
 * Created by Swallow on 7/8/16.
 */
public class PhotoView extends ScrollView {

    private static final int VIEW_INTRO_RES_ID = R.id.photo_intro;
    private static final int VIEW_GALLERY_RES_ID = R.id.photo_gallery;
    private static final int VIEW_TAG_AREA_RES_ID = R.id.photo_tag_area;

    private PhotoIntro mPhotoIntro;
    private PhotoGallery mPhotoGallery;
    private PhotoTagArea mPhotoTagArea;

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

    public void setCover(int resId) {
        if (mPhotoIntro == null) {
            mPhotoIntro = (PhotoIntro) findViewById(VIEW_INTRO_RES_ID);
        }
        mPhotoIntro.setCoverImage(resId);
    }

    public void setCover(Bitmap bitmap) {
        if (mPhotoIntro == null) {
            mPhotoIntro = (PhotoIntro) findViewById(VIEW_INTRO_RES_ID);
        }
        mPhotoIntro.setCoverImage(bitmap);
    }

    public void setPhoto(Bitmap bitmap) {
        if (mPhotoGallery == null) {
            mPhotoGallery = (PhotoGallery) findViewById(VIEW_GALLERY_RES_ID);
        }
        mPhotoGallery.setGallery(bitmap);
    }

    public void setPhotos(LinkedList<Bitmap> bitmapLinkedList) {
        if (mPhotoGallery == null) {
            mPhotoGallery = (PhotoGallery) findViewById(VIEW_GALLERY_RES_ID);
        }
        mPhotoGallery.setGallery(bitmapLinkedList);
    }

    public void setTags(LinkedList<String> tagStrs) {
        if (mPhotoTagArea == null) {
            mPhotoTagArea = (PhotoTagArea) findViewById(VIEW_TAG_AREA_RES_ID);
        }
        mPhotoTagArea.setTags(tagStrs);
    }
}