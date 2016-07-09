package com.studio.swallowcharchar.happybirthday2016.photopage;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.photopage.controller.PhotoFragment;

public class PhotoActivity extends Activity {

    private static final int VIEW_RES_ID = R.layout.activity_photo;
    private static final int VIEW_CONTAINER_RES_ID = R.id.photo_fragment_container;
    private static final int STRING_TAG_PHOTO_FRAGMENT_RES_ID = R.string.tag_photo_fragment;
    private static final int STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID = R.string.bundle_album_clicked_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String bundleStr = getResources().getString(STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID);
        int albumOnClickPosition = intent.getIntExtra(bundleStr, 0);
        setContentView(VIEW_RES_ID);
        attachFragment(albumOnClickPosition);
    }

    /**
     * @param position Position is used to indicated which album
     *                 {@link com.studio.swallowcharchar.happybirthday2016.database.Album} is
     *                 clicked and the parameter is passed to PhotoFragment {@link PhotoFragment}
     *                 for showing Photos, according to Photo
     *                 {@link com.studio.swallowcharchar.happybirthday2016.database.Photo} object.
     *                 The object will contains a resName to get the corresponding picture.
     * */
    private void attachFragment(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String fragmentTag = getResources().getString(STRING_TAG_PHOTO_FRAGMENT_RES_ID);
        String bundleStr = getResources().getString(STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID);

        PhotoFragment photoFragment = new PhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(bundleStr, position);
        photoFragment.setArguments(bundle);

        fragmentTransaction.add(VIEW_CONTAINER_RES_ID, photoFragment, fragmentTag);
        fragmentTransaction.commit();
    }
}
