package com.studio.swallowcharchar.happybirthday2016.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.LinkedList;

/**
 * Created by swallow on 2015/9/22.
 */
public class Infinite3View extends RelativeLayout {

    public static final String TAG = Infinite3View.class.getSimpleName();

    public interface OnPageChangedListener {
        void onPageScrolled();
        void onPageScrollStop(boolean isOrderChanged, int direction);
        void onPageChanged(int direction);
    }

    public static final int LEFT_VIEW_INDEX = 0;
    public static final int CENTER_VIEW_INDEX = 1;
    public static final int RIGHT_VIEW_INDEX = 2;
    public static final int MOVE_TO_RIGHT = 1;
    public static final int MOVE_TO_LEFT = -1;

    private static final int MIN_FLING_VELOCITY = 400; // dips
    private static final int VIEW_NUMBER = 3;
    private static final int DIMEN_MARGIN_RES_ID = R.dimen.three_view_margin;

    private Context mContext;
    private LayoutParams mDefaultItemLayoutParams;
    private int mThreeViewWidth;
    private int mOneViewWidth;
    private int mMarginBetweenViews;
    private LinkedList<ViewGroup> mThreeViewLL;
    private OnPageChangedListener mOnPageChangedListener;
    private float mOnInitialTouch;
    private float mOnTouchX;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private VelocityTracker mVelocityTracker;
    private int mActivePointerId = -1;

    public Infinite3View(Context context) {
        this(context, null);
    }

    public Infinite3View(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutParams defaultLayoutParams;
        LayoutParams defaultContainerParams;
        LinearLayout threeViewContainer;
        DisplayMetrics metrics = new DisplayMetrics();

        mMarginBetweenViews = (int) getResources().getDimension(DIMEN_MARGIN_RES_ID);
        ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        mOneViewWidth = (metrics.widthPixels) - mMarginBetweenViews;
        mThreeViewWidth = mOneViewWidth * VIEW_NUMBER ;

        defaultLayoutParams = new LayoutParams(mThreeViewWidth, LayoutParams.MATCH_PARENT);
        setLayoutParams(defaultLayoutParams);

        threeViewContainer = new LinearLayout(mContext);
        defaultContainerParams = new LayoutParams(mThreeViewWidth, LayoutParams.MATCH_PARENT);
        defaultContainerParams.setMargins(-1 * (mOneViewWidth - (int) (mMarginBetweenViews * 0.5)), 0, 0, 0);
        threeViewContainer.setLayoutParams(defaultContainerParams);
        addView(threeViewContainer);

        mThreeViewLL = new LinkedList<>();

        for (int i = 0; i < VIEW_NUMBER; i++) {
            RelativeLayout viewGroupItem = new RelativeLayout(mContext);
            viewGroupItem.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
            viewGroupItem.setBackgroundColor(Color.WHITE);
            mThreeViewLL.add(viewGroupItem);
            threeViewContainer.addView(viewGroupItem);
        }

        mDefaultItemLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        setOnPageChangedListener(new OnPageChangedListener() {
            @Override
            public void onPageScrolled() {
            }

            @Override
            public void onPageScrollStop(boolean isOrderChanged, int direction) {

            }

            @Override
            public void onPageChanged(int direction) {
            }
        });

        final float density = mContext.getResources().getDisplayMetrics().density;
        mMinimumVelocity = (int) (MIN_FLING_VELOCITY * density);
        final ViewConfiguration configuration = ViewConfiguration.get(mContext);
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }

    public void setCurrentItem(int index) {
        if (index != 0 && index != 2)
            return;
        AnimatorSet set = new AnimatorSet();
        ValueAnimator animatorLeft, animatorCenter, animatorRight;
        int moveToLeftOrRight;
        if (index == LEFT_VIEW_INDEX) {
            moveToLeftOrRight = MOVE_TO_RIGHT;
            animatorLeft = ObjectAnimator.ofFloat(mThreeViewLL.get(LEFT_VIEW_INDEX), "X", mThreeViewLL.get(LEFT_VIEW_INDEX).getX(), moveToLeftOrRight * mOneViewWidth);
            animatorRight = ObjectAnimator.ofFloat(mThreeViewLL.get(RIGHT_VIEW_INDEX), "X", mThreeViewLL.get(RIGHT_VIEW_INDEX).getX(), 0);
            mThreeViewLL.get(RIGHT_VIEW_INDEX).setVisibility(INVISIBLE);
        } else {
            moveToLeftOrRight = MOVE_TO_LEFT;
            animatorLeft = ObjectAnimator.ofFloat(mThreeViewLL.get(LEFT_VIEW_INDEX), "X", mThreeViewLL.get(LEFT_VIEW_INDEX).getX(), -2 * moveToLeftOrRight * mOneViewWidth);
            animatorRight = ObjectAnimator.ofFloat(mThreeViewLL.get(RIGHT_VIEW_INDEX), "X", mThreeViewLL.get(RIGHT_VIEW_INDEX).getX(), -1 * moveToLeftOrRight * mOneViewWidth);
            mThreeViewLL.get(LEFT_VIEW_INDEX).setVisibility(INVISIBLE);
        }
        animatorCenter = ObjectAnimator.ofFloat(mThreeViewLL.get(CENTER_VIEW_INDEX), "X", mThreeViewLL.get(CENTER_VIEW_INDEX).getX(), (1 + moveToLeftOrRight) * mOneViewWidth);
        set.play(animatorCenter).with(animatorLeft).with(animatorRight);
        set.setDuration(50);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.addListener(new ThreeViewAnimatorListener(moveToLeftOrRight));
        set.start();
    }

