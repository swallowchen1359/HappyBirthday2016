package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Swallow on 6/30/16.
 */
public class AlbumView extends RecyclerView {

    public interface OnCardClickListener {
        void onCardClick(int index, ImageView sharedImageView);
    }

    private AlbumAdapter mAlbumAdapter;
    private LinkedList<HashMap> mAlbumLinkedList;
    private LayoutManager mLayoutManager;

    private OnCardClickListener mOnCardClickListener;

    public AlbumView(Context context) {
        this(context, null);
    }

    public AlbumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(mLayoutManager);
        mAlbumLinkedList = new LinkedList<>();
        mAlbumAdapter = new AlbumAdapter(mAlbumLinkedList);
        setAdapter(mAlbumAdapter);
    }

    public void setAlbums(LinkedList<Integer> resIds, LinkedList<String> titleStrings, LinkedList<String> descriptionStrings) {
        if (resIds == null) {
            return;
        }
        for (int i = 0; i < resIds.size(); i++) {
            HashMap<Integer, Object> hashMap = new HashMap<>();
            hashMap.put(AlbumCardView.KEY_IMG_RES_ID, resIds.get(i));
            hashMap.put(AlbumCardView.KEY_TITLE, titleStrings.get(i));
            hashMap.put(AlbumCardView.KEY_DESCRIPTION, descriptionStrings.get(i));
            mAlbumLinkedList.add(hashMap);
        }

        mAlbumAdapter.notifyDataSetChanged();
    }

    public void setOnCardClickListener(OnCardClickListener listener) {
        mOnCardClickListener = listener;
    }

    public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

        /**
         * VIEW_CARD_RES_ID will be inflated, which root is ViewCardView
         * */
        private static final int VIEW_CARD_RES_ID = R.layout.view_album_card;

        private LinkedList<HashMap> mLinkedList;
        /**
         * mAlbumBitmapLinkedList is used to cache loaded bitmap, avoiding reloading and spend time
         * */
        private HashMap<Integer, Bitmap> mAlbumBitmapHashMap;

        public class AlbumViewHolder extends RecyclerView.ViewHolder {
            public AlbumCardView mAlbumCardView;
            public AlbumViewHolder(AlbumCardView view) {
                super(view);
                mAlbumCardView = view;
            }
        }

        public AlbumAdapter(LinkedList<HashMap> ll) {
            mLinkedList = ll;
            mAlbumBitmapHashMap = new HashMap<>();
        }

        /**
         * onCreateViewHolder will create a ViewHolder for next function : onBindViewHolder,
         * to put the content into the view
         * */
        @Override
        public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /** Create a CardView, belongs to parent*/
            AlbumCardView v = (AlbumCardView) ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(VIEW_CARD_RES_ID, null, true);
            /** CardView size is specified in xml */
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 10, 0, 10);
            v.setLayoutParams(layoutParams);
            AlbumViewHolder vh = new AlbumViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(AlbumViewHolder holder, int position) {
            Bitmap bitmap;
            holder.mAlbumCardView.setAlbumDescription((String) mLinkedList.get(position).get(AlbumCardView.KEY_DESCRIPTION));
            holder.mAlbumCardView.setAlbumTitle((String) mLinkedList.get(position).get(AlbumCardView.KEY_TITLE));
            /**
             * Set AlbumCardView's image.
             * If set before, using cache (Do not resample again)
             * Else, setAlbumImage with resId (Do resample)
             * */
            if (mAlbumBitmapHashMap != null) {
                if ((bitmap = mAlbumBitmapHashMap.get(position)) != null) {
                    holder.mAlbumCardView.setAlbumImage(bitmap);
                } else {
                    mAlbumBitmapHashMap.put(position, holder.mAlbumCardView.setAlbumImage((int) mLinkedList.get(position).get(AlbumCardView.KEY_IMG_RES_ID)));
                }
            }
            if (mOnCardClickListener != null) {
                final int index = position;
                /**
                 * shared ImageView for transition
                 * */
                final ImageView sharedImageView = holder.mAlbumCardView.getAlbumImage();
                holder.mAlbumCardView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnCardClickListener.onCardClick(index, sharedImageView);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mLinkedList.size();
        }
    }
}
