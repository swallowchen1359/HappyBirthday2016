package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;

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
        v.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        AlbumViewHolder vh = new AlbumViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.mAlbumCardView.setAlbumDescription("TestDescription");
        holder.mAlbumCardView.setAlbumTitle("TestTitle");
        holder.mAlbumCardView.setAlbumImage(R.mipmap.swallow_and_panda);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
