package com.studio.swallowcharchar.happybirthday2016.photopage.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.swallowcharchar.happybirthday2016.R;
import com.studio.swallowcharchar.happybirthday2016.photopage.view.PhotoDialogView;
import com.studio.swallowcharchar.happybirthday2016.widget.DialogFragment;
import com.studio.swallowcharchar.happybirthday2016.widget.DialogView;

/**
 * Created by Swallow on 7/14/16.
 */
public class PhotoDialogFragment extends DialogFragment implements PhotoDialogView.OnBackgroundClickListener {

    public static final int STRING_BUNDLE_DIALOG_STYLE_RES_ID = R.string.bundle_dialog_style;

    private static final int VIEW_DIALOG_RES_ID_s[] = {
        0, // for debugging
        R.layout.view_photo_chg_cover_dialog,
        R.layout.view_photo_chg_time_dialog,
        R.layout.view_photo_chg_place_dialog,
        R.layout.view_photo_chg_gallery_dialog
    };

    private static final int TAG_DIALOG_RES_ID_s[] = {
        0, // for debugging
        R.string.tag_photo_chg_cover_dialog,
        R.string.tag_photo_chg_time_dialog,
        R.string.tag_photo_chg_place_dialog,
        R.string.tag_photo_chg_gallery_dialog
    };

    private int mStyleIndex;
    private String mPhotoDialogTag;
    private PhotoDialogView mPhotoDialogView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String bundleStr = getResources().getString(STRING_BUNDLE_DIALOG_STYLE_RES_ID);
            mStyleIndex = getArguments().getInt(bundleStr, 0);
            mPhotoDialogTag = getResources().getString(TAG_DIALOG_RES_ID_s[mStyleIndex]);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("PhotoDialogFragment", "mStyleIndex " +mStyleIndex);
        mPhotoDialogView = (PhotoDialogView) inflater.inflate(VIEW_DIALOG_RES_ID_s[mStyleIndex], container, false);
        mPhotoDialogView.setOnBackgroundClickListener(this);
        return mPhotoDialogView;
    }

    @Override
    protected DialogView getDialogView() {
        return mPhotoDialogView;
    }

    @Override
    public String getCallerTag() {
        return mPhotoDialogTag;
    }

    @Override
    public void onBackgroundClick() {
        getActivity().onBackPressed();
    }
}
