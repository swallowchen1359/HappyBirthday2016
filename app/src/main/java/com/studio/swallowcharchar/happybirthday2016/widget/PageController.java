package com.studio.swallowcharchar.happybirthday2016.widget;

import android.content.Context;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.MainBotView;
import com.studio.swallowcharchar.happybirthday2016.MainTopView;

/**
 * Created by Swallow on 6/10/16.
 */
public abstract class PageController {
    private Context mContext;
    private ViewGroup mTopContainer, mBotContainer;

    public PageController(Context context, ViewGroup topContainer, ViewGroup botContainer) {
        mContext = context;
        mTopContainer = topContainer;
        mBotContainer = botContainer;
    }

    public Context getContext() {
        return mContext;
    }

    public ViewGroup getTopContainer() {
        return mTopContainer;
    }

    public ViewGroup getBotContainer() {
        return mBotContainer;
    }

    public abstract void changePage(MainTopView topView, MainBotView botView);
}
