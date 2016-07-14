package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.widget.ImageUtility;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * AlbumView contains lots of AlbumCardView, see {@link AlbumCardView}, by using AlbumAdapter
 * , see {@link AlbumAdapter}
 */
public class AlbumView extends RecyclerView {

    public interface OnCardClickListener {
        void onCardImageClick(int index, ImageView sharedImageView);
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

    public void setAlbums(LinkedList<Integer> resIds, LinkedList<String> titleStrings, LinkedList<String> descriptionStrings,
                            LinkedList<String> places, LinkedList<LinkedList<Integer>> times, LinkedList<LinkedList<String>> tags) {
        if (resIds == null) {
            return;
        }
        for (int i = 0; i < resIds.size(); i++) {
            HashMap<Integer, Object> hashMap = new HashMap<>();
            hashMap.put(AlbumCardView.KEY_IMG_RES_ID, resIds.get(i));
            hashMap.put(AlbumCardView.KEY_TITLE, titleStrings.get(i));
            hashMap.put(AlbumCardView.KEY_DESCRIPTION, descriptionStrings.get(i));
            hashMap.put(AlbumCardView.KEY_PLACE, places.get(i));
            hashMap.put(AlbumCardView.KEY_TIME, times.get(i));
            hashMap.put(AlbumCardView.KEY_TAGS, tags.get(i));
            mAlbumLinkedList.add(hashMap);
        }

        mAlbumAdapter.notifyDataSetChanged();
    }

    public void setOnCardClickListener(OnCardClickListener listener) {
        mOnCardClickListener = listener;
    }

    public class AddImageSyncTask extends AsyncTask<AlbumAdapter.AlbumViewHolder, Void, Bitmap> {

        private AlbumAdapter.AlbumViewHolder mViewHolder;
        private Resources mResources;
        /** LinkedList of HashMap, containing the information of CardView */
        private LinkedList<HashMap> mAsyncLinkedList;
        private int mIndex;

        public AddImageSyncTask(Resources res, LinkedList<HashMap> linkedList, int index) {
            mResources = res;
            mAsyncLinkedList = linkedList;
            mIndex = index;
        }

        @Override
        protected Bitmap doInBackground(AlbumAdapter.AlbumViewHolder... params) {
            mViewHolder = params[0];
            int resId = (int) mAsyncLinkedList.get(mIndex).get(AlbumCardView.KEY_IMG_RES_ID);
            return ImageUtility.decodeSampledBitmapFromResource(mResources, resId, 900, 900);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mViewHolder.mAlbumCardView.setAlbumDescription((String) mAsyncLinkedList.get(mIndex).get(AlbumCardView.KEY_DESCRIPTION));
            mViewHolder.mAlbumCardView.setAlbumTitle((String) mAsyncLinkedList.get(mIndex).get(AlbumCardView.KEY_TITLE));
            mViewHolder.mAlbumCardView.setAlbumImage(bitmap);
            mViewHolder.mAlbumCardView.setAlbumPlace((String) mAsyncLinkedList.get(mIndex).get(AlbumCardView.KEY_PLACE));
            mViewHolder.mAlbumCardView.setAlbumTime((LinkedList<Integer>) mAsyncLinkedList.get(mIndex).get(AlbumCardView.KEY_TIME));
            mViewHolder.mAlbumCardView.setAlbumTags((LinkedList<String>) mAsyncLinkedList.get(mIndex).get(AlbumCardView.KEY_TAGS));

            if (mOnCardClickListener != null) {
                final int index = mIndex;
                /**
                 * shared ImageView for transition
                 * */
                final ImageView sharedImageView = mViewHolder.mAlbumCardView.getAlbumImage();
                mViewHolder.mAlbumCardView.getAlbumImage().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnCardClickListener.onCardImageClick(index, sharedImageView);
                    }
                });
            }
        }
    }

    public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

        /**
         * VIEW_CARD_RES_ID will be inflated, which root is ViewCardView
         * */
        private static final int VIEW_CARD_RES_ID = R.layout.view_album_card;

        private LinkedList<HashMap> mLinkedList;

        public class AlbumViewHolder extends RecyclerView.ViewHolder {
            public AlbumCardView mAlbumCardView;
            public AlbumViewHolder(AlbumCardView view) {
                super(view);
                mAlbumCardView = view;
            }
        }

        public AlbumAdapter(LinkedList<HashMap> ll) {
            mLinkedList = ll;
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
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 10, 0, 10);
            v.setLayoutParams(layoutParams);
            AlbumViewHolder vh = new AlbumViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(AlbumViewHolder holder, int position) {
            new AddImageSyncTask(getResources(), mLinkedList, position).execute(holder);
        }

        @Override
        public int getItemCount() {
            return mLinkedList.size();
        }
    }
}
