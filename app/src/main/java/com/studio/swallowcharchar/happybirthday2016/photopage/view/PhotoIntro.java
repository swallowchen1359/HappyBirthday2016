package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studio.swallowcharchar.happybirthday2016.R;

/**
 * Created by Swallow on 7/8/16.
 */
public class PhotoIntro extends LinearLayout {
    public interface OnEditorClickListener {
        void onEditorClick(int mode);
        void onCoverClick(int mode);
        void onPlaceClick(int mode);
        void onTimeClick(int mode);
    }

    private static final int VIEW_COVER_RES_ID = R.id.photo_intro_cover;
    private static final int VIEW_TIME_RES_ID = R.id.photo_intro_time_text_view;
    private static final int VIEW_NUM_RES_ID = R.id.photo_intro_num_text_view;
    private static final int VIEW_PLACE_RES_ID = R.id.photo_intro_place_text_view;
    private static final int VIEW_EDITOR_RES_ID = R.id.photo_intro_editor_text_view;

    private ImageView mCoverImageView;
    private TextView mTimeTextView;
    private TextView mNumTextView;
    private TextView mPlaceTextView;
    private TextView mEditorTextView;

    private OnEditorClickListener mOnEditorClickListener;

    private AnimatorSet mEditorModeAnimatorSet;
    /**
     * Current mode will be MODE_NORMAL or MODE_EDITOR
     * */
    private int mCurrentMode;

    public PhotoIntro(Context context) {
        super(context);
    }

    public PhotoIntro(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoIntro(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mCoverImageView == null) {
            mCoverImageView = (ImageView) findViewById(VIEW_COVER_RES_ID);
        }
        if (mTimeTextView == null) {
            mTimeTextView = (TextView) findViewById(VIEW_TIME_RES_ID);
        }
        if (mNumTextView == null) {
            mNumTextView = (TextView) findViewById(VIEW_NUM_RES_ID);
        }
        if (mPlaceTextView == null) {
            mPlaceTextView = (TextView) findViewById(VIEW_PLACE_RES_ID);
        }
        if (mEditorTextView == null) {
            mEditorTextView = (TextView) findViewById(VIEW_EDITOR_RES_ID);
        }

        mEditorTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEditorClickListener != null) {
                    mOnEditorClickListener.onEditorClick(mCurrentMode);
                }
            }
        });

        mCoverImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEditorClickListener != null) {
                    mOnEditorClickListener.onCoverClick(mCurrentMode);
                }
            }
        });

        mPlaceTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEditorClickListener != null) {
                    mOnEditorClickListener.onPlaceClick(mCurrentMode);
                }
            }
        });

        mTimeTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEditorClickListener != null) {
                    mOnEditorClickListener.onTimeClick(mCurrentMode);
                }
            }
        });
    }

    public void setCoverImage(Bitmap bitmap) {
        mCoverImageView.setImageBitmap(bitmap);
    }

    public void setDate(int year, int month, int day) {
        mTimeTextView.setText(year + "/" + month + "/" + day);
    }
    
    public void setNum(int num) {
        mNumTextView.setText(num);
    }

    public void setOnEditorClickListener(OnEditorClickListener listener) {
        mOnEditorClickListener = listener;
    }

    public void enterEditorMode() {
        mCurrentMode = PhotoView.MODE_EDITOR;
        /** first time entering editor mode */
        if (mEditorModeAnimatorSet == null) {
            mEditorModeAnimatorSet = new AnimatorSet();
            registerEditorModeAnimator();
        }
        mEditorModeAnimatorSet.start();
    }

    public void exitEditorMode() {
        mCurrentMode = PhotoView.MODE_NORMAL;
        mEditorModeAnimatorSet.cancel();
    }

    private void registerEditorModeAnimator() {
        AnimatorSet coverAnimatorSet = new AnimatorSet();
        ValueAnimator coverAnimatorA = ObjectAnimator.ofFloat(mCoverImageView, "Rotation", -5f, 5f);
        ValueAnimator coverAnimatorB = ObjectAnimator.ofFloat(mCoverImageView, "Rotation", 5f, -5f);
        coverAnimatorSet.playSequentially(coverAnimatorA, coverAnimatorB);
        coverAnimatorSet.setDuration(300);

        AnimatorSet timeAnimatorSet = new AnimatorSet();
        ValueAnimator timeAnimatorA = ObjectAnimator.ofFloat(mTimeTextView, "Rotation", -5f, 5f);
        ValueAnimator timeAnimatorB = ObjectAnimator.ofFloat(mTimeTextView, "Rotation", 5f, -5f);
        timeAnimatorSet.playSequentially(timeAnimatorA, timeAnimatorB);
        timeAnimatorSet.setDuration(300);

        AnimatorSet numAnimatorSet = new AnimatorSet();
        ValueAnimator numAnimatorA = ObjectAnimator.ofFloat(mNumTextView, "Rotation", -5f, 5f);
        ValueAnimator numAnimatorB = ObjectAnimator.ofFloat(mNumTextView, "Rotation", 5f, -5f);
        numAnimatorSet.playSequentially(numAnimatorA, numAnimatorB);
        numAnimatorSet.setDuration(300);

        AnimatorSet placeAnimatorSet = new AnimatorSet();
        ValueAnimator placeAnimatorA = ObjectAnimator.ofFloat(mPlaceTextView, "Rotation", -5f, 5f);
        ValueAnimator placeAnimatorB = ObjectAnimator.ofFloat(mPlaceTextView, "Rotation", 5f, -5f);
        placeAnimatorSet.playSequentially(placeAnimatorA, placeAnimatorB);
        placeAnimatorSet.setDuration(300);

        AnimatorSet editorAnimatorSet = new AnimatorSet();
        ValueAnimator editorAnimatorA = ObjectAnimator.ofFloat(mEditorTextView, "Rotation", -5f, 5f);
        ValueAnimator editorAnimatorB = ObjectAnimator.ofFloat(mEditorTextView, "Rotation", 5f, -5f);
        editorAnimatorSet.playSequentially(editorAnimatorA, editorAnimatorB);
        editorAnimatorSet.setDuration(300);

        mEditorModeAnimatorSet.addListener(new ShakeAnimationListener());
        mEditorModeAnimatorSet.playTogether(coverAnimatorSet, timeAnimatorSet, numAnimatorSet, placeAnimatorSet, editorAnimatorSet);
    }

    private class ShakeAnimationListener implements Animator.AnimatorListener {

        private boolean mCanceled;
        @Override
        public void onAnimationStart(Animator animation) {
            mCanceled = false;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (!mCanceled) {
                animation.start();
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mCanceled = true;
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
