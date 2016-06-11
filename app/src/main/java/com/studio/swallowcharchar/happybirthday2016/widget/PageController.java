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
    private ViewGroup mRootContainer, mTopContainer, mBotContainer;

    public PageController(Context context, ViewGroup rootContainer, ViewGroup topContainer, ViewGroup botContainer) {
        mContext = context;
        mRootContainer = rootContainer;
        mTopContainer = topContainer;
        mBotContainer = botContainer;
    }

    public Context getContext() {
        return mContext;
    }

    public ViewGroup getRootContainer() {
        return mRootContainer;
    }
    public ViewGroup getTopContainer() {
        return mTopContainer;
    }

    public ViewGroup getBotContainer() {
        return mBotContainer;
    }

    public abstract void changePage(MainTopView topView, MainBotView botView);
}
