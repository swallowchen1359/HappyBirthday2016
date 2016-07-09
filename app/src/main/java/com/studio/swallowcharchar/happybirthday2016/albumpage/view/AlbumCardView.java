package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.support.v7.widget.CardView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.swallowcharchar.happybirthday2016.widget.ImageUtility;

import com.studio.swallowcharchar.happybirthday2016.R;

public class AlbumCardView extends CardView {

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
     * AlbumCardView contains an FrameLayout, which contains an ImageView inside
     */
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
        this(context, attrs, 0);
    }

    public AlbumCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /** Align and add the children here */
        mImageView = (ImageView) findViewById(VIEW_IMAGE_RES_ID);
        mTitleTextView = (TextView) findViewById(VIEW_TITLE_RES_ID);
        mDescriptionTextView = (TextView) findViewById(VIEW_DESCRIPTION_RES_ID);
        mCardViewRadius = context.getResources().getDimension(DIMEN_RADIUS_RES_ID);
        setRadius(mCardViewRadius);
    }

    /**
     * set Album Image with resId
     */
    public void setAlbumImage(int resId) {
        if (mImageView == null) {
            mImageView = (ImageView) findViewById(VIEW_IMAGE_RES_ID);
        }
        Bitmap bitmap = ImageUtility.decodeSampledBitmapFromResource(getResources(), resId, 900, 900);
        mImageView.setImageBitmap(bitmap);
/*
        mImageView.setImageResource(resId);
*/
    }

    public void setAlbumImage(Bitmap bitmap) {
        if (mImageView == null) {
            mImageView = (ImageView) findViewById(VIEW_IMAGE_RES_ID);
        }
        mImageView.setImageBitmap(bitmap);
    }

    /**
     * set Album Title with string with limit length
     */
    public void setAlbumTitle(String string) {
        /** TODO: remember to limit the string length */
        if (mTitleTextView == null) {
            mTitleTextView = (TextView) findViewById(VIEW_TITLE_RES_ID);
        }
        mTitleTextView.setText(string);
    }

    /**
     * set Album Description with string with limit length
     */
    public void setAlbumDescription(String string) {
        /** TODO: remember to limit the string length */
        if (mDescriptionTextView == null) {
            mDescriptionTextView = (TextView) findViewById(VIEW_DESCRIPTION_RES_ID);
        }
        mDescriptionTextView.setText(string);
    }

    public ImageView getAlbumImage() {
        return mImageView;
    }

    public TextView getAlbumTitle() {
        return mTitleTextView;
    }

    public TextView getAlbumDescription() {
        return mDescriptionTextView;
    }
}