package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumCardView extends CardView {

    /**
     * AlbumCardView contains an ImageView
     */
    private ImageView mImageView;
    /**
     * AlbumCardView contains some TextViews for description
     */
    private TextView mTitleTextView, mDescriptionTextView;

    public AlbumCardView(Context context) {
        this(context, null);
    }

    public AlbumCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /** Align and add the children here */
        mImageView = new ImageView(context);
        mTitleTextView = new TextView(context);
        mDescriptionTextView = new TextView(context);
        mTitleTextView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(mTitleTextView);
    }

    /**
     * set Album Image with resId
     */
    public void setAlbumImage(int resId) {
        /** TODO: remember to use ImageUtility to resize the photo */
    }

    /**
     * set Album Title with string with limit length
     */
    public void setAlbumTitle(String string) {
        /** TODO: remember to limit the string length */
        mTitleTextView.setText(string);
    }

    /**
     * set Album Description with string with limit length
     */
    public void setAlbumDescription(String string) {
        /** TODO: remember to limit the string length */
        mDescriptionTextView.setText(string);
    }
}