package com.studio.swallowcharchar.happybirthday2016.photopage.model;

import android.content.Context;

import com.studio.swallowcharchar.happybirthday2016.database.Album;
import com.studio.swallowcharchar.happybirthday2016.database.Database;
import com.studio.swallowcharchar.happybirthday2016.database.Photo;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Swallow on 7/8/16.
 */
public class PhotoModel {

    private Context mContext;
    /**
     * Album is needed because we need to know which photos this album contains
     * */
    private ArrayList<Album> mAlbumArrayList;
    /**
     * Photo is needed of course because we need the photo resource name to get image
     * */
    private ArrayList<Photo> mPhotoArrayList;
    /**
     * PhotoPictureList is used to let PhotoView and PhotoFragment to show photos
     * */
    private LinkedList<Integer> mPhotoPictureList;

    public PhotoModel(Context context) {
        mContext = context;
        mPhotoPictureList = new LinkedList<>();
    }

    /**
     * @param position is the position according to AlbumView
     * {@link com.studio.swallowcharchar.happybirthday2016.database.Photo}
     * */
    public LinkedList<Integer> getPhotoPictureList(int position) {
        if (mContext == null || mPhotoArrayList == null || mAlbumArrayList == null) {
            return null;
        }
        /** Get the clicked album in AlbumFragment */
        Album album = mAlbumArrayList.get(position);
        /** Get the photoIds of picked album */
        int photoIds[] = album.getPhotoResIds();
        /** Load the resource and add them into PhotoPictureList and return according to photos[] */
        for (int i = 0; i < photoIds.length; i++) {
            /** 1. Get photoId */
            int photoId = photoIds[i];
            Photo photoObject = null;
            /** 2. Get Photo object from mPhotoArrayList by comparing photoId */
            for (int j = 0; j < mPhotoArrayList.size(); j++) {
                if (mPhotoArrayList.get(j).getPhotoId() == photoId) {
                    photoObject = mPhotoArrayList.get(j);
                    break;
                }
            }
            /** 3. Load the resource by photoResName ( using photoObject.getPhotoResName() ) */
            if (photoObject != null) {
                mPhotoPictureList.add(mContext.getResources().getIdentifier(photoObject.getPhotoResName(), "mipmap", mContext.getPackageName()));
            }
        }
        return mPhotoPictureList;
    }

    public int getPhotoCoverId(int position) {
        if (mContext == null || mPhotoArrayList == null || mAlbumArrayList == null) {
            return 0;
        }
        Album album = mAlbumArrayList.get(position);
        String albumResName = album.getAlbumResName();
        return mContext.getResources().getIdentifier(albumResName, "mipmap", mContext.getPackageName());
    }
    public boolean initModel() {
        mAlbumArrayList = new Database().loadJson(mContext, Database.JSON_ALBUM);
        mPhotoArrayList = new Database().loadJson(mContext, Database.JSON_PHOTO);
        return mPhotoArrayList != null ;
    }
}
