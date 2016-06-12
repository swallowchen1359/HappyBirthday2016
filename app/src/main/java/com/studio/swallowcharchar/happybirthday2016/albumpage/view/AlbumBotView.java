package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.studio.swallowcharchar.happybirthday2016.MainBotView;
import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.widget.PopScrollView;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * AlbumBotView extends ScrollView will contains only one child view, LinearLayout,
 * which is used to put all widget_album_cover
 */
public class AlbumBotView extends LinearLayout implements MainBotView {
    public interface AlbumOnClickListener {
        void albumOnClickListener(int albumIndex);
    }
    private AlbumContainer mAlbumContainer;
    private AlbumDetailScrollView mAlbumDetailScrollView;
    private LinkedList<AlbumCover> mAlbumCoverList;
    private AlbumOnClickListener mAlbumOnClickListener;

    public AlbumBotView(Context context) {
        this(context, null);
    }

    public AlbumBotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mAlbumCoverList = new LinkedList<>();
        initAlbumContainer();
        mAlbumContainer.addAlbum(new AlbumCover(context));
        mAlbumContainer.addAlbum(new AlbumCover(context));
        mAlbumContainer.addAlbum(new AlbumCover(context));
        mAlbumContainer.addAlbum(new AlbumCover(context));
        mAlbumContainer.addAlbum(new AlbumCover(context));
        mAlbumContainer.addAlbum(new AlbumCover(context));
        mAlbumContainer.addAlbum(new AlbumCover(context));
        mAlbumContainer.addAlbum(new AlbumCover(context));
        mAlbumContainer.addAlbum(new AlbumCover(context));
        mAlbumContainer.addAlbum(new AlbumCover(context));
        mAlbumContainer.attachAlbum();
    }

    public void setAlbumOnClickListener(AlbumOnClickListener listener) {
        mAlbumOnClickListener = listener;
    }

    public AlbumContainer getAlbumContainer() {
        return mAlbumContainer;
    }

    public AlbumDetailScrollView getAlbumDetailScrollView() {
        return mAlbumDetailScrollView;
    }

    /**
     *
     * */
    public void initAlbumContainer() {
        mAlbumContainer = new AlbumContainer(getContext());
        addView(mAlbumContainer);
    }

    public void initAlbumDetailScrollView() {
        mAlbumDetailScrollView = new AlbumDetailScrollView(getContext());
        addView(mAlbumDetailScrollView);
    }

    /**
     * AlbumContainer is a vertical arrangement LinearLayout, can put a lots of row of AlbumRowContainer
     * */
    public class AlbumContainer extends ScrollView {
        private LinearLayout mContainer;
        public AlbumContainer(Context context) {
            this(context, null);
        }

        public AlbumContainer(Context context, AttributeSet attrs) {
            super(context, attrs);
            setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            this.mContainer = new LinearLayout(context);
            mContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            mContainer.setOrientation(VERTICAL);
            addView(mContainer);
        }

        public void addNewAlbumRowContainer(AlbumRowContainer albumRowContainer) {
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(0, 0, 0, 20);
            albumRowContainer.setLayoutParams(layoutParams);
            mContainer.addView(albumRowContainer);
        }

        /**
         * attachAlbum means PUT already existing album into AlbumContainer to SHOW.
         * @return number of attached album
         * */
        public int attachAlbum() {
            AlbumCover albumCover;
            AlbumRowContainer albumRowContainer = new AlbumRowContainer(getContext());
            for (int i = 0; i < mAlbumCoverList.size(); i++) {
                if (albumRowContainer.getChildCount() == AlbumRowContainer.ROW_MAXIMUM_ALBUM) {
                    albumRowContainer = new AlbumRowContainer(getContext());
                    albumRowContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                }
                final int albumIndex = i;
                albumCover = mAlbumCoverList.get(i);
                albumCover.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAlbumOnClickListener != null) {
                            mAlbumOnClickListener.albumOnClickListener(albumIndex);
                        }
                    }
                });
                albumRowContainer.addNewAlbumCover(albumCover);
                if (albumRowContainer.getChildCount() == AlbumRowContainer.ROW_MAXIMUM_ALBUM || i == mAlbumCoverList.size() - 1) {
                    addNewAlbumRowContainer(albumRowContainer);
                }
            }
            return 0;
        }

        /**
         * addAlbum means ADD a new album into album list, waiting to attach
         * @return current number of album in list
         * */
        public int addAlbum(AlbumCover album) {
            mAlbumCoverList.add(album);
            return mAlbumCoverList.size();
        }

        public AlbumCover getAlbumCoverAt(int albumIndex) {
            if (albumIndex < 0 || albumIndex > mAlbumCoverList.size()) {
                return null;
            }

            return mAlbumCoverList.get(albumIndex);
        }
    }

    /** AlbumRowContainer is a horizontal arrangement LinearLayout, can put 3 AlbumCovers */
    private class AlbumRowContainer extends LinearLayout {
        private static final int ROW_MAXIMUM_ALBUM = 3;
        private int mAlbumCoverInRowCount;
        public AlbumRowContainer(Context context) {
            this(context, null);
        }

        public AlbumRowContainer(Context context, AttributeSet attrs) {
            super(context, attrs);
            setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            setOrientation(HORIZONTAL);
            mAlbumCoverInRowCount = 0;
        }

        public void addNewAlbumCover(AlbumCover albumCover) {
            LayoutParams layoutParams;
            if (mAlbumCoverInRowCount >= ROW_MAXIMUM_ALBUM) {
                return;
            }
            layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
            albumCover.setGravity(Gravity.CENTER_HORIZONTAL);
            albumCover.setLayoutParams(layoutParams);
            addView(albumCover);
            mAlbumCoverInRowCount++;
        }
    }

    /**
     * AlbumCover is the layout to be put into album cover and control the appearance of album
     * */
    public class AlbumCover extends LinearLayout {
        /** The VIEW_RES_ID is used to inflate */
        private static final int VIEW_RES_ID = R.layout.widget_album_cover;
        private static final int VIEW_ALBUM_COVER_IMG_RES_ID = R.id.album_cover_image;
        private static final int ALBUM_WIDTH_RES_ID = R.dimen.album_cover_width;
        private static final int ALBUM_HEIGHT_RES_ID = R.dimen.album_cover_height;

        private ViewGroup mAlbumCoverContainer;
        public AlbumCover(Context context) {
            this(context, null);
        }

        public AlbumCover(Context context, AttributeSet attrs) {
            super(context, attrs);
            int albumWidth, albumHeight;

            setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            mAlbumCoverContainer = (ViewGroup) ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(VIEW_RES_ID, null, false);
            albumWidth = (int) (getResources().getDimension(ALBUM_WIDTH_RES_ID));
            albumHeight = (int) (getResources().getDimension(ALBUM_HEIGHT_RES_ID));
            mAlbumCoverContainer.setLayoutParams(new LayoutParams(albumWidth, albumHeight));
            addView(mAlbumCoverContainer);
        }

        public void setAlbumCoverImage() {

        }

        public void setAlbumDescription() {

        }

        public View getAlbumCoverImage() {
            return mAlbumCoverContainer.findViewById(VIEW_ALBUM_COVER_IMG_RES_ID);
        }
    }

    public class AlbumDetailScrollView extends PopScrollView {
        private final int ALBUM_DETAIL_ITEM_LAYOUT_RES_ID = R.layout.view_album_detail_item;
        public AlbumDetailScrollView(Context context) {
            this(context, null);
        }

        public AlbumDetailScrollView(Context context, AttributeSet attrs) {
            super(context, attrs);
            setShowingDetails(false);
            LinkedList<HashMap> ll = new LinkedList<>();
            String emptyString[] = new String[2];
            int emptyInteger[] = new int[2];
            for (int i = 0; i < 10; i++) {
                ll.add(new HashMap());
            }
            setPopItemAdapter(getPopItemAdapter(context, ALBUM_DETAIL_ITEM_LAYOUT_RES_ID, ll, emptyString, emptyInteger), 0);
        }

        @Override
        protected View getItemDetailView() {
            return null;
        }
    }
}
