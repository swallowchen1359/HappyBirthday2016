package com.studio.swallowcharchar.happybirthday2016.albumpage.controller;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.studio.swallowcharchar.happybirthday2016.MainBotView;
import com.studio.swallowcharchar.happybirthday2016.MainTopView;
import com.studio.swallowcharchar.happybirthday2016.albumpage.view.AlbumBotView;
import com.studio.swallowcharchar.happybirthday2016.albumpage.view.AlbumTopView;
import com.studio.swallowcharchar.happybirthday2016.widget.PageController;

/**
 * Created by Swallow on 6/10/16.
 */
public class AlbumController extends PageController {
    private ViewGroup mTopContainer;
    private ViewGroup mBotContainer;

    private AlbumTopView mAlbumTopView;
    private AlbumBotView mAlbumBotView;

    public AlbumController(Context context, ViewGroup topContainer, ViewGroup botContainer) {
        super(context, topContainer, botContainer);
        mTopContainer = getTopContainer();
        mBotContainer = getBotContainer();
        mAlbumTopView = new AlbumTopView(context);
        mAlbumBotView = new AlbumBotView(context);
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
    }
}
