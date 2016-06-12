package com.studio.swallowcharchar.happybirthday2016.albumpage.controller;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.transition.Visibility;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.studio.swallowcharchar.happybirthday2016.MainBotView;
import com.studio.swallowcharchar.happybirthday2016.MainTopView;
import com.studio.swallowcharchar.happybirthday2016.albumpage.view.AlbumBotView;
import com.studio.swallowcharchar.happybirthday2016.albumpage.view.AlbumTopView;
import com.studio.swallowcharchar.happybirthday2016.widget.Infinite3View;
import com.studio.swallowcharchar.happybirthday2016.widget.PageController;
import com.studio.swallowcharchar.happybirthday2016.widget.PopScrollView;
import com.studio.swallowcharchar.happybirthday2016.widget.Utility;

/**
 * Created by Swallow on 6/10/16.
 */
public class AlbumController extends PageController implements AlbumBotView.AlbumOnClickListener {
    private static final int FSM_HOME = 0;
    private static final int FSM_DETAIL = 1;

    private static int sAlbumFSM;

    private Context mContext;
    private ViewGroup mRootContainer;
    private ViewGroup mTopContainer;
    private ViewGroup mBotContainer;

    private AlbumTopView mAlbumTopView;
    private AlbumBotView mAlbumBotView;

    public AlbumController(Context context, ViewGroup rootContainer, ViewGroup topContainer, ViewGroup botContainer) {
        super(context, rootContainer, topContainer, botContainer);
        mContext = context;
        mRootContainer = getRootContainer();
        mTopContainer = getTopContainer();
        mBotContainer = getBotContainer();
        mAlbumTopView = new AlbumTopView(context);
        mAlbumBotView = new AlbumBotView(context);
        sAlbumFSM = -1;
        mAlbumBotView.setAlbumOnClickListener(this);
    }