    public View getCurrentItem() {
        if (mThreeViewLL.get(CENTER_VIEW_INDEX).getChildCount() > 0)
            return mThreeViewLL.get(CENTER_VIEW_INDEX).getChildAt(0);
        return null;
    }

    public void setOnPageChangedListener(OnPageChangedListener listener) {
        mOnPageChangedListener = listener;
    }

    public void destroyItem(int index) {
        mThreeViewLL.get(index).removeAllViews();
    }

    public void refreshItem(int index, View v) {
        mThreeViewLL.get(index).removeAllViews();

        if (v != null) {
            v.setLayoutParams(mDefaultItemLayoutParams);
            mThreeViewLL.get(index).addView(v);
        }
    }

    public int getOneViewWidth() {
        return mOneViewWidth;
    }

    private void refreshLinkedListOrder(int moveToLeftOrRight) {
        ViewGroup oriLeft = mThreeViewLL.get(LEFT_VIEW_INDEX);
        ViewGroup oriRight = mThreeViewLL.get(RIGHT_VIEW_INDEX);

        Log.d(TAG, moveToLeftOrRight + "");

        if (moveToLeftOrRight == MOVE_TO_RIGHT) {  //right
            mThreeViewLL.removeLast();
            mThreeViewLL.addFirst(oriRight);
        } else {                       //left
            mThreeViewLL.removeFirst();
            mThreeViewLL.addLast(oriLeft);
        }

//        if(mOnPageChangedListener != null)
//            mOnPageChangedListener.onPageChanged(moveToLeftOrRight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(mThreeViewWidth, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("Infinite3View", "ACTION_DOWN");
                if (mThreeViewLL.get(CENTER_VIEW_INDEX).getChildCount() == 0) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("Infinite3View", "ACTION_MOVE");
                onInterceptTouchEvent(event);
                break;
            case MotionEvent.ACTION_UP:
                onInterceptTouchEvent(event);
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final boolean orderChanged;
        float offset = 0;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(mVelocityTracker == null)
                    mVelocityTracker = VelocityTracker.obtain();
                mVelocityTracker.addMovement(ev);
                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                mOnInitialTouch = mOnTouchX = ev.getAxisValue(0);
                break;
            case MotionEvent.ACTION_MOVE:
                offset = (ev.getAxisValue(0) - mOnTouchX);
                if (Math.abs(ev.getAxisValue(0) - mOnInitialTouch) < mOneViewWidth * 0.1)
                    break;

                mVelocityTracker.addMovement(ev);
                mOnTouchX = ev.getAxisValue(0);
                mThreeViewLL.get(LEFT_VIEW_INDEX).setX(mThreeViewLL.get(LEFT_VIEW_INDEX).getX() + offset);
                mThreeViewLL.get(RIGHT_VIEW_INDEX).setX(mThreeViewLL.get(RIGHT_VIEW_INDEX).getX() + offset);
                mThreeViewLL.get(CENTER_VIEW_INDEX).setX(mThreeViewLL.get(CENTER_VIEW_INDEX).getX() + offset);
                mOnPageChangedListener.onPageScrolled();
                return true;
            case MotionEvent.ACTION_UP:
                float differ = ev.getAxisValue(0) - mOnInitialTouch;

                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int initialVelocity = (int) VelocityTrackerCompat.getXVelocity(
                        velocityTracker, mActivePointerId);

                AnimatorSet set = new AnimatorSet();
                ValueAnimator animatorLeft, animatorCenter, animatorRight;
                final int moveToLeftOrRight = differ > 0 ? MOVE_TO_RIGHT : MOVE_TO_LEFT;       // 1: right, -1: left
                if (Math.abs(differ) > mOneViewWidth * 0.5 || Math.abs(initialVelocity) > mMinimumVelocity) {
                    if (moveToLeftOrRight == MOVE_TO_RIGHT) {
                        animatorLeft = ObjectAnimator.ofFloat(mThreeViewLL.get(LEFT_VIEW_INDEX), "X", mThreeViewLL.get(LEFT_VIEW_INDEX).getX(), moveToLeftOrRight * mOneViewWidth);
                        animatorRight = ObjectAnimator.ofFloat(mThreeViewLL.get(RIGHT_VIEW_INDEX), "X", mThreeViewLL.get(RIGHT_VIEW_INDEX).getX(), 0);
                        mThreeViewLL.get(RIGHT_VIEW_INDEX).setVisibility(INVISIBLE);
                    } else {
                        animatorLeft = ObjectAnimator.ofFloat(mThreeViewLL.get(LEFT_VIEW_INDEX), "X", mThreeViewLL.get(LEFT_VIEW_INDEX).getX(), -2 * moveToLeftOrRight * mOneViewWidth);
                        animatorRight = ObjectAnimator.ofFloat(mThreeViewLL.get(RIGHT_VIEW_INDEX), "X", mThreeViewLL.get(RIGHT_VIEW_INDEX).getX() + offset, -1 * moveToLeftOrRight * mOneViewWidth);
                        mThreeViewLL.get(LEFT_VIEW_INDEX).setVisibility(INVISIBLE);
                    }
                    animatorCenter = ObjectAnimator.ofFloat(mThreeViewLL.get(CENTER_VIEW_INDEX), "X", mThreeViewLL.get(CENTER_VIEW_INDEX).getX() + offset, (1 + moveToLeftOrRight) * mOneViewWidth);
                    orderChanged = true;

                } else {
                    if (moveToLeftOrRight == MOVE_TO_RIGHT) {
                        animatorLeft = ObjectAnimator.ofFloat(mThreeViewLL.get(LEFT_VIEW_INDEX), "X", mThreeViewLL.get(LEFT_VIEW_INDEX).getX(), 0);
                        animatorRight = ObjectAnimator.ofFloat(mThreeViewLL.get(RIGHT_VIEW_INDEX), "X", mThreeViewLL.get(RIGHT_VIEW_INDEX).getX(), 2 * moveToLeftOrRight * mOneViewWidth);
                    } else {
                        animatorLeft = ObjectAnimator.ofFloat(mThreeViewLL.get(LEFT_VIEW_INDEX), "X", mThreeViewLL.get(LEFT_VIEW_INDEX).getX(), 0);
                        animatorRight = ObjectAnimator.ofFloat(mThreeViewLL.get(RIGHT_VIEW_INDEX), "X", mThreeViewLL.get(RIGHT_VIEW_INDEX).getX(), -2 * moveToLeftOrRight * mOneViewWidth);
                    }
                    animatorCenter = ObjectAnimator.ofFloat(mThreeViewLL.get(CENTER_VIEW_INDEX), "X", mThreeViewLL.get(CENTER_VIEW_INDEX).getX(), mOneViewWidth);

                    orderChanged = false;
                }
                set.play(animatorCenter).with(animatorLeft).with(animatorRight);
                set.setDuration(100);
                set.setInterpolator(new AccelerateDecelerateInterpolator());
                set.addListener(new ThreeViewAnimatorListener(moveToLeftOrRight, orderChanged));
                set.start();
                if(mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
        }
        return false;
    }

    private class ThreeViewAnimatorListener implements Animator.AnimatorListener {

        int moveToLeftOrRight;
        boolean orderChanged;

        ThreeViewAnimatorListener(int moveToLeftOrRight) {
            this(moveToLeftOrRight, true);
        }

        ThreeViewAnimatorListener(int moveToLeftOrRight, boolean orderChanged) {
            this.moveToLeftOrRight = moveToLeftOrRight;
            this.orderChanged = orderChanged;
        }

        @Override
        public void onAnimationStart(Animator animation) {}

        @Override
        public void onAnimationEnd(Animator animation) {
            if (orderChanged) {
                refreshLinkedListOrder(moveToLeftOrRight);
                mThreeViewLL.get(LEFT_VIEW_INDEX).setVisibility(VISIBLE);
                mThreeViewLL.get(RIGHT_VIEW_INDEX).setVisibility(VISIBLE);
            }

            if(mOnPageChangedListener != null)
                mOnPageChangedListener.onPageScrollStop(orderChanged, moveToLeftOrRight);
        }
        @Override
        public void onAnimationCancel(Animator animation) {}
        @Override
        public void onAnimationRepeat(Animator animation) {}
    }


}
