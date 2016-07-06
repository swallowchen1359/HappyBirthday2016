package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Swallow on 6/30/16.
 */
public class AlbumView extends RecyclerView {
    private AlbumAdapter mAlbumAdapter;
    private LinkedList<HashMap> mAlbumLinkedList;
    private LayoutManager mLayoutManager;

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

    public void setAlbums(LinkedList<Integer> resIds) {
        if (resIds == null) {
            return;
        }
        for(int i = 0; i < resIds.size(); i++) {
            HashMap<Integer, Object> hashMap = new HashMap<>();
            hashMap.put(AlbumCardView.KEY_IMG_RES_ID, resIds.get(i));
            hashMap.put(AlbumCardView.KEY_DESCRIPTION, "");
            hashMap.put(AlbumCardView.KEY_TITLE, "");
            mAlbumLinkedList.add(hashMap);
        }

        mAlbumAdapter.notifyDataSetChanged();
    }
}
