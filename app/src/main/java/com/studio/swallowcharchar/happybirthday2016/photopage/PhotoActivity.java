package com.studio.swallowcharchar.happybirthday2016.photopage;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.photopage.controller.PhotoFragment;

public class PhotoActivity extends Activity {

    private static final int VIEW_RES_ID = R.layout.activity_photo;
    private static final int VIEW_CONTAINER_RES_ID = R.id.photo_fragment_container;
    private static final int STRING_TAG_PHOTO_FRAGMENT_RES_ID = R.string.tag_photo_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(VIEW_RES_ID);
        attachFragment();
    }

    private void attachFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PhotoFragment albumFragment = new PhotoFragment();
        String fragmentTag = getResources().getString(STRING_TAG_PHOTO_FRAGMENT_RES_ID);

        fragmentTransaction.add(VIEW_CONTAINER_RES_ID, albumFragment, fragmentTag);
        fragmentTransaction.commit();
    }
}
