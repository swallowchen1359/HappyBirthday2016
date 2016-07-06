package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Swallow on 7/1/16.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private LinkedList<HashMap> mLinkedList;

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
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
        AlbumCardView v = new AlbumCardView(parent.getContext());
        /** CardView size is specified in xml */
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 10);
        v.setLayoutParams(layoutParams);
        AlbumViewHolder vh = new AlbumViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.mAlbumCardView.setAlbumDescription((String) mLinkedList.get(position).get(AlbumCardView.KEY_DESCRIPTION));
        holder.mAlbumCardView.setAlbumTitle((String) mLinkedList.get(position).get(AlbumCardView.KEY_TITLE));
        holder.mAlbumCardView.setAlbumImage((int) mLinkedList.get(position).get(AlbumCardView.KEY_IMG_RES_ID));
    }

    @Override
    public int getItemCount() {
        return mLinkedList.size();
    }
}
