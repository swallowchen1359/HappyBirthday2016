package com.studio.swallowcharchar.happybirthday2016.widget;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

/**
 * Created by Swallow on 6/8/16.
 */
public class LinkedListPagerAdapter<T extends View> extends PagerAdapter {

    private LinkedList<T> mLinkedList;

    private int mItemCount;

    public LinkedListPagerAdapter(LinkedList<T> ll) {
        mLinkedList = ll;
        mItemCount = ll.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        T v = mLinkedList.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public int getCount() {
        return mItemCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
