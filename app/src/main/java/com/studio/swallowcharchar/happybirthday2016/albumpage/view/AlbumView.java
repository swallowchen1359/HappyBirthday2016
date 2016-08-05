package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.studio.swallowcharchar.happybirthday2016.R;
import java.util.LinkedList;

public class AlbumView extends ScrollView {

    public interface OnCardClickListener {
        void onCardImageClick(int index, ImageView sharedImageView);
    }

    /**
     * VIEW_CARD_RES_ID will be inflated, which root is ViewCardView
     * */
    private static final int VIEW_CONTAINER_RES_ID = R.id.album_view_container;
    private static final int VIEW_CARD_RES_ID = R.layout.view_album_card;

    private LinearLayout mContainer;

    private LinkedList<AlbumCardView> mAlbumLinkedList;

    private OnCardClickListener mOnCardClickListener;

    public AlbumView(Context context) {
        this(context, null);
    }

    public AlbumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mAlbumLinkedList = new LinkedList<>();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mContainer == null) {
            mContainer = (LinearLayout) findViewById(VIEW_CONTAINER_RES_ID);
        }
    }

    public void setAlbum(Bitmap bitmap, String title, String description, LinkedList<Integer> photos, String place, LinkedList<Integer> time, LinkedList<String> tags) {
        AlbumCardView v = (AlbumCardView) ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(VIEW_CARD_RES_ID, null, true);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 10);
        v.setLayoutParams(layoutParams);
        v.setAlbumTitle(title);
        v.setAlbumDescription(description);
        v.setAlbumImage(bitmap);
        v.setAlbumPlace(place);
        v.setAlbumTime(time);
        v.setAlbumTags(tags);
        mAlbumLinkedList.add(v);
        mContainer.addView(v);
    }

    public void setOnCardClickListener(OnCardClickListener listener) {
        mOnCardClickListener = listener;
    }
}
