package com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.controller;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.albumpage.AlbumActivity;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.BirthdayCardActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BirthdayCardFragment extends Fragment {
    private static final int VIEW_RES_ID = R.layout.fragment_birthday_card;
    private static final int VIEW_IMAGE_RES_ID = R.id.image_swallow_and_panda;

    View mImageView;
    public BirthdayCardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(VIEW_RES_ID, container, false);
        mImageView = mainView.findViewById(VIEW_IMAGE_RES_ID);
        initOnImageSwallowAndPandaClick();
        // Inflate the layout for this fragment
        return mainView;
    }

    private void initOnImageSwallowAndPandaClick() {
        final Activity activity = getActivity();
        if (mImageView == null) {
            return;
        }
        Log.d("BirthdayCardFragment", "initOnImageSwallowAndPandaClick");
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    Log.d("BirthdayCardFragment", "on click");
                    ((BirthdayCardActivity) getActivity()).goActivity(AlbumActivity.class, v);
                } else {
                    Log.d("BirthdayCardFragment", "activity null");
                }
            }
        });
    }

}
