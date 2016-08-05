package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.swallowcharchar.happybirthday2016.widget.ImageUtility;

import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.LinkedList;
import java.util.Random;

public class AlbumCardView extends CardView {

    public interface OnTimeUpListener {
        void onTimeUp();
    }

    private static final int VIEW_TITLE_RES_ID = R.id.album_title;
    private static final int VIEW_IMAGE_RES_ID = R.id.album_image;
    private static final int VIEW_DESCRIPTION_RES_ID = R.id.album_description;
    private static final int VIEW_PLACE_RES_ID = R.id.album_place;
    private static final int VIEW_TIME_RES_ID = R.id.album_time;
    private static final int VIEW_TAGS_RES_ID = R.id.album_tags;
    private static final int DIMEN_RADIUS_RES_ID = R.dimen.album_card_radius;

    private static final int TIMER_BASE = 1000;
    /**
     * AlbumCardView contains an FrameLayout, which contains an ImageView inside
     */
    private ImageView mImageView;
    /**
     * AlbumCardView contains some TextViews for description
     */
    private TextView mTitleTextView, mDescriptionTextView, mPlaceTextView, mTimeTextView, mTagsTextView;

    /**
     * Radius of AlbumCardView
     * */
    private float mCardViewRadius;

    private OnTimeUpListener mOnTimeUpListener;

    private Runnable mTimerTask;

    public AlbumCardView(Context context) {
        this(context, null);
    }

    public AlbumCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlbumCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTimerTask = new Runnable() {
            @Override
            public void run() {
                if (mOnTimeUpListener != null) {
                    mOnTimeUpListener.onTimeUp();
                }
                AlbumCardView.this.postDelayed(mTimerTask, TIMER_BASE * (new Random().nextInt(3) + 5));
            }
        };
        /** Align and add the children here */
        mCardViewRadius = context.getResources().getDimension(DIMEN_RADIUS_RES_ID);
        setRadius(mCardViewRadius);
    }

    public void setAlbumImage(Bitmap bitmap) {
        if (mImageView == null) {
            mImageView = (ImageView) findViewById(VIEW_IMAGE_RES_ID);
        }
        mImageView.setImageBitmap(bitmap);
        this.postDelayed(mTimerTask, TIMER_BASE * (new Random().nextInt(3) + 5));
    }

    public void setAlbumImage(Bitmap bitmap, boolean animation) {
        if (mImageView == null) {
            mImageView = (ImageView) findViewById(VIEW_IMAGE_RES_ID);
        }
        if (animation) {
            ImageUtility.ImageViewAnimatedChange(getContext(), mImageView, bitmap);
        } else {
            setAlbumImage(bitmap);
        }
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

    public void setAlbumPlace(String string) {
        if (mPlaceTextView == null) {
            mPlaceTextView = (TextView) findViewById(VIEW_PLACE_RES_ID);
        }
        mPlaceTextView.setText(string);
    }

    public void setAlbumTime(LinkedList<Integer> time) {
        if (mTimeTextView == null) {
            mTimeTextView = (TextView) findViewById(VIEW_TIME_RES_ID);
        }

        if (time.size() != 3) {
            mTimeTextView.setText("1991/05/17");
            return;
        }

        int year, month, day;
        String timeStr, yearStr, monthStr, dayStr;

        year = time.get(0);
        month = time.get(1);
        day = time.get(2);

        yearStr = String.valueOf(year);
        if (month < 10) {
            monthStr = "0" +String.valueOf(month);
        } else {
            monthStr = String.valueOf(month);
        }
        if (day < 10) {
            dayStr = "0" +String.valueOf(day);
        } else {
            dayStr = String.valueOf(day);
        }
        timeStr = yearStr +"/"+ monthStr +"/"+ dayStr;
        mTimeTextView.setText(timeStr);
    }

    public void setAlbumTags(LinkedList<String> tags) {
        if (mTagsTextView == null) {
            mTagsTextView = (TextView) findViewById(VIEW_TAGS_RES_ID);
        }
        String tagsStr = "";
        for (int i = 0; i < tags.size(); i++) {
            tagsStr += "#" +tags.get(i)+ "  ";
        }
        mTagsTextView.setText(tagsStr);
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

    public TextView getAlbumPlace() {
        return mPlaceTextView;
    }

    public TextView getAlbumTime() {
        return mTimeTextView;
    }

    public TextView getAlbumTags() {
        return mTagsTextView;
    }

    public void setOnTimeUpListener(OnTimeUpListener listener) {
        mOnTimeUpListener = listener;
    }
}