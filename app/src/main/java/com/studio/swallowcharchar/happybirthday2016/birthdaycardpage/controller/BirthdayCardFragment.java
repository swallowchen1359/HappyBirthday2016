package com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.controller;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.albumpage.AlbumActivity;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.BirthdayCardActivity;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.view.BirthdayCardBotView;
import com.studio.swallowcharchar.happybirthday2016.birthdaycardpage.view.BirthdayCardTopView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BirthdayCardFragment extends Fragment implements BirthdayCardTopView.OnPagerClickListener {
    private static final int VIEW_RES_ID = R.layout.fragment_birthday_card;
    private static final int VIEW_TOP_RES_ID = R.id.birthday_card_top;
    private static final int VIEW_BOT_RES_ID = R.id.birthday_card_bot;

    View mImageView;
    BirthdayCardTopView mTopView;
    BirthdayCardBotView mBotView;
    public BirthdayCardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(VIEW_RES_ID, container, false);
        mTopView = (BirthdayCardTopView) mainView.findViewById(VIEW_TOP_RES_ID);
        mBotView = (BirthdayCardBotView) mainView.findViewById(VIEW_BOT_RES_ID);
        mTopView.setOnPagerClickListener(this);
        initOnImageSwallowAndPandaClick();
        // Inflate the layout for this fragment
        return mainView;
    }

    private void initOnImageSwallowAndPandaClick() {
        final Activity activity = getActivity();
        if (mImageView == null) {
            return;
        }
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    ((BirthdayCardActivity) getActivity()).goActivity(AlbumActivity.class, v);
                }
            }
        });
    }

    /************************* BirthdayCardTopView.OnPagerClickListener ***************************/
    @Override
    public void onPagerClick(int position, View view) {
        ((BirthdayCardActivity) getActivity()).goActivity(AlbumActivity.class, view);
    }
}
