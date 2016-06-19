package com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.studio.swallowcharchar.happybirthday2016.MainTopView;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.BirthdayCardActivity;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.model.BirthdayCardModel;
import com.studio.swallowcharchar.happybirthday2016.widget.LinkedListPagerAdapter;

import java.util.LinkedList;

public class BirthdayCardTopView extends ViewPager implements MainTopView {

    private BirthdayCardModel mModel;
    public interface OnPagerClickListener {
        void onPagerClick(int position, View pagerView);
    }

    LinkedList<ImageView> mImageViewLL;
    OnPagerClickListener mOnPagerClickListener;
    public BirthdayCardTopView(Context context) {
        this(context, null);
    }

    public BirthdayCardTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mModel = new BirthdayCardModel();
        LinkedListPagerAdapter<ImageView> adapter;
        ImageView viewA, viewB;

        viewA = new ImageView(context);
        viewA.setBackgroundColor(Color.CYAN);
        viewB = new ImageView(context);
        viewB.setImageResource(mModel.getImageResId());

        mImageViewLL = new LinkedList<>();
        mImageViewLL.add(viewA);
        mImageViewLL.add(viewB);

        adapter = new LinkedListPagerAdapter<>(mImageViewLL);
        setAdapter(adapter);
        addOnPageChangeListener(new OnPageChangeListener());
    }

    public void setOnPagerClickListener(OnPagerClickListener listener) {
        mOnPagerClickListener = listener;
    }

    private class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        /** Init first View in ViewPager OnClickListener */
        OnPageChangeListener() {
            if (mImageViewLL != null && mImageViewLL.size() > 0) {
                mImageViewLL.get(0).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnPagerClickListener != null) {
                            mOnPagerClickListener.onPagerClick(0, mImageViewLL.get(0));
                        }
                    }
                });
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            final ImageView imageView;
            final int selectPosition = position;
            int leftPosition = position - 1;
            int rightPosition = position + 1;

            if (mImageViewLL == null) {
                return;
            }
            imageView = mImageViewLL.get(selectPosition);
            String sharedString = getResources().getString(BirthdayCardActivity.STRING_TRANSITION_IMAGE_SWALLOW_AND_PANDA_RES_ID);
            if (imageView != null) {
                imageView.setTransitionName(sharedString);
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnPagerClickListener != null) {
                            mOnPagerClickListener.onPagerClick(selectPosition, imageView);
                        }
                    }
                });
            }

            /** Clean left and right ImageView OnClickListener and transitionName to prevent error transition */
            if (leftPosition > 0) {
                View v = mImageViewLL.get(leftPosition);
                v.setTransitionName("");
                v.setOnClickListener(null);
            }

            if (rightPosition < mImageViewLL.size()) {
                View v = mImageViewLL.get(rightPosition);
                v.setTransitionName("");
                v.setOnClickListener(null);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
