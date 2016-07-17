package com.studio.swallowcharchar.happybirthday2016.photopage.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.studio.swallowcharchar.happybirthday2016.R;

/**
 * Created by Swallow on 7/17/16.
 */
public class PhotoTimeDialogFragment extends PhotoDialogFragment {

    class PickedDate {
        private int mYear;
        private int mMonth;
        private int mDay;

        public PickedDate(int year, int month, int day) {
            mYear = year;
            /** Doesn't know why picked month always minus 1 */
            mMonth = month + 1;
            mDay = day;
        }

        public int getYear() {
            return mYear;
        }

        public int getMonth() {
            return mMonth;
        }

        public int getDay() {
            return mDay;
        }
    }

    private static final int VIEW_DATE_PICKER_RES_ID = R.id.photo_dialog_date_picker;

    private DatePicker mDatePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = super.onCreateView(inflater, container, savedInstanceState);
        mDatePicker = (DatePicker) mainView.findViewById(VIEW_DATE_PICKER_RES_ID);
        return mainView;
    }

    @Override
    public void onBackgroundClick() {
        int year = mDatePicker.getYear();
        int month = mDatePicker.getMonth();
        int day = mDatePicker.getDayOfMonth();
        OnDialogFinishListener listener = getOnDialogFinishListener();
        if (listener != null) {
            listener.onDialogFinish(new PickedDate(year, month, day));
        }
        super.onBackgroundClick();
    }
}
