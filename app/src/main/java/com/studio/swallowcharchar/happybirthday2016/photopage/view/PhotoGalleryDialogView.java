package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.studio.swallowcharchar.happybirthday2016.R;

/**
 * Created by Swallow on 7/19/16.
 */
public class PhotoGalleryDialogView extends PhotoDialogView {

    private static final int VIEW_GALLERY_RES_ID = R.id.photo_dialog_gallery;

    private PhotoGallery mPhotoGallery;

    public PhotoGalleryDialogView(Context context) {
        this(context, null);
    }

    public PhotoGalleryDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mPhotoGallery == null) {
            mPhotoGallery = (PhotoGallery) findViewById(VIEW_GALLERY_RES_ID);
        }
    }

    public void setPhoto(Bitmap bitmap) {
        mPhotoGallery.setGallery(bitmap);
    }
}
