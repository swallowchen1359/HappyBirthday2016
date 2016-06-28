package com.studio.swallowcharchar.happybirthday2016.albumpage.model;

import com.studio.swallowcharchar.happybirthday2016.R;

import java.util.LinkedList;

/**
 * Created by Swallow on 6/22/16.
 */
public class AlbumModel {

    private static final int PICTURE_RES_ID_s[] = {
            R.drawable.dsc_0052,
            R.drawable.dsc_0156,
            R.drawable.dsc_0238,
            R.drawable.dsc_0867,
            R.drawable.dsc_0872,
            R.drawable.dsc_0923,
            R.drawable.dsc_0937
    };

    private LinkedList<Integer> mAlbumPictureList;
    public AlbumModel() {
        mAlbumPictureList = new LinkedList<>();
    }

    public LinkedList<Integer> getAlbumPictureList() {
        for (int i = 0; i < PICTURE_RES_ID_s.length; i++) {
            mAlbumPictureList.add(PICTURE_RES_ID_s[i]);
        }
        return mAlbumPictureList;
    }

    public int[] getAlbumPictureArray() {
        return PICTURE_RES_ID_s;
    }

    public boolean initModel() {
        return true;
    }
}
