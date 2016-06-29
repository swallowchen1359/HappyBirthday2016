package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.support.v7.widget.CardView;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class AlbumCardView extends CardView {

    private AlbumAdapter mAlbumAdapter;

    public AlbumCardView(Context context) {
        this(context, null);
    }

    public AlbumCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Add an Album
     */
    public void addAlbum(AlbumChildView albumChildView) {

    }

    /**
     * Add an Albums with input LinkedList<AlbumChildView>
     */
    public void addAlbums(LinkedList<AlbumChildView> albumLinkedList) {
        if (albumLinkedList == null) {
            return;
        }
        for (int i = 0; i < albumLinkedList.size(); i++) {
            addView(albumLinkedList.get(i));
        }
    }

    public void setAdapter(AlbumAdapter adapter) {
        mAlbumAdapter = adapter;
        addAlbums(adapter.getView());
    }

    public AlbumAdapter getAdapter() {
        return mAlbumAdapter;
    }

    /**
     * AlbumAdapter is used for AlbumCardView to specify what content should be put into AlbumCardView
     */
    public class AlbumAdapter {
        private Context mContext;
        private int[] mImageResIds;
        private String[] mTitleStrings;
        private String[] mDescriptionStrings;
        private LinkedList<AlbumChildView> mAlbumChildViewLL;

        public AlbumAdapter(Context context, int[] imageResIds, String[] titleStrings, String[] descriptionStrings) {
            mContext = context;
            mImageResIds = imageResIds;
            mTitleStrings = titleStrings;
            mDescriptionStrings = descriptionStrings;
            mAlbumChildViewLL = new LinkedList<>();
        }

        /**
         * getView() will create a LinkedList of AlbumChildView, containing the content that Adapter get.
         * @return LinkedList<AlbumChildView>
         */
        protected LinkedList<AlbumChildView> getView() {
            for (int i = 0; i < mImageResIds.length; i++) {
                AlbumChildView albumView = new AlbumChildView(mContext);
                albumView.setAlbumImage(mImageResIds[i]);
                albumView.setAlbumTitle(mTitleStrings[i]);
                albumView.setAlbumDescription(mDescriptionStrings[i]);
                mAlbumChildViewLL.add(albumView);
            }
            return mAlbumChildViewLL;
        }
    }

    /**
     * AlbumChildView is the only child type can be put into AlbumCardView
     */
    public class AlbumChildView extends RelativeLayout {
        /**
         * AlbumChildView contains an ImageView
         */
        private ImageView mImageView;
        /**
         * AlbumChildView contains some TextViews for description
         */
        private TextView mTitleTextView, mDescriptionTextView;

        public AlbumChildView(Context context) {
            this(context, null);
        }

        public AlbumChildView(Context context, AttributeSet attrs) {
            super(context, attrs);
            /** Align and add the children here */
            mImageView = new ImageView(context);
            mTitleTextView = new TextView(context);
            mDescriptionTextView = new TextView(context);
        }

        /**
         * set Album Image with resId
         */
        public void setAlbumImage(int resId) {
            /** TODO: remember to use ImageUtility to resize the photo */
        }

        /**
         * set Album Title with string with limit length
         */
        public void setAlbumTitle(String string) {
            /** TODO: remember to limit the string length */
            mTitleTextView.setText(string);
        }

        /**
         * set Album Description with string with limit length
         */
        public void setAlbumDescription(String string) {
            /** TODO: remember to limit the string length */
            mDescriptionTextView.setText(string);
        }
    }
}