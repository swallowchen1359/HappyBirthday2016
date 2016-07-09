package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.studio.swallowcharchar.happybirthday2016.widget.ImageUtility;

import java.util.LinkedList;

/**
 * Created by Swallow on 7/8/16.
 */
//public class PhotoGallery extends GridView {
//
//    private boolean mExpanded;
//    private ImageAdapter mImageAdapter;
//    private LinkedList<Integer> mPhotoLinkedList;
//
//    public PhotoGallery(Context context) {
//        this(context, null);
//    }
//
//    public PhotoGallery(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public PhotoGallery(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        mPhotoLinkedList = new LinkedList<>();
//        mImageAdapter = new ImageAdapter(context, mPhotoLinkedList);
//        setAdapter(mImageAdapter);
//        setFocusable(false);
//        setExpanded(false);
//    }
//
//    public boolean isExpanded()
//    {
//        return mExpanded;
//    }
//
//    @Override
//    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//    {
//        // HACK! TAKE THAT ANDROID!
//        if (isExpanded())
//        {
//            // Calculate entire height by providing a very large height hint.
//            // But do not use the highest 2 bits of this integer; those are
//            // reserved for the MeasureSpec mode.
//            int expandSpec = MeasureSpec.makeMeasureSpec(
//                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//            super.onMeasure(widthMeasureSpec, expandSpec);
//
//            ViewGroup.LayoutParams params = getLayoutParams();
//            params.height = getMeasuredHeight();
//        }
//        else
//        {
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        }
//    }
//
//    public void setExpanded(boolean expanded)
//    {
//        this.mExpanded = expanded;
//    }
//
//    public void setGallery(LinkedList<Integer> linkedList) {
//        if (linkedList == null) {
//            return;
//        }
//        for (int i = 0; i < linkedList.size(); i++) {
//            mPhotoLinkedList.add(linkedList.get(i));
//        }
//        mImageAdapter.notifyDataSetChanged();
//    }
//
//    public class ImageAdapter extends BaseAdapter {
//
//        private Context mContext;
//        private LinkedList<Integer> mImageLinkedList;
//        public ImageAdapter(Context context, LinkedList<Integer> linkedList) {
//            mContext = context;
//            mImageLinkedList = linkedList;
//        }
//
//        @Override
//        public int getCount() {
//            return mImageLinkedList == null ? -1 : mImageLinkedList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return mImageLinkedList == null ? null : mImageLinkedList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return mImageLinkedList == null ? -1 : position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView imageView;
//            if (mImageLinkedList == null) {
//                return null;
//            }
//            if (convertView == null) {
//                Log.d("PhotoGallery", "convertView null");
//                imageView = new ImageView(mContext);
//                imageView.setImageBitmap(ImageUtility.decodeSampledBitmapFromResource(getResources(), mImageLinkedList.get(position), 360, 360));
////                imageView.setImageResource(mImageLinkedList.get(position));
//                imageView.setLayoutParams(new GridView.LayoutParams(360, 360));
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            } else {
//                Log.d("PhotoGallery", "convertView not null");
//                imageView = (ImageView) convertView;
//            }
//            return imageView;
//        }
//    }
//}

public class PhotoGallery extends ViewGroup {

    public PhotoGallery(Context context) {
        this(context, null);
    }

    public PhotoGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setGallery(LinkedList<Integer> resIds) {
        if (resIds == null) {
            return;
        }
        for (int i = 0; i < resIds.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageBitmap(ImageUtility.decodeSampledBitmapFromResource(getResources(), resIds.get(i), 340, 340));
            imageView.setLayoutParams(new ViewGroup.LayoutParams(340, 340));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            addView(imageView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        int childWidth = 0, childHeight = 0;
        if (childCount > 0) {
            if (getChildAt(0) != null) {
                childWidth = getChildAt(0).getMeasuredWidth();
                childHeight = getChildAt(0).getMeasuredHeight();
            }
        }
        int rowNumber = childCount / 3;
        if (childCount % 3 != 0 && childCount != 0) {
            rowNumber += 1;
        }
        int measuredHeight = childHeight * rowNumber;
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

        String TAG = getClass().getSimpleName();

        Log.d(TAG, "(parentWidth) (" +parentWidth+")");
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            /** Get width and height of child and re-align */
            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();
            /** Parent, i.e., ViewGroup, is not big enough to put the child, put to the next row */
            if (painterX + width > parentWidth) {
                painterX = left;
                painterY += height;
            }
            /** re-align and re-draw child view */
            childView.layout(painterX, painterY, painterX + width, painterY + height);
            /** refresh painterX, while painterY is refreshed during re-align judgement */
            painterX += width;
        }
    }
}
