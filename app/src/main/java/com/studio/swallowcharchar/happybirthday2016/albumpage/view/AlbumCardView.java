package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.swallowcharchar.happybirthday2016.widget.ImageUtility;

import com.studio.swallowcharchar.happybirthday2016.R;

public class AlbumCardView extends CardView {

    private static final int VIEW_RES_ID = R.layout.view_card_parent;
    private static final int VIEW_TITLE_RES_ID = R.id.album_title;
    private static final int VIEW_IMAGE_RES_ID = R.id.album_image;
    private static final int VIEW_DESCRIPTION_RES_ID = R.id.album_description;
    private static final int DIMEN_RADIUS_RES_ID = R.dimen.album_card_radius;
    /**
     * These constant integer is used for AlbumAdapter and AlbumView to figure out which content
     * should be put into the corresponding views.
     * */
    public static final int KEY_IMG_RES_ID = 0x01;
    public static final int KEY_TITLE = 0x02;
    public static final int KEY_DESCRIPTION = 0x03;
    /**
     *
     * */
    private View mParentView;
    /**
     * AlbumCardView contains an FrameLayout, which contains an ImageView inside
     */
    private FrameLayout mImageFrameLayout;
    private ImageView mImageView;
    /**
     * AlbumCardView contains some TextViews for description
     */
    private TextView mTitleTextView, mDescriptionTextView;

    /**
     * Radius of AlbumCardView
     * */
    private float mCardViewRadius;

    public AlbumCardView(Context context) {
        this(context, null);
    }

    public AlbumCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /** Align and add the children here */
        mParentView = (((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(VIEW_RES_ID, null));
        mImageView = (ImageView) mParentView.findViewById(VIEW_IMAGE_RES_ID);
        mTitleTextView = (TextView) mParentView.findViewById(VIEW_TITLE_RES_ID);
        mDescriptionTextView = (TextView) mParentView.findViewById(VIEW_DESCRIPTION_RES_ID);
        mCardViewRadius = context.getResources().getDimension(DIMEN_RADIUS_RES_ID);
        setRadius(mCardViewRadius);
        addView(mParentView);
    }

    /**
     * set Album Image with resId
     */
    public void setAlbumImage(int resId) {
        /** TODO: remember to use ImageUtility to resize the photo */
        mImageView.setImageBitmap(ImageUtility.decodeSampledBitmapFromResource(getResources(), resId, 1080, 607));
/*
        mImageView.setImageResource(resId);
*/
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