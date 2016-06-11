package com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.controller;

import android.content.Context;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.MainBotView;
import com.studio.swallowcharchar.happybirthday2016.MainTopView;
import com.studio.swallowcharchar.happybirthday2016.widget.PageController;

/**
 * Created by Swallow on 6/10/16.
 */
public class BirthdayCardController extends PageController{
    public BirthdayCardController(Context context, ViewGroup rootContainer, ViewGroup topContainer, ViewGroup botContainer) {
        super(context, rootContainer, topContainer, botContainer);
    }

    @Override
    public void changePage(MainTopView topView, MainBotView botView) {

    }
}
