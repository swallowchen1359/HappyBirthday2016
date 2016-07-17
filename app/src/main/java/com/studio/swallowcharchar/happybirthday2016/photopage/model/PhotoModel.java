package com.studio.swallowcharchar.happybirthday2016.photopage.model;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.studio.swallowcharchar.happybirthday2016.database.Album;
import com.studio.swallowcharchar.happybirthday2016.database.Database;
import com.studio.swallowcharchar.happybirthday2016.database.Photo;
import com.studio.swallowcharchar.happybirthday2016.widget.ImageUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Swallow on 7/8/16.
 */
public class PhotoModel implements LoaderManager.LoaderCallbacks<Cursor> {
    /**
     * database_photo.json :
     * -----------------------------------------------------------
     * * photoId      : Index for Album to identified photos.    *
     * * photoResName : Img file name, excluding path.           *
     * * source       : 0: from mipmap, 1: internal 2: external. *
     * * title        : Title for photo.                         *
     * * description  : Description for photo.                   *
     * * tags         : Tags of photos (for future use).         *
     * -----------------------------------------------------------
     */

    public interface TaskCallbacks {
        void onInitModelDone();
        void onLoadDone();
        void onBitmapCreateDone(Bitmap bitmap);
    }

    private static final int URL_LOADER = 0;


    private static final String[] STR_MEDIA_EXTERNAL_COLUMN_s = new String[] {
        MediaStore.Images.ImageColumns.DATA,
        MediaStore.Images.ImageColumns.DISPLAY_NAME,
    };

    private static final int IDX_MEDIA_EXTERNAL_DATA = 0x0;
    private static final int IDX_MEDIA_EXTERNAL_DISPLAY_NAME = 0x1;

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
     * PhotoTagList showing tags the same with tags in AlbumView
     * */
    private LinkedList<String> mPhotoTagList;

    /**
     * TaskCallbacks 
     */
    private TaskCallbacks mTaskCallbacks;

    /**
     * Cursor, assigned after onLoadFinished, used for searching the photo to show
     */
    private Cursor mImageMediaCursor;

    public PhotoModel(Context context) {
        mContext = context;
        mPhotoTagList = new LinkedList<>();
    }

    /**
     * @param position is the position according to AlbumView
     * The function will keep send Bitmap to callback function onBitmapCreateDone
     * TODO: Using AsyncTask to return bitmap
     * @return The last bitmap
     * */
    public Bitmap getPhotoPictureBitmap(int position) {
        if (mContext == null || mPhotoArrayList == null || mAlbumArrayList == null) {
            return null;
        }

        /** Get the clicked album in AlbumFragment */
        Album album = mAlbumArrayList.get(position);
        /** Get the photoIds of picked album */
        int photoIds[] = album.getPhotoResIds();
        /** 1. Get photoId from photoIds */
        mImageMediaCursor.moveToFirst();
        for (int i = 0; i < photoIds.length; i++) {
            Photo photoObject = null;
            int photoId = photoIds[i];
            /** 2. Get Photo object from mPhotoArrayList by comparing photoId */
            for (int j = 0; j < mPhotoArrayList.size(); j++) {
                if (mPhotoArrayList.get(j).getPhotoId() == photoId) {
                    photoObject = mPhotoArrayList.get(j);
                    break;
                }
            }
            /**
             * 3. Create an AsyncTask to load the resource by photoResName
             * */
            if (photoObject != null) {
                new AsyncTask<Photo, Void, Bitmap>() {

                    @Override
                    protected Bitmap doInBackground(Photo... params) {
                        Photo photo = params[0];
                        Bitmap bitmap;
                        /**
                         * Photo image may come from 3 places
                         * if mipmap, directly get mipmap
                         * else, get from cursor
                         */
                        if (photo.getSource() == Photo.SOURCE_MIPMAP) {
                            int resId = mContext.getResources().getIdentifier(photo.getPhotoResName(), "drawable", mContext.getPackageName());
                            bitmap = ImageUtility.decodeSampledBitmapFromResource(mContext.getResources(), resId, 300, 300);
                        } else {
                            bitmap = getBitmapFromCursor(photo.getPhotoResName());
                        }
                        return bitmap;
                    }

                    @Override
                    protected void onPostExecute(Bitmap bitmap) {
                        mTaskCallbacks.onBitmapCreateDone(bitmap);
                    }
                }.execute(photoObject);
            }
        }
        return null;
    }

