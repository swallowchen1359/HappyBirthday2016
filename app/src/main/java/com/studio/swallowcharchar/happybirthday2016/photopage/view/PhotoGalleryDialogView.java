package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.studio.swallowcharchar.happybirthday2016.R;

/**
 * Created by Swallow on 7/19/16.
 */
public class PhotoGalleryDialogView extends PhotoDialogView {
    
    public interface EventListener {
        void onImageClick(boolean picked, int index);
        void onConfirmClick();
    }

    private static final int VIEW_GALLERY_RES_ID = R.id.photo_dialog_gallery;
    private static final int VIEW_CONFIRM_RES_ID = R.id.photo_dialog_gallery_confirm;

    public static final int MODE_NORMAL = 0x0;
    public static final int MODE_EDITOR = 0x1;

    private PhotoGallery mPhotoGallery;
    private LinearLayout mConfirmButton;
    
    private EventListener mEventListener;

    public PhotoGalleryDialogView(Context context) {
        this(context, null);
    }

    public PhotoGalleryDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mPhotoGallery == null) {
            mPhotoGallery = (PhotoGallery) findViewById(VIEW_GALLERY_RES_ID);
        }
        mPhotoGallery.setOnImageClickListener(new PhotoGallery.OnImageClickListener() {
            @Override
            public void onImageClick(boolean picked, int index) {
                if (mEventListener != null) {
                    mEventListener.onImageClick(picked, index);
                }
            }
        });
        
        if (mConfirmButton == null) {
            mConfirmButton = (LinearLayout) findViewById(VIEW_CONFIRM_RES_ID);
        }
        mConfirmButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEventListener != null) {
                    mEventListener.onConfirmClick();
                }
            }
        });
    }

    public void setPhoto(Bitmap bitmap) {
        mPhotoGallery.setGallery(bitmap);
    }

    public void setEditorMode(int mode) {
        mPhotoGallery.setEditorMode(mode);
    }

    public void setEventListener(EventListener listener) {
        mEventListener = listener;
    }
    
}
