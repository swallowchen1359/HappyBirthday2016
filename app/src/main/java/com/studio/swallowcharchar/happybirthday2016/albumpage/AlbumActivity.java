package com.studio.swallowcharchar.happybirthday2016.albumpage;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.albumpage.controller.AlbumFragment;
import com.studio.swallowcharchar.happybirthday2016.photopage.model.PhotoModel;

public class AlbumActivity extends Activity {

    private static final int VIEW_RES_ID = R.layout.activity_album;
    private static final int VIEW_CONTAINER_RES_ID = R.id.album_fragment_container;
    private static final int STRING_TAG_ALBUM_FRAGMENT_RES_ID = R.string.tag_album_fragment;
    private static final int STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID = R.string.bundle_album_clicked_position;
    private static final int STRING_TRANSITION_ALBUM_AND_PHOTO = R.string.transition_image_album_and_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(VIEW_RES_ID);
        attachMainFragment();
    }

    private void attachMainFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AlbumFragment albumFragment = new AlbumFragment();
        String fragmentTag = getResources().getString(STRING_TAG_ALBUM_FRAGMENT_RES_ID);

        fragmentTransaction.add(VIEW_CONTAINER_RES_ID, albumFragment, fragmentTag);
        fragmentTransaction.commit();
    }

    /**
     * @param position Position is the indicator of which Album is clicked, which will be handled
     *                 by PhotoModel and get the PhotoObject
     * {@link PhotoModel}
     * */
    public void goActivity(Class activityClass, View view, int position) {
        Intent intent = new Intent(this, activityClass);
        String bundleStr = getResources().getString(STRING_BUNDLE_ALBUM_CLICKED_POSITION_RES_ID);
        intent.putExtra(bundleStr, position);

        View sharedView = view;
        /** TODO: The code is used to transfer the shared image */
//        String sharedString = getResources().getString(STRING_TRANSITION_ALBUM_AND_PHOTO);
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, sharedString);
//        startActivity(intent, options.toBundle());

        startActivity(intent);
    }
}
