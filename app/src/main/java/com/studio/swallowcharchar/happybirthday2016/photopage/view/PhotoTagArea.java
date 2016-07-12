package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.util.AttributeSet;

import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;

import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.LinkedList;

public class PhotoTagArea extends HorizontalScrollView {

    private static final int DIMEN_TAG_MARGIN_RES_ID = R.dimen.photo_intro_tag_margin;
    private static final int VIEW_TAG_BACKGROUND_RES_ID = R.drawable.rect_round_corner;

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
        mLinearLayout.setGravity(Gravity.CENTER_VERTICAL);
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
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginStart((int) mContext.getResources().getDimension(DIMEN_TAG_MARGIN_RES_ID));
            textView.setLayoutParams(layoutParams);
            textView.setText(tagStr);
            textView.setBackgroundResource(VIEW_TAG_BACKGROUND_RES_ID);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
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