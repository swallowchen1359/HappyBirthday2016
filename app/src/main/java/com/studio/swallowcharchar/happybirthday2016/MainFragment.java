package com.studio.swallowcharchar.happybirthday2016;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.view.BirthdayCardBotView;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.view.BirthdayCardTopView;


/**
 * MainFragment is used to manage fragment's lifecycle and view's movement
 */
public class MainFragment extends Fragment {

    private static final int VIEW_RES_ID = R.layout.fragment_main;
    private static final int VIEW_TOP_RES_ID = R.id.main_top_container;
    private static final int VIEW_BOT_RES_ID = R.id.main_bot_container;

    private ViewGroup mTopContainer;
    private ViewGroup mBotContainer;

    /**
     * MainTopView and MainBotView are interface to make MainFragment can control all its
     * implemented object like BirthdayCardTopView etc.
     * */
    private MainTopView mTopView;
    private MainBotView mBotView;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(VIEW_RES_ID, container, false);
        mTopContainer = (ViewGroup) fragmentView.findViewById(VIEW_TOP_RES_ID);
        mBotContainer = (ViewGroup) fragmentView.findViewById(VIEW_BOT_RES_ID);
        /** Put BirthdayCardTop/BotView for first page, and testing */
        mTopContainer.addView(new BirthdayCardTopView(getContext()));
        mBotContainer.addView(new BirthdayCardBotView(getContext()));
        return fragmentView;
    }

    @Override
    public void onResume() {
        View topView, botView;

        super.onResume();
        /** If mTopView and mBotView already attached, doesn't need to attach again, just return. */
        if (mTopView != null && mBotView != null) {
            return;
        }
        if (mTopContainer == null || mBotContainer == null ||
            mTopContainer.getChildCount() < 1 || mBotContainer.getChildCount() < 1) {
            return;
        }
        topView = mTopContainer.getChildAt(0);
        botView = mBotContainer.getChildAt(0);

        if (topView instanceof MainTopView && botView instanceof MainBotView) {
            mTopView = (MainTopView) topView;
            mBotView = (MainBotView) botView;
        }
    }
}
