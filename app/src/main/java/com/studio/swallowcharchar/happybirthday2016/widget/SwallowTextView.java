package com.studio.swallowcharchar.happybirthday2016.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.studio.swallowcharchar.happybirthday2016.R;

/**
 * Created by Swallow on 7/11/16.
 */
public class SwallowTextView extends TextView {
    private Context mContext;
    private String mTtfString;
    public SwallowTextView(Context context) {
        this(context, null);
    }

    public SwallowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SwallowTextView, 0, 0);
        try {
            mTtfString = ta.getString(R.styleable.SwallowTextView_ttf_name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        init();
    }

    private  void init() {
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), mTtfString);
        setTypeface(tf);
    }
}
