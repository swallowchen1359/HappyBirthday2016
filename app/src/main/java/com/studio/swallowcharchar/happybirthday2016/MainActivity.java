package com.studio.swallowcharchar.happybirthday2016;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int VIEW_RES_ID = R.layout.activity_main;
    private static final int VIEW_MAIN_FG_RES_ID = R.id.main_fragment;

    private MainFragment mMainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FragmentManager fragmentManager;

        super.onCreate(savedInstanceState);
        setContentView(VIEW_RES_ID);
        mMainFragment = new MainFragment();
        fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction().add(VIEW_MAIN_FG_RES_ID, mMainFragment).commit();
        }
    }
}
