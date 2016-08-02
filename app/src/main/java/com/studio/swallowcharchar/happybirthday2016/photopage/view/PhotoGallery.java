package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.RelativeLayout;
import android.widget.ImageView;

import com.studio.swallowcharchar.happybirthday2016.R;

/**
 * Created by Swallow on 7/8/16.
 */
public class PhotoGallery extends ViewGroup {

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }
    }
    
    public class ImageContainer extends RelativeLayout {
        
        private ImageView imageView;
        private RelativeLayout mask;
        
        private boolean isPicked;
        
        public ImageContainer(Context context) {
            this(context, null);
        }
        
        public ImageContainer(Context context, AttributeSet attrs) {
            super(context, attrs);
            isPicked = false;

            imageView = new ImageView(context);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.setScaleType.CENTER_CROP);

            maks = new RelativeLayout(context);
            mask.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

            addView(imageView);
            addView(mask);
        }
        
        public void setImageBitmap(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
        
        public void setMaskColor(int colorId) {
            mask.setBackgroundColor(colorId);
        }
        
        public void setPicked(boolean picked) {
            isPicked = picked;
        }
        
        public boolean isPicked() {
            return isPicked;
        }
    }

    public interface OnAddClickListener {
        void onAddClick(int mode);
    }
    
    public interface OnImageClickListener {
        void onImageClick();
    }

    private int mPhotoSize;
    private int mCurrentMode;
    private OnAddClickListener mOnAddClickListener;
    private OnImageClickListener mOnImageClickListener;

    public PhotoGallery(Context context) {
        this(context, null);
    }

    public PhotoGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PhotoGallery, 0, 0);
        try {
            mPhotoSize = (int) ta.getDimension(R.styleable.PhotoGallery_photoSize, 330);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCurrentMode = PhotoView.MODE_NORMAL;
    }

    /**
     * TODO : Debug, if ChildView without specific size, the ChildView will disappear
     * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        int childWidth = 0, childHeight = 0;
        int childMarginTop = 0, childMarginBot = 0;
        if (getChildAt(0) != null) {
            childWidth = getChildAt(0).getMeasuredWidth();
            childHeight = getChildAt(0).getMeasuredHeight();
            childMarginTop = ((LayoutParams) getChildAt(0).getLayoutParams()).topMargin;
            childMarginBot = ((LayoutParams) getChildAt(0).getLayoutParams()).bottomMargin;
        }

        int rowNumber = childCount / 3;
        if (childCount % 3 != 0 && childCount != 0) {
            rowNumber += 1;
        }
        int measuredHeight = (childHeight + childMarginBot + childMarginTop) * rowNumber;
        int measuredWidthSpec = View.MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int measuredHeightSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, MeasureSpec.EXACTLY);
        setMeasuredDimension(measuredWidthSpec, measuredHeightSpec);
        measureChildren(measuredWidthSpec, measuredHeightSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        /** The width of parent */
        int parentWidth = getMeasuredWidth();
        int painterX = left;
        /** painterX and painterY is based on the ViewParent (This view). */
        /** But args are based on the screen, relative to the left corner of screen */
        int painterY = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            PhotoGallery.LayoutParams margins = (PhotoGallery.LayoutParams) childView.getLayoutParams();
            /** Get width and height of child and re-align */
            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();
            /** Parent, i.e., ViewGroup, is not big enough to put the child, put to the next row */
            if (painterX + width + margins.leftMargin + margins.rightMargin > parentWidth) {
                painterX = left;
                painterY += height + margins.topMargin + margins.bottomMargin;
            }
            /** re-align and re-draw child view */
            childView.layout(painterX + margins.leftMargin, painterY + margins.topMargin, painterX + margins.leftMargin + width, painterY + margins.topMargin + height);
            /** refresh painterX, while painterY is refreshed during re-align judgement */
            painterX += width + margins.leftMargin + margins.rightMargin;
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new PhotoGallery.LayoutParams(getContext(), attrs);
    }

    public void setGallery(Bitmap bitmap) {
        createImageView(bitmap);
    }

    public void enterEditorMode() {
        mCurrentMode = PhotoView.MODE_EDITOR;
        createAddImageView();
    }

    public void exitEditorMode() {
        removeAddImageView();
        mCurrentMode = PhotoView.MODE_NORMAL;
    }

    public void setOnAddClickListener(OnAddClickListener listener) {
        mOnAddClickListener = listener;
    }
    
    public void setOnImageClickListener(OnImageClickListener listener) {
        mOnImageClickListener = listener;
    }

    private void createImageView(Bitmap bitmap) {
        ImageContainer imageContainer = new ImageContainer(getContext());
        imageContainer.setImageBitmap(bitmap);
        PhotoGallery.LayoutParams layoutParams = new PhotoGallery.LayoutParams(mPhotoSize, mPhotoSize);
        layoutParams.setMargins(5 ,5, 5, 5);
        imageContainer.setLayoutParams(layoutParams);
        /** For editor mode use */
        imageContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentMode == PhotoView.MODE_EDITOR) {
                    if (imageContainer.isPicked()) {
                        imageContainer.setMaskColor(getResources.getColor(R.color.default_img_background));
                        imageContainer.setPicked(false);
                        if (mOnImageClickListener != null) {
                            mOnImageClickListener.onImageClick();
                        }
                    } else {
                        imageContainer.setMaskColor(getResources().getColor(R.color.default_img_background));
                        imageContainer.setPicked(true);
                        if (mOnImageClickListener != null) {
                            mOnImageClickListener.onImageClick();
                        }
                    }
                }
            }
        });
        addView(imageView);
    }

    /** For editor mode use */
    private void createAddImageView() {
        if (mCurrentMode == PhotoView.MODE_NORMAL) {
            return;
        }

        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundColor(getResources().getColor(R.color.default_img_background));
        PhotoGallery.LayoutParams layoutParams = new PhotoGallery.LayoutParams(mPhotoSize, mPhotoSize);
        layoutParams.setMargins(5, 5, 5, 5);
        imageView.setLayoutParams(layoutParams);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnAddClickListener != null) {
                    mOnAddClickListener.onAddClick(mCurrentMode);
                }
            }
        });
        addView(imageView, 0);
    }

    /** For editor mode use */
    private void removeAddImageView() {
        if (mCurrentMode == PhotoView.MODE_NORMAL) {
            return;
        }

        removeViewAt(0);
    }
}
