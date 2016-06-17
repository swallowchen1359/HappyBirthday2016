package com.studio.swallowcharchar.happybirthday2016.albumpage;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

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
}
