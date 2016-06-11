package com.studio.swallowcharchar.happybirthday2016.widget;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Swallow on 6/12/16.
 */
public class Utility {
    public static void setAllParentsClip(View v, boolean enabled) {
        while (v.getParent() != null && v.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v.getParent();
            viewGroup.setClipChildren(enabled);
            viewGroup.setClipToPadding(enabled);
            v = viewGroup;
        }
    }
}
