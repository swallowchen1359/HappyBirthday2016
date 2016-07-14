package com.studio.swallowcharchar.happybirthday2016.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by Swallow on 7/14/16.
 */
public class DialogView extends CardView {
    public DialogView(Context context) {
        this(context, null);
    }

    public DialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void showDialog() {
        setVisibility(VISIBLE);
    }

    public void closeDialog() {
        removeAllViews();
        setVisibility(INVISIBLE);
    }
}