    /**
     * @param position is the position according to AlbumView
     * {@link com.studio.swallowcharchar.happybirthday2016.database.Photo}
     * @return Bitmap contains bitmap of cover
     * */
    public Bitmap getPhotoCoverBitmap(int position) {
        if (mContext == null || mPhotoArrayList == null || mAlbumArrayList == null) {
            return null;
        }
        Album album = mAlbumArrayList.get(position);
        String albumResName = album.getAlbumResName();
        if (album.getSource() == Album.SOURCE_MIPMAP) {
            int resId = mContext.getResources().getIdentifier(albumResName, "drawable", mContext.getPackageName());
            return BitmapFactory.decodeResource(mContext.getResources(), resId, null);
        } else {
            return getBitmapFromCursor(albumResName);
        }
    }

    /**
     * @param position is the position according to AlbumView
     * {@link com.studio.swallowcharchar.happybirthday2016.database.Photo}
     * @return LinkedList contains tags for cover
     * */
    public LinkedList<String> getPhotoTagList(int position) {
        if (mContext == null || mAlbumArrayList == null) {
            Log.d("PhotoModel", "something null");
            return null;
        }
        Album album = mAlbumArrayList.get(position);
        String[] tags = album.getTags();
        for (int i = 0; i < tags.length; i++) {
            mPhotoTagList.add(tags[i]);
        }
        return mPhotoTagList;
    }

    public void setTaskCallbacks(TaskCallbacks callbacks) {
        mTaskCallbacks = callbacks;
    }

    public boolean initModel() {
        mAlbumArrayList = new Database().loadJson(mContext, Database.JSON_ALBUM);
        mPhotoArrayList = new Database().loadJson(mContext, Database.JSON_PHOTO);
        /** Awake Callback (In PhotoFragment) */
        if (mTaskCallbacks != null) {
            mTaskCallbacks.onInitModelDone();
        }
        return mPhotoArrayList != null ;
    }

    public void createPhotoLoader() {
        if (mContext == null) {
            return;
        }
        ((Activity)mContext).getLoaderManager().initLoader(URL_LOADER, null, this);
    }

    private Bitmap getBitmapFromCursor(String fileStr) {
        if (mImageMediaCursor == null) {
            return null;
        }
        Bitmap bitmap = null;

        for (mImageMediaCursor.moveToFirst(); !mImageMediaCursor.isAfterLast();mImageMediaCursor.moveToNext()) {
            /** TODO: use a constant value to identified what data to get */
            if (!mImageMediaCursor.getString(IDX_MEDIA_EXTERNAL_DISPLAY_NAME).equals(fileStr)) {
                continue;
            }
            File file = new File(mImageMediaCursor.getString(IDX_MEDIA_EXTERNAL_DATA));
            Uri imgUri = Uri.fromFile(file);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), imgUri);
                bitmap = ImageUtility.resizeBitmap(bitmap, 300, 300);
                break;
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /************************ LoaderManager.LoaderCallbacks<Cursor> ******************************/

    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle args) {
        Log.d("PhotoModel", "onCreateLoader");
        /** Fetch data from external content */
        Uri externalUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        /** Fetch corresponding columns according to STR_MEDIA_EXTERNAL_COLUMN_s by CursorLoader*/
        return new CursorLoader(mContext, externalUri, STR_MEDIA_EXTERNAL_COLUMN_s, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mImageMediaCursor = data;
        /** Awake Callback (In PhotoFragment) */
        if (mTaskCallbacks != null) {
            mTaskCallbacks.onLoadDone();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
