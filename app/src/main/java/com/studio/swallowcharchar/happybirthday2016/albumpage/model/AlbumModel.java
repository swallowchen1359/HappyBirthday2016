package com.studio.swallowcharchar.happybirthday2016.albumpage.model;

import android.content.Context;

import com.studio.swallowcharchar.happybirthday2016.database.Album;
import com.studio.swallowcharchar.happybirthday2016.database.Database;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Swallow on 6/22/16.
 */
public class AlbumModel {

    private Context mContext;

    /**
     * mAlbumArrayList is the array of Album, which is object related to Database
     * mAlbumArrayList will be initialized by private function initModel().
     * All Album related information is from mAlbumArrayList(index)
     * */
    private ArrayList<Album> mAlbumArrayList;

    /**
     * mAlbumPictureList is the list contains AlbumCard picture to show in AlbumCardView
     * mAlbumPictureList will be returned by calling getAlbumPictureList()
     * */
    private LinkedList<Integer> mAlbumPictureList;

    /**
     * mAlbumTitleList is the list contains AlbumCard title to show.
     * */
    private LinkedList<String> mAlbumTitleList;

    /**
     * mAlbumDescription is the list contains AlbumCard description.
     * TODO: Maybe we should use InputStream instead
     * */
    private LinkedList<String> mAlbumDescriptionList;
    public AlbumModel(Context context) {
        mContext = context;
        mAlbumPictureList = new LinkedList<>();
        mAlbumTitleList = new LinkedList<>();
        mAlbumDescriptionList = new LinkedList<>();
    }

    public LinkedList<Integer> getAlbumPictureList() {
        if (mContext == null || mAlbumArrayList == null) {
            return null;
        }
        /**
         * loadJson(Context context, int index) is used to parsing JSON file by GSON, according to
         * entered index.
         * */
        for (int i = 0; i < mAlbumArrayList.size(); i++) {
            mAlbumPictureList.add(mContext.getResources().getIdentifier(mAlbumArrayList.get(i).getAlbumResName(), "mipmap", mContext.getPackageName()));
        }
        return mAlbumPictureList;
    }

    public LinkedList<String> getAlbumTitleList() {
        if (mContext == null || mAlbumArrayList == null) {
            return null;
        }
        for (int i = 0; i < mAlbumArrayList.size(); i++) {
            mAlbumTitleList.add(mAlbumArrayList.get(i).getTitle());
        }
        return mAlbumTitleList;
    }

    public LinkedList<String> getAlbumDescriptionList() {
        if (mContext == null || mAlbumArrayList == null) {
            return null;
        }
        for (int i = 0; i < mAlbumArrayList.size(); i++) {
            mAlbumDescriptionList.add(mAlbumArrayList.get(i).getDescription());
        }
        return mAlbumDescriptionList;
    }

    public boolean initModel() {
        mAlbumArrayList = new Database().loadJson(mContext, Database.JSON_ALBUM);
        return mAlbumArrayList != null ;
    }
}
