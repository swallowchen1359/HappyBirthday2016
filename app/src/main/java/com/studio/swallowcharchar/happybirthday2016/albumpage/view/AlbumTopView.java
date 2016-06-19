package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.studio.swallowcharchar.happybirthday2016.MainTopView;
import com.studio.swallowcharchar.happybirthday2016.widget.Infinite3View;

public class AlbumTopView extends Infinite3View implements MainTopView {

    private static final float DIMEN_PADDING_RATIO = 0.0625f;

    private int mOneViewWidth;
    private int mOneViewPadding;
    private LinearLayout viewA, viewB, viewC;
    private View childA, childB, childC;

    public AlbumTopView(Context context) {
        this(context, null);
    }

    public AlbumTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mOneViewWidth = getOneViewWidth();
        mOneViewPadding = (int) (mOneViewWidth * DIMEN_PADDING_RATIO);

        viewA = new LinearLayout(context);
        viewA.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        viewA.setPadding(mOneViewPadding, mOneViewPadding, mOneViewPadding, mOneViewPadding);
        viewA.setBackgroundColor(Color.RED);
        viewB = new LinearLayout(context);
        viewB.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        viewB.setPadding(mOneViewPadding, mOneViewPadding, mOneViewPadding, mOneViewPadding);
        viewB.setBackgroundColor(Color.YELLOW);
        viewC = new LinearLayout(context);
        viewC.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        viewC.setPadding(mOneViewPadding, mOneViewPadding, mOneViewPadding, mOneViewPadding);
        viewC.setBackgroundColor(Color.GREEN);

        childA = new View(context);
        childA.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        childA.setBackgroundColor(Color.WHITE);
        childB = new View(context);
        childB.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        childB.setBackgroundColor(Color.WHITE);
        childC = new View(context);
        childC.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        childC.setBackgroundColor(Color.WHITE);

        viewA.addView(childA);
        viewB.addView(childB);
        viewC.addView(childC);

        refreshItem(LEFT_VIEW_INDEX, viewA);
        refreshItem(CENTER_VIEW_INDEX, viewB);
        refreshItem(RIGHT_VIEW_INDEX, viewC);

    }
}
