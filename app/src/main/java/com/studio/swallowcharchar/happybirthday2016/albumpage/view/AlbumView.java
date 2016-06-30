package com.studio.swallowcharchar.happybirthday2016.albumpage.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
    private HashMap<Integer, Object> mAlbumHashMap;
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
        mAlbumHashMap = new HashMap<>();
        mAlbumAdapter = new AlbumAdapter(mAlbumLinkedList);
        setAdapter(mAlbumAdapter);
    }
}
