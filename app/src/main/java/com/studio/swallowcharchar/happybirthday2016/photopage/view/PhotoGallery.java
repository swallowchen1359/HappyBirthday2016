package com.studio.swallowcharchar.happybirthday2016.photopage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by Swallow on 7/8/16.
 */
public class PhotoGallery extends GridView {
    public PhotoGallery(Context context) {
        super(context);
    }

    public PhotoGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoGallery(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
