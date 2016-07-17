package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.widget.DialogView;

/**
 * Created by Swallow on 7/14/16.
 */
public class PhotoDialogView extends DialogView {
    public interface OnBackgroundClickListener {
        void onBackgroundClick();
    }
    private static final int VIEW_BACKGROUND_RES_ID = R.id.photo_dialog_view;
    public static final int IDX_STYLE_CHG_COVER = 0x1;
    public static final int IDX_STYLE_CHG_TIME = 0x2;
    public static final int IDX_STYLE_CHG_PLACE = 0x3;
    public static final int IDX_STYLE_CHG_GALLERY = 0x4;

    private View mBackgroundView;

    private OnBackgroundClickListener mOnBackgroundClickListener;

    public PhotoDialogView(Context context) {
        this(context, null);
    }

    public PhotoDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mBackgroundView == null) {
            mBackgroundView = findViewById(VIEW_BACKGROUND_RES_ID);
        }
        mBackgroundView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBackgroundClickListener != null) {
                    mOnBackgroundClickListener.onBackgroundClick();
                }
            }
        });
    }

    public void setOnBackgroundClickListener(OnBackgroundClickListener listener) {
        mOnBackgroundClickListener = listener;
    }
}
