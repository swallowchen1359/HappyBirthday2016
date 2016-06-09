package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.studio.swallowcharchar.happybirthday2016.MainBotView;
import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.LinkedList;

/**
 * AlbumBotView extends ScrollView will contains only one child view, LinearLayout,
 * which is used to put all widget_album_cover
 */
public class AlbumBotView extends ScrollView implements MainBotView {
    /** mContainer is the only child will be put into AlbumBotView */
    private AlbumContainer mContainer;
    private LinkedList<AlbumCover> mAlbumCoverList;

    public AlbumBotView(Context context) {
        this(context, null);
    }

    public AlbumBotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mAlbumCoverList = new LinkedList<>();
        initContainer();
    }

    /**
     * attachAlbum means PUT already existing album into AlbumContainer to SHOW.
     * @return number of attached album
     * */
    public int attachAlbum() {
        for (int i = 0; i < mAlbumCoverList.size(); i++) {
            mContainer.addView(mAlbumCoverList.get(i));
        }
        return 0;
    }

    /**
     * addAlbum means ADD a new album into album list, waiting to attach
     * @return current number of album in list
     * */
    public int addAlbum(AlbumCover album) {
        mAlbumCoverList.add(album);
        return 0;
    }

    /**
     * The function is used to init the only child of ScrollView
     * */
    private void initContainer() {
        mContainer = new AlbumContainer(getContext());
        mContainer.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        addView(mContainer);
    }

    /**
     * AlbumContainer is a special LinearLayout that will only put ROW_MAXIMUM_ALBUM number
     * item into one row, the other items will move to the next row.
     * */
    private class AlbumContainer extends LinearLayout {
        /** Only ROW_MAXIMUM_ALBUM number item want to be put into the container */
        private static final int ROW_MAXIMUM_ALBUM = 3;
        public AlbumContainer(Context context) {
            this(context, null);
        }

        public AlbumContainer(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
//            super.onLayout(changed, l, t, r, b);
            int i;
            int paintPositionX, paintPositionY;

            int childCount = this.getChildCount();
            int childWidth, childHeight;
            int marginHorizontal, marginVertical;
            if (childCount < 1) {
                return;
            }

            /** Because all album size should be the same, use first child to measure dimension */
            childWidth = this.getChildAt(0).getMeasuredWidth();
            childHeight = this.getChildAt(0).getMeasuredHeight();
            if (getMeasuredWidth() < ROW_MAXIMUM_ALBUM * childWidth) {
                return;
            }
            /** All margins are set to equal. 3 items contain 4 margins, including start and end */
            marginHorizontal = (getMeasuredWidth() - ROW_MAXIMUM_ALBUM * childWidth) / (ROW_MAXIMUM_ALBUM + 1);
            marginVertical = marginHorizontal;

            for (i = 0; i < childCount; i++) {
                View childView = this.getChildAt(i);
                /** X is determined by index 0, 1, 2 in one row, using % */
                paintPositionX = l + marginHorizontal + (i % ROW_MAXIMUM_ALBUM) * (childWidth + marginHorizontal);
                /** Y is determined by which column is belongs to, using / */
                paintPositionY = t + marginVertical + (i / ROW_MAXIMUM_ALBUM) * (childHeight + marginVertical);
                childView.layout(paintPositionX, paintPositionY, paintPositionX + childWidth, paintPositionY + childHeight);
            }
        }
    }

    /**
     * AlbumCover is the layout to be put into album cover and control the appearance of album
     * */
    public class AlbumCover extends LinearLayout {
        /** The VIEW_RES_ID is used to inflate */
        private static final int VIEW_RES_ID = R.layout.widget_album_cover;

        private ViewGroup mAlbumCoverContainer;
        public AlbumCover(Context context) {
            this(context, null);
        }

        public AlbumCover(Context context, AttributeSet attrs) {
            super(context, attrs);
            setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            mAlbumCoverContainer = (ViewGroup) ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(VIEW_RES_ID, null);
            addView(mAlbumCoverContainer);
        }

        public void setAlbumCoverImage() {

        }

        public void setAlbumDescription() {

        }
    }
}
