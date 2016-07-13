package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.AttributeSet;
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

    private class AddImageAsyncTask extends AsyncTask<Integer, Void, LinkedList<Bitmap>> {

        private Resources mResources;

        public AddImageAsyncTask(Resources res) {
            mResources = res;
        }

        @Override
        protected LinkedList<Bitmap> doInBackground(Integer... params) {
            LinkedList<Bitmap> bitmapLinkedList = new LinkedList<>();
            for (int i = 0; i < params.length; i++) {
                int resId = params[i];
                Bitmap bitmap = ImageUtility.decodeSampledBitmapFromResource(mResources, resId, 330, 330);
                bitmapLinkedList.add(bitmap);
            }
            return bitmapLinkedList;
        }

        @Override
        protected void onPostExecute(LinkedList<Bitmap> bitmapLinkedList) {
            for (int i = 0; i < bitmapLinkedList.size(); i++) {
                createImageView(bitmapLinkedList.get(i));
            }
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }
    }

    public PhotoGallery(Context context) {
        this(context, null);
    }

    public PhotoGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setGallery(LinkedList<Bitmap> bitmapLinkedList) {
        for (int i = 0; i < bitmapLinkedList.size(); i++) {
            createImageView(bitmapLinkedList.get(i));
        }
    }

    public void setGallery(Bitmap bitmap) {
        createImageView(bitmap);
    }

    private void createImageView(Bitmap bitmap) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bitmap);
        PhotoGallery.LayoutParams layoutParams = new PhotoGallery.LayoutParams(330, 330);
        layoutParams.setMargins(5,5,5,5);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(imageView);
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
}
