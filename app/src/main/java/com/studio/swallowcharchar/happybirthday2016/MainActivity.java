package com.studio.swallowcharchar.happybirthday2016;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public interface SwitchPageButtonListener {
        void buttonAlbumOnClick();
        void buttonTheaterOnClick();
    }

    private static final int VIEW_RES_ID = R.layout.activity_main;
    private static final int VIEW_MAIN_FG_RES_ID = R.id.main_fragment;
    private static final int VIEW_BUTTON_ALBUM = R.id.button_to_album;
    private static final int VIEW_BUTTON_THEATER = R.id.button_to_theater;

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

        initButtonListener();
    }

    private void initButtonListener() {
        View buttonAlbum = findViewById(VIEW_BUTTON_ALBUM);
        View buttonTheater = findViewById(VIEW_BUTTON_THEATER);

        if (buttonAlbum == null || buttonTheater == null || mMainFragment == null) {
            return;
        }

        buttonAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainFragment.buttonAlbumOnClick();
            }
        });

        buttonTheater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainFragment.buttonTheaterOnClick();
            }
        });
    }
}
