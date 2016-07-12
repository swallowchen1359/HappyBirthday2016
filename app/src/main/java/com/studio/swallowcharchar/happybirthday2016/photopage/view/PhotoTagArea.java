package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.util.AttributeSet;

import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;

import java.util.LinkedList;
import java.util.String;

public class PhotoTagArea extends HorizontalScrollView {

    public interface OnTagClickListener {
        public void OnTagClick(String string);
    }

    /**
     * The only child of ScrollView, which width is wrap_content to contain lots of TextView
     */
    private LinearLayout mLinearLayout;
    private LinkedList<TextView> mTagViewList;
    private Context mContext;
    private OnTagClickListener mOnTagClickListener;

    public PhotoTagArea(Context context) {
        this(context, null);
    }

    public PhotoTagArea(Context context, AttributeSet attr) {
        super(context, attr);
        mContext = context;
        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setLayoutParams(new HorizontalScrollView.LayoutParams(HorizontalScrollView.LayoutParams.WRAP_CONTENT, HorizontalScrollView.LayoutParams.MATCH_PARENT));
        addView(mLinearLayout);
        mTagViewList = new LinkedList<>();
    }

    public void setTags(LinkedList<String> strList) {
        if (mTagViewList == null) {
            mTagViewList = new LinkedList<>();
        }

        if (strList == null) {
            return;
        }

        for (int i = 0; i < strList.size(); i++) {
            final String tagStr = strList.get(i);
            TextView textView = new TextView(mContext);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(tagStr);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                void onClick(View v) {
                    if (mOnTagClickListener != null) {
                        mOnTagClickListener.OnTagClick(tagStr);
                    }
                }
            });
            mTagViewList.add(textView);
            mLinearLayout.addView(textView);
        }
    }

    public void setOnTagClickListener(OnTagClickListener listener) {
        mOnTagClickListener = listener;
    }
}