package com.studio.swallowcharchar.happybirthday2016.birthdaycardpage;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.controller.BirthdayCardFragment;

public class BirthdayCardActivity extends Activity {
    private static final int VIEW_RES_ID = R.layout.activity_birthday_card;
    private static final int VIEW_CONTAINER_RES_ID = R.id.birthday_card_fragment_container;
    private static final int STRING_TAG_BIRTHDAY_CARD_FRAGMENT_RES_ID = R.string.tag_birthday_card_fragment;
    private static final int STRING_TRANSITION_IMAGE_SWALLOW_AND_PANDA_RES_ID = R.string.transition_image_swallow_and_panda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(VIEW_RES_ID);
        attachMainFragment();
    }

    public void goActivity(Class activityClass, View view) {
        Intent intent = new Intent(this, activityClass);
        View sharedView = view;
        String sharedString = getResources().getString(STRING_TRANSITION_IMAGE_SWALLOW_AND_PANDA_RES_ID);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, sharedString);
        startActivity(intent, options.toBundle());
    }

    private void attachMainFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BirthdayCardFragment birthdayCardFragment = new BirthdayCardFragment();
        String fragmentTag = getResources().getString(STRING_TAG_BIRTHDAY_CARD_FRAGMENT_RES_ID);

        fragmentTransaction.add(VIEW_CONTAINER_RES_ID, birthdayCardFragment, fragmentTag);
        fragmentTransaction.commit();
    }
}