    @Override
    public void changePage(MainTopView preTopView, MainBotView preBotView) {
        /** TODO: animation of change page should move to corresponding Top/BotView?????? */
        /** Controlling parameter for TopContainer */
        LayoutTransition layoutTopTransition = new LayoutTransition();
//        PropertyValuesHolder containerTopScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.9f);
        PropertyValuesHolder containerTopScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.0f);
        PropertyValuesHolder containerBotScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.9f);
        ValueAnimator appearTopAnimator = ObjectAnimator.ofFloat(mTopContainer, "alpha", 0f, 1f);
        ValueAnimator vanishTopAnimator = ObjectAnimator.ofFloat(mTopContainer, "alpha", 1f, 0f);
        ValueAnimator containerTopAnimator = ObjectAnimator.ofPropertyValuesHolder(mTopContainer, containerTopScaleX, containerBotScaleY);
        /** Controlling parameter for BotContainer */
        LayoutTransition layoutBotTransition = new LayoutTransition();
        PropertyValuesHolder appearBotAlpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        PropertyValuesHolder appearBotTransY = PropertyValuesHolder.ofFloat("translationY", 200f, 0f);
        ValueAnimator appearBotAnimator = ObjectAnimator.ofPropertyValuesHolder(mBotContainer, appearBotAlpha, appearBotTransY);
        ValueAnimator vanishBotAnimator = ObjectAnimator.ofFloat(mBotContainer, "alpha", 1f, 0f);

        /** Control TopContainer */
        appearTopAnimator.setDuration(300);
        appearTopAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        vanishTopAnimator.setDuration(300);
        vanishTopAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        layoutTopTransition.setAnimator(LayoutTransition.APPEARING, appearTopAnimator);
        layoutTopTransition.setAnimator(LayoutTransition.DISAPPEARING, vanishTopAnimator);
        mTopContainer.setLayoutTransition(layoutTopTransition);

        containerTopAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mTopContainer.addView(mAlbumTopView);
                mTopContainer.removeViewAt(0);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        containerTopAnimator.setDuration(300);
        containerTopAnimator.start();

        /** Control BotContainer */
        appearBotAnimator.setDuration(300);
        appearBotAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        vanishBotAnimator.setDuration(300);
        vanishBotAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        layoutBotTransition.setAnimator(LayoutTransition.APPEARING, appearBotAnimator);
        layoutBotTransition.setAnimator(LayoutTransition.DISAPPEARING, vanishBotAnimator);
        mBotContainer.setLayoutTransition(layoutBotTransition);
        mBotContainer.removeViewAt(0);
        mBotContainer.addView(mAlbumBotView);
        sAlbumFSM = FSM_HOME;
    }

    public void changeSelectedAlbum(int albumIndex) {
        int sliceCoordinate[] = new int[2];
        int albumCoordinate[] = new int[2];
        AlbumBotView.AlbumCover albumCover;
        /** Slice is the center view of Infinite3View, i.e., AlbumTopView */
        float slicePositionX, slicePositionY, sliceWidth, sliceHeight;
        /** Album will be the selected album, if albumIndex is valid. */
        float albumPositionX, albumPositionY, albumWidth, albumHeight;
        Animator albumAnimator;
        PropertyValuesHolder albumTransX, albumTransY, albumScaleX, albumScaleY;

        if (albumIndex < 0 || sAlbumFSM != FSM_HOME) {
            return;
        }

        /** if index and state is valid, using slice and album position to do animation */
        ((ViewGroup)mAlbumTopView.getCurrentItem()).getChildAt(0).getLocationInWindow(sliceCoordinate);
        albumCover = mAlbumBotView.getAlbumContainer().getAlbumCoverAt(albumIndex);
        Utility.setAllParentsClip(albumCover.getAlbumCoverImage(), false);
        albumCover.getAlbumCoverImage().getLocationInWindow(albumCoordinate);
        albumCover.getAlbumCoverImage().setPivotX(0);
        albumCover.getAlbumCoverImage().setPivotY(0);
        slicePositionX = sliceCoordinate[0];
        slicePositionY = sliceCoordinate[1];
        sliceWidth = ((ViewGroup)mAlbumTopView.getCurrentItem()).getChildAt(0).getMeasuredWidth() * mTopContainer.getScaleX();
        sliceHeight = ((ViewGroup)mAlbumTopView.getCurrentItem()).getChildAt(0).getMeasuredHeight() * mTopContainer.getScaleY();
        albumPositionX = albumCoordinate[0];
        albumPositionY = albumCoordinate[1];
        albumWidth = albumCover.getAlbumCoverImage().getMeasuredWidth();
        albumHeight = albumCover.getAlbumCoverImage().getMeasuredHeight();
        Log.d("AlbumController", "sliceX " +slicePositionX+ " sliceY " +slicePositionY);
        Log.d("AlbumController", "albumX " +albumPositionX+ " albumY " +albumPositionY);
        albumTransX = PropertyValuesHolder.ofFloat("translationX", 0f, slicePositionX - albumPositionX);
        albumTransY = PropertyValuesHolder.ofFloat("translationY", 0f, slicePositionY - albumPositionY);
        albumScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, sliceWidth / albumWidth);
        albumScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, sliceHeight / albumHeight);
        /** Because AlbumCover cannot cross over its container, using a fake ImageView */
        albumAnimator = ObjectAnimator.ofPropertyValuesHolder(albumCover.getAlbumCoverImage(), albumTransX, albumTransY, albumScaleX, albumScaleY);
        albumAnimator.setDuration(300);
        albumAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        albumAnimator.start();
        albumAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /** change FSM if it is a successful procedure */
                sAlbumFSM = FSM_DETAIL;
                showAlbumDetailAtBot();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void showAlbumDetailAtBot() {
        if (sAlbumFSM != FSM_DETAIL) {
            return;
        }
        mAlbumBotView.getAlbumContainer().setVisibility(View.GONE);
        mAlbumBotView.initAlbumDetailScrollView();
        mAlbumBotView.getAlbumDetailScrollView().setOnFocusedItemChangedListener(new PopScrollView.OnFocusedItemChangedListener() {
            @Override
            public void onFocusedItemChanged(int currentIndex, int previousIndex) {
                if (currentIndex > previousIndex) {
                    mAlbumTopView.setCurrentItem(Infinite3View.RIGHT_VIEW_INDEX);
                } else {
                    mAlbumTopView.setCurrentItem(Infinite3View.LEFT_VIEW_INDEX);
                }
            }
        });
    }

    /*********************** Implement AlbumBotView.AlbumOnClickListener **************************/
    @Override
    public void albumOnClickListener(int albumIndex) {
        changeSelectedAlbum(albumIndex);
    }
}
