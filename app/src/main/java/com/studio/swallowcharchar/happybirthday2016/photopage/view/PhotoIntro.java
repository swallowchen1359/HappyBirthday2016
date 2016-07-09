package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.studio.swallowcharchar.happybirthday2016.R;

/**
 * Created by Swallow on 7/8/16.
 */
public class PhotoIntro extends LinearLayout {
    private static final int VIEW_COVER_RES_ID = R.id.photo_intro_cover;

    private ImageView mCoverImageView;

    public PhotoIntro(Context context) {
        super(context);
    }

    public PhotoIntro(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoIntro(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCoverImage(Bitmap bitmap) {
        if (mCoverImageView == null) {
            mCoverImageView = (ImageView) findViewById(VIEW_COVER_RES_ID);
        }
        mCoverImageView.setImageBitmap(bitmap);
    }

    public void setCoverImage(int resId) {
        if (mCoverImageView == null) {
            mCoverImageView = (ImageView) findViewById(VIEW_COVER_RES_ID);
        }
        mCoverImageView.setImageResource(resId);
    }

}
