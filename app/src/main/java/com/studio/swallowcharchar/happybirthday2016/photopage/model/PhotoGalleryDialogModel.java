package com.studio.swallowcharchar.happybirthday2016.photopage.model;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
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
 * Created by Swallow on 8/3/16.
 */
public class PhotoGalleryDialogModel implements LoaderManager.LoaderCallbacks<Cursor> {

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

    public PhotoGalleryDialogModel(Context context) {
        mContext = context;
        mPhotoTagList = new LinkedList<>();
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

    public void setTaskCallbacks(TaskCallbacks callbacks) {
        mTaskCallbacks = callbacks;
    }

    public void createPhotoLoader() {
        if (mContext == null) {
            return;
        }
        ((Activity)mContext).getLoaderManager().initLoader(URL_LOADER, null, this);
    }

    /**
     * The function is used to find the availabe picture in the device.
     * The function will keep send Bitmap to callback function onBitmapCreateDone
     * @return Bitmap according to Cursor
     * */
    public Bitmap getAvailableBitmap() {
        if (mImageMediaCursor == null) {
            return null;
        }
        for (mImageMediaCursor.moveToFirst(); !mImageMediaCursor.isAfterLast() && !mImageMediaCursor.isClosed();mImageMediaCursor.moveToNext()) {
            File file = new File(mImageMediaCursor.getString(IDX_MEDIA_EXTERNAL_DATA));
            Uri imgUri = Uri.fromFile(file);
            new AsyncTask<Uri, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Uri... params) {
                    Bitmap bitmap = null;
                    try {
                        Uri uri = params[0];
                        bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
                        bitmap = ImageUtility.resizeBitmap(bitmap, 200, 200);
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                    return bitmap;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    if (mTaskCallbacks != null) {
                        mTaskCallbacks.onBitmapCreateDone(bitmap);
                    }
                }
            }.execute(imgUri);
        }
        return null;
    }


    /**
     * The function is used to create Photo Object according to index mapping to cursor
     * The function will keep send Bitmap to callback function onBitmapCreateDone
     * @return Bitmap according to Cursor
     * */
    public Photo getPhotoFromCursorByIndex(int index) {
        if (mImageMediaCursor == null) {
            return null;
        }
        /** Move index of Cursor to @param index */
        mImageMediaCursor.moveToFirst();
        mImageMediaCursor.move(index);
        /** Create Photo Object and put it into Photo database and Album database */
        String string = mImageMediaCursor.getString(IDX_MEDIA_EXTERNAL_DATA);
        Log.d("PhotoDialogModel", string);
        /** Remember to search to avoid duplicate Photo item in database */
        return null;
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
