package com.studio.swallowcharchar.happybirthday2016.database;

import android.content.Context;

import com.studio.swallowcharchar.happybirthday2016.R;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Swallow on 7/8/16.
 */
public class Database {
    public static final int JSON_ALBUM = 0x1;

    private static final int RAW_ALBUM_JSON_RES_ID = R.raw.database_album;

    private ArrayList<Album> mAlbumArrayList;
    public Database() {
    }

    public ArrayList loadJson(Context context, int index) {
        switch (index) {
            case JSON_ALBUM:
                mAlbumArrayList = load(Album[].class, context.getResources().openRawResource(RAW_ALBUM_JSON_RES_ID));
                return mAlbumArrayList;
            default:
                return null;
        }
    }

    private <T> ArrayList<T> load(Class<T[]> classOfT, InputStream inputStream) {

        BufferedReader bfdReader = null;
        try {
            bfdReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (bfdReader == null) throw new NullPointerException();

        String readLine;
        StringBuilder builder = new StringBuilder();
        try {
            while ((readLine = bfdReader.readLine()) != null) {
                builder.append(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bfdReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        T[] tArray = new Gson().fromJson(builder.toString(), classOfT);
        return new ArrayList<T>(Arrays.asList(tArray));
    }
}