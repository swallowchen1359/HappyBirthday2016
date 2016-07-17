package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.LinkedList;

/**
 * Created by Swallow on 7/8/16.
 */
public class PhotoView extends ScrollView {

    public interface EventListener {
        void onEditorClick(int mode);
        void onCoverClick(int mode);
        void onPlaceClick(int mode);
        void onTimeClick(int mode);
    }

    public static final int MODE_NORMAL = 0x0;
    public static final int MODE_EDITOR = 0x1;

    private static final int VIEW_INTRO_RES_ID = R.id.photo_intro;
    private static final int VIEW_GALLERY_RES_ID = R.id.photo_gallery;
    private static final int VIEW_TAG_AREA_RES_ID = R.id.photo_tag_area;

    private PhotoIntro mPhotoIntro;
    private PhotoGallery mPhotoGallery;
    private PhotoTagArea mPhotoTagArea;

    private EventListener mEventListener;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mPhotoIntro == null) {
            mPhotoIntro = (PhotoIntro) findViewById(VIEW_INTRO_RES_ID);
        }

        if(mPhotoGallery == null) {
            mPhotoGallery = (PhotoGallery) findViewById(VIEW_GALLERY_RES_ID);
        }

        if (mPhotoTagArea == null) {
            mPhotoTagArea = (PhotoTagArea) findViewById(VIEW_TAG_AREA_RES_ID);
        }

        mPhotoIntro.setOnEditorClickListener(new PhotoIntro.OnEditorClickListener() {
            @Override
            public void onEditorClick(int mode) {
                if (mEventListener != null) {
                    mEventListener.onEditorClick(mode);
                }
            }

            @Override
            public void onCoverClick(int mode) {
                if (mEventListener != null) {
                    mEventListener.onCoverClick(mode);
                }
            }

            @Override
            public void onPlaceClick(int mode) {
                if (mEventListener != null) {
                    mEventListener.onPlaceClick(mode);
                }
            }

            @Override
            public void onTimeClick(int mode) {
                if (mEventListener != null) {
                    mEventListener.onTimeClick(mode);
                }
            }
        });
    }

    public void setEventListener(EventListener listener) {
        mEventListener = listener;
    }

    public void setCover(Bitmap bitmap) {
        mPhotoIntro.setCoverImage(bitmap);
    }

    public void setDate(int year, int month, int day) {
        mPhotoIntro.setDate(year, month, day);
    }

    public void setPhoto(Bitmap bitmap) {
        mPhotoGallery.setGallery(bitmap);
    }

    public void setTags(LinkedList<String> tagStrs) {
        mPhotoTagArea.setTags(tagStrs);
    }

    public void enterEditorMode() {
        mPhotoIntro.enterEditorMode();
    }

    public void exitEditorMode() {
        mPhotoIntro.exitEditorMode();
    }
}