package com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.studio.swallowcharchar.happybirthday2016.MainBotView;

/**
 * Created by Swallow on 6/8/16.
 */
public class BirthdayCardBotView extends ScrollView implements MainBotView {

    private TextView mTextView;

    public BirthdayCardBotView(Context context) {
        this(context, null);
    }

    public BirthdayCardBotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setBackgroundColor(Color.GRAY);
    }
}
