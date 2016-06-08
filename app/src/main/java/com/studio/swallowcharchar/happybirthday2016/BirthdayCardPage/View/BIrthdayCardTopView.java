package com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.studio.swallowcharchar.happybirthday2016.MainTopView;
import com.studio.swallowcharchar.happybirthday2016.widget.LinkedListPagerAdapter;

import java.util.LinkedList;

/**
 * Created by Swallow on 6/8/16.
 */
public class BirthdayCardTopView extends ViewPager implements MainTopView {

    public BirthdayCardTopView(Context context) {
        this(context, null);
    }

    public BirthdayCardTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LinkedListPagerAdapter<ImageView> adapter;
        LinkedList<ImageView> ll = new LinkedList<>();
        ImageView viewA, viewB;

        viewA = new ImageView(context);
        viewA.setBackgroundColor(Color.CYAN);
        viewB = new ImageView(context);
        viewB.setBackgroundColor(Color.YELLOW);

        ll.add(viewA);
        ll.add(viewB);

        adapter = new LinkedListPagerAdapter<>(ll);
        setAdapter(adapter);
    }
}
