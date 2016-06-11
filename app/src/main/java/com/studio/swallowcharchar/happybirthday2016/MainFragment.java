package com.studio.swallowcharchar.happybirthday2016;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.albumpage.controller.AlbumController;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.controller.BirthdayCardController;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.view.BirthdayCardBotView;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.view.BirthdayCardTopView;
import com.studio.swallowcharchar.happybirthday2016.theaterpage.controller.TheaterController;
import com.studio.swallowcharchar.happybirthday2016.widget.PageController;


/**
 * MainFragment is used to manage fragment's lifecycle and view's movement
 */
public class MainFragment extends Fragment implements MainActivity.SwitchPageButtonListener {

    public static final int PAGE_BIRTHDAY_CARD = 0;
    public static final int PAGE_ALBUM = 1;
    public static final int PAGE_THEATER = 2;

    private static final int VIEW_RES_ID = R.layout.fragment_main;
    private static final int VIEW_TOP_RES_ID = R.id.main_top_container;
    private static final int VIEW_BOT_RES_ID = R.id.main_bot_container;

    private PageController mPageController;
    private BirthdayCardController mBirthdayCardController;
    private AlbumController mAlbumController;
    private TheaterController mTheaterController;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).setSwitchPageButtonListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(VIEW_RES_ID, container, false);
        mTopContainer = (ViewGroup) fragmentView.findViewById(VIEW_TOP_RES_ID);
        mBotContainer = (ViewGroup) fragmentView.findViewById(VIEW_BOT_RES_ID);
        /** Put BirthdayCardTop/BotView for first page, and testing */
        /** TODO: move to BirthdayCardController later */
        mTopContainer.addView(new BirthdayCardTopView(getContext()));
        mBotContainer.addView(new BirthdayCardBotView(getContext()));

        /**
         * Using BirthdayCardController as init controller, controller will be attached according
         * to current showing page.
         * */
        mBirthdayCardController = new BirthdayCardController(getContext(), mTopContainer, mBotContainer);
        mPageController = mBirthdayCardController;
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

    public void switchPage(int pageIndex) {
        switch (pageIndex) {
            case PAGE_BIRTHDAY_CARD:
                mPageController = mBirthdayCardController;
                break;
            case PAGE_ALBUM:
                if (mAlbumController == null) {
                    mAlbumController = new AlbumController(getContext(), mTopContainer, mBotContainer);
                }
                mPageController = mAlbumController;
                break;
            case PAGE_THEATER:
                mPageController = mTheaterController;
                break;
            default:
                mPageController = mBirthdayCardController;
                break;
        }
        if (mPageController != null) {
            mPageController.changePage(mTopView, mBotView);
        }
    }

    /***************************** MainActivity.SwitchPageButtonListener **************************/
    /**
     * Implement all event in Fragment, do not do much thing in MainActivity
     * */
    @Override
    public void buttonAlbumOnClick() {
        switchPage(PAGE_ALBUM);
    }

    @Override
    public void buttonTheaterOnClick() {
        switchPage(PAGE_THEATER);
    }
}
