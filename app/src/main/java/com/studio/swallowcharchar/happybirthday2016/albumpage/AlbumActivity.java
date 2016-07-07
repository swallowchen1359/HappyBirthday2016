package com.studio.swallowcharchar.happybirthday2016.albumpage;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.albumpage.controller.AlbumFragment;

public class AlbumActivity extends Activity {
    private static final int VIEW_RES_ID = R.layout.activity_album;
    private static final int VIEW_CONTAINER_RES_ID = R.id.album_fragment_container;
    private static final int STRING_TAG_ALBUM_FRAGMENT_RES_ID = R.string.tag_album_fragment;

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

    public void goActivity(Class activityClass/*, View view*/) {
        Intent intent = new Intent(this, activityClass);
/*
        View sharedView = view;
        TODO: The code is used to transfer the shared image
        String sharedString = getResources().getString(STRING_TRANSITION_IMAGE_SWALLOW_AND_PANDA_RES_ID);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, sharedString);
        startActivity(intent, options.toBundle());
*/
        startActivity(intent);
    }
}
