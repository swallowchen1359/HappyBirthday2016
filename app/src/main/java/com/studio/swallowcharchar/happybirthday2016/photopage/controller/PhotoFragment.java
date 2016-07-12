package com.studio.swallowcharchar.happybirthday2016.photopage.controller;


import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.photopage.model.PhotoModel;
import com.studio.swallowcharchar.happybirthday2016.photopage.view.PhotoView;

import java.io.File;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int VIEW_RES_ID = R.layout.fragment_photo;
    private static final int VIEW_ALBUM_RES_ID = R.id.photo_view;
    private static final int STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID = R.string.bundle_album_clicked_position;

    private static final int URL_LOADER = 0;

    private PhotoView mPhotoView;
    private PhotoModel mModel;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /** Inflate the layout for this fragment */
        View mainView = inflater.inflate(VIEW_RES_ID, container, false);
        String bundleStr = getResources().getString(STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID);
        final int albumOnClickPosition = getArguments().getInt(bundleStr, 0);
        /** Loader testing */
        getLoaderManager().initLoader(URL_LOADER, null, this);

        /** find PhotoView */
        mPhotoView = (PhotoView) mainView.findViewById(VIEW_ALBUM_RES_ID);
        if (mModel != null && mPhotoView != null) {
//            mPhotoView.setCover(mModel.getPhotoCoverId(albumOnClickPosition));
            mPhotoView.setTags(mModel.getPhotoTagList(albumOnClickPosition));
            /** PhotoView.setPhotos(PhotoPictures) */
            mPhotoView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPhotoView.setPhotos(mModel.getPhotoPictureList(albumOnClickPosition));
                }
            }, 600);
        } else {
            Log.d("PhotoFragment", "Model or View is null");
        }
        return mainView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mModel = new PhotoModel(activity);
        mModel.initModel();
    }

    /**
     * Testing
     * */
    /***************************** LoaderManager.LoaderCallbacks **********************************/
    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle args) {
        /**
         * Testing
         * */
        Uri mDataUrl = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        final String[] mProjection = new String[] {
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.DISPLAY_NAME
        };
        /**
         * Takes action based on the ID of the Loader that's being created
         */
        switch (loaderID) {
            case URL_LOADER:
                // Returns a new CursorLoader
                return new CursorLoader(
                        getActivity(),   // Parent activity context
                        mDataUrl,        // Table to query
                        mProjection,     // Projection to return
                        null,            // No selection clause
                        null,            // No selection arguments
                        null             // Default sort order
                );
            default:
                // An invalid id was passed in
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d("PhotoFragment", "Row count " + data.getCount());
        data.moveToFirst();
        Log.d("PhotoFragment", "Row count " + data.getCount());
        Uri imgUri;
        Bitmap bitmap;
        for (int i = 0; i < 1; i++) {
            data.moveToNext();
            Log.d("PhotoFragment" ,data.getString(0));
            Log.d("PhotoFragment" ,data.getString(1));
//            imgUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Uri.encode(data.getString(0)));
            File file = new File(data.getString(0));
            imgUri = Uri.fromFile(file);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imgUri);
                mPhotoView.setCover(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
