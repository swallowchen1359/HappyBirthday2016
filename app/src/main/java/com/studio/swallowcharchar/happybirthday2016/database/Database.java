package com.studio.swallowcharchar.happybirthday2016.database;

import android.content.Context;

import com.studio.swallowcharchar.happybirthday2016.R;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    public static final int JSON_PHOTO = 0x2;
    public static final int JSON_ALBUM_INTERNAL = 0x3;
    public static final int JSON_PHOTO_INTERNAL = 0x4;

    private static final int RAW_JSON_RES_ID_s[] = {
        0,
        R.raw.database_album,
        R.raw.database_photo,
        0,
        0
    };
    
    private static final String FILE_JSON_STRING_s[] = {
        "",
        "",
        "",
        "database_album.json",      /** internal file, not raw file */
        "database_photo.json"       /** internal file, not raw file */
    };

    public Database() {
    }

    /**
     * loadJson can load both raw file and file in internal storage
     */
    public ArrayList loadJson(Context context, int index) {
        switch (index) {
            case JSON_ALBUM:
                return load(Album[].class, context.getResources().openRawResource(RAW_JSON_RES_ID_s[index]));
            case JSON_PHOTO:
                return load(Photo[].class, context.getResources().openRawResource(RAW_JSON_RES_ID_s[index]));
            case JSON_ALBUM_INTERNAL:
                try {
                    return load(Album[].class, context.openFileInput(FILE_JSON_STRING_s[index]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            case JSON_PHOTO_INTERNAL:
                try {
                    return load(Photo[].class, context.openFileInput(FILE_JSON_STRING_s[index]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            default:
                return null;
        }
    }

    /**
     * writeJson can only write to internal storage
     */
    public void writeJson(Context context, int index, ArrayList arrayList) {
        FileOutputStream fos;
        switch (index) {
            case JSON_ALBUM_INTERNAL:
            case JSON_PHOTO_INTERNAL:
                write(context, FILE_JSON_STRING_s[index], arrayList.toArray());
                return;
            case JSON_ALBUM:
            case JSON_PHOTO:
            default:
                return;
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
    
    private <T> void write(Context context, String fileName, T[] array) {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(new Gson().toJson(array).getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}