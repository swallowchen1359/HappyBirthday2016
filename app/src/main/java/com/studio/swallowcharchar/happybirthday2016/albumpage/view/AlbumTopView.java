package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.studio.swallowcharchar.happybirthday2016.MainTopView;
import com.studio.swallowcharchar.happybirthday2016.widget.Infinite3View;

/**
 * Created by Swallow on 6/9/16.
 */
public class AlbumTopView extends Infinite3View implements MainTopView {

    View viewA, viewB, viewC;

    public AlbumTopView(Context context) {
        this(context, null);
    }

    public AlbumTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        viewA = new View(context);
        viewA.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        viewA.setBackgroundColor(Color.RED);
        viewB = new View(context);
        viewB.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        viewB.setBackgroundColor(Color.YELLOW);
        viewC = new View(context);
        viewC.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        viewC.setBackgroundColor(Color.GREEN);

        refreshItem(LEFT_VIEW_INDEX, viewA);
        refreshItem(CENTER_VIEW_INDEX, viewB);
        refreshItem(RIGHT_VIEW_INDEX, viewC);
    }
}
