package com.studio.swallowcharchar.happybirthday2016.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.LinkedList;

/**
 * Created by Swallow on 6/19/16.
 */
public class SlideshowView extends HorizontalScrollView {

    private float mContainerHeight, mContainerWidth;
    private float mChildHeight, mChildWidth;
    private OverlappingView mContainer;
    private LinearLayout mLinearLayout;

    public SlideshowView(Context context) {
        this(context, null);
    }

    public SlideshowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        mContainerWidth = mContainerHeight = 0;
        mChildWidth = mChildHeight = 0;
        mContainer = new OverlappingView(getContext());
        mContainer.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
        addView(mContainer);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.d("SlideshowView", " width " + getMeasuredWidth() + " height " + getMeasuredHeight());
        mContainerWidth  = getMeasuredWidth();
        mContainerHeight = getMeasuredHeight();
    }

    public void addPicture(ImageView v) {
        int childCount;
        if (mContainer == null) {
            return;
        }
        childCount = mContainer.getChildCount();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        layoutParams.setMarginStart((int) (childCount * getResources().getDimension(OverlappingView.MARGIN_RES_ID)));
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        v.setLayoutParams(layoutParams);
        v.setRotationY(50);
        mContainer.addView(v);
    }

    public void addPicture(LinkedList<Integer> viewResIds) {
        if (viewResIds == null) {
            return;
        }
        for (int i = 0; i < viewResIds.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageBitmap(ImageUtility.decodeSampledBitmapFromResource(getResources(), viewResIds.get(i), 50, 50));
            addPicture(imageView);
        }
    }

    public void addPicture(int[] viewResIds) {
        if (viewResIds.length < 0) {
            return;
        }
        for (int i = 0; i < viewResIds.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageBitmap(ImageUtility.decodeSampledBitmapFromResource(getResources(), viewResIds[i], 50, 50));
            addPicture(imageView);
        }
    }

    private class OverlappingView extends RelativeLayout {
        private static final int MARGIN_RES_ID = R.dimen.slide_picture_left_margin;

        public OverlappingView(Context context) {
            this(context, null);
        }

        public OverlappingView(Context context, AttributeSet attrs) {
            super(context, attrs);
            setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
            setBackgroundColor(Color.WHITE);
        }

    }
}
