package com.studio.swallowcharchar.happybirthday2016.albumpage.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.studio.swallowcharchar.happybirthday2016.database.Album;
import com.studio.swallowcharchar.happybirthday2016.database.Database;
import com.studio.swallowcharchar.happybirthday2016.database.Photo;
import com.studio.swallowcharchar.happybirthday2016.widget.ImageUtility;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Swallow on 6/22/16.
 */
public class AlbumModel {

    public interface TaskCallbacks {
        void onInitModelDone();
        void onLoadDone();
        void onBitmapCreateDone(BitmapWithIndex bitmap);
    }

    public class BitmapWithIndex {
        private Bitmap bitmap;
        private int index;

        BitmapWithIndex(Bitmap bitmap, int index) {
            this.bitmap = bitmap;
            this.index = index;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public int getIndex() {
            return index;
        }
    }

    private Context mContext;

    /**
     * mAlbumArrayList is the array of Album, which is object related to Database
     * mAlbumArrayList will be initialized by private function initModel().
     * All Album related information is from mAlbumArrayList(index)
     * */
    private ArrayList<Album> mAlbumArrayList;

    /**
     *
     * */
    private ArrayList<Photo> mPhotoArrayList;

    /**
     * mAlbumPictureList is the list contains AlbumCard picture to show in AlbumCardView
     * mAlbumPictureList will be returned by calling getAlbumPictureList()
     * */
    private LinkedList<Integer> mAlbumPictureList;

    /**
     * mAlbumTitleList is the list contains AlbumCard title to show.
     * */
    private LinkedList<String> mAlbumTitleList;

    /**
     * mAlbumPlaceList is the list contains AlbumCard place to show
     * */
    private LinkedList<String> mAlbumPlaceList;

    /**
     * mAlbumTimeList is the list contains AlbumCard time to show
     * which contains another LinkedList of Integer, individually year, month and day
     * */
    private LinkedList<LinkedList<Integer>> mAlbumTimeList;

    /**
     * mAlbumTagsList is the list contains AlbumCard tag to show
     * which contains another LinkedList of String, all tags reserved
     * */
    private LinkedList<LinkedList<String>> mAlbumTagsList;

    /**
     * mAlbumDescription is the list contains AlbumCard description.
     * TODO: Maybe we should use InputStream instead
     * */
    private LinkedList<String> mAlbumDescriptionList;

    /**
     * TaskCallbacks
     */
    private TaskCallbacks mTaskCallbacks;

    public AlbumModel(Context context) {
        mContext = context;
        mAlbumPictureList = new LinkedList<>();
        mAlbumTitleList = new LinkedList<>();
        mAlbumDescriptionList = new LinkedList<>();
        mAlbumPlaceList = new LinkedList<>();
        mAlbumTimeList = new LinkedList<>();
        mAlbumTagsList = new LinkedList<>();
    }

    public void setTaskCallbacks(TaskCallbacks taskCallbacks) {
        mTaskCallbacks = taskCallbacks;
    }

    public LinkedList<Integer> getAlbumPictureList() {
        if (mContext == null || mAlbumArrayList == null) {
            return null;
        }
        /**
         * loadJson(Context context, int index) is used to parsing JSON file by GSON, according to
         * entered index.
         * */
        for (int i = 0; i < mAlbumArrayList.size(); i++) {
            mAlbumPictureList.add(mContext.getResources().getIdentifier(mAlbumArrayList.get(i).getAlbumResName(), "drawable", mContext.getPackageName()));
        }
        return mAlbumPictureList;
    }

    public LinkedList<String> getAlbumTitleList() {
        if (mContext == null || mAlbumArrayList == null) {
            return null;
        }
        for (int i = 0; i < mAlbumArrayList.size(); i++) {
            mAlbumTitleList.add(mAlbumArrayList.get(i).getTitle());
        }
        return mAlbumTitleList;
    }

    public LinkedList<String> getAlbumDescriptionList() {
        if (mContext == null || mAlbumArrayList == null) {
            return null;
        }
        for (int i = 0; i < mAlbumArrayList.size(); i++) {
            mAlbumDescriptionList.add(mAlbumArrayList.get(i).getDescription());
        }
        return mAlbumDescriptionList;
    }

    public LinkedList<String> getAlbumPlaceList() {
        if (mContext == null || mAlbumArrayList == null) {
            return null;
        }
        for (int i = 0; i < mAlbumArrayList.size(); i++) {
            mAlbumPlaceList.add(mAlbumArrayList.get(i).getPlace());
        }
        return mAlbumPlaceList;
    }

    public LinkedList<LinkedList<Integer>> getAlbumTimeList() {
        if (mContext == null || mAlbumArrayList == null) {
            return null;
        }
        for (int i = 0; i < mAlbumArrayList.size(); i++) {
            LinkedList<Integer> timeList = new LinkedList<>();
            int[] timeArray = mAlbumArrayList.get(i).getTime();
            for (int j = 0; j < timeArray.length; j++) {
                timeList.add(timeArray[j]);
            }
            mAlbumTimeList.add(timeList);
        }
        return mAlbumTimeList;
    }

    public LinkedList<LinkedList<String>> getAlbumTagsList() {
        if (mContext == null || mAlbumArrayList == null) {
            return null;
        }
        for (int i = 0; i < mAlbumArrayList.size(); i++) {
            LinkedList<String> tagsList = new LinkedList<>();
            String[] tagsArray = mAlbumArrayList.get(i).getTags();
            for (int j = 0; j < tagsArray.length; j++) {
                tagsList.add(tagsArray[j]);
            }
            mAlbumTagsList.add(tagsList);
        }
        return mAlbumTagsList;
    }

    public int getAlbumResId(int index) {
        return mContext.getResources().getIdentifier(mAlbumArrayList.get(index).getAlbumResName(), "drawable", mContext.getPackageName());
    }

    public String getAlbumTitle(int index) {
        return mAlbumArrayList.get(index).getTitle();
    }

    public String getAlbumDescription(int index) {
        return mAlbumArrayList.get(index).getDescription();
    }

    public String getAlbumPlace(int index) {
        return mAlbumArrayList.get(index).getPlace();
    }

    public LinkedList<Integer> getAlbumTime(int index) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        Album album = mAlbumArrayList.get(index);
        int time[] = album.getTime();
        for (int i = 0; i < time.length; i++) {
            linkedList.add(time[i]);
        }
        return linkedList;
    }

    public LinkedList<String> getAlbumTags(int index) {
        LinkedList<String> linkedList = new LinkedList<>();
        Album album = mAlbumArrayList.get(index);
        String[] tags = album.getTags();
        for (int i = 0; i < tags.length; i++) {
            linkedList.add(tags[i]);
        }
        return linkedList;
    }

    public LinkedList<Integer> getAlbumPhotos(int index) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        Album album = mAlbumArrayList.get(index);
        int[] resIds = album.getPhotoResIds();
        for (int i = 0; i < resIds.length; i++) {
            linkedList.add(resIds[i]);
        }
        return linkedList;
    }
    /**
     * The function will keep send Bitmap to callback function onBitmapCreateDone
     * TODO: Using AsyncTask to return bitmap
     * @return The last bitmap
     * */
    public Bitmap getAlbumPictureBitmaps() {
        if (mContext == null || mPhotoArrayList == null || mAlbumArrayList == null) {
            return null;
        }


        for (int i = 0; i < mAlbumArrayList.size(); i++) {
            /** 1. Get album object */
            Album albumObject = mAlbumArrayList.get(i);
            final int index = i;
            /** 2. Get album res name */
            /** 3. Create an AsyncTask to load the resource by albumResName */
            if (albumObject != null) {
                new AsyncTask<Album, Void, BitmapWithIndex>() {

                    @Override
                    protected BitmapWithIndex doInBackground(Album... params) {
                        Album album = params[0];
                        Bitmap bitmap = null;
                        /**
                         * Photo image may come from 3 places
                         * if mipmap, directly get mipmap
                         * else, get from cursor
                         */
                        if (album.getSource() == Album.SOURCE_DRAWABLE) {
                            int resId = mContext.getResources().getIdentifier(album.getAlbumResName(), "drawable", mContext.getPackageName());
                            bitmap = ImageUtility.decodeSampledBitmapFromResource(mContext.getResources(), resId, 900, 900);
                        }
                        return new BitmapWithIndex(bitmap, index);
                    }

                    @Override
                    protected void onPostExecute(BitmapWithIndex bitmapWithIndex) {
                        if (mTaskCallbacks != null) {
                            mTaskCallbacks.onBitmapCreateDone(bitmapWithIndex);
                        }
                    }
                }.execute(albumObject);
            }
        }
        return null;
    }

    public boolean initModel() {
        mAlbumArrayList = new Database().loadJson(mContext, Database.JSON_ALBUM);
        mPhotoArrayList = new Database().loadJson(mContext, Database.JSON_PHOTO);
        if (mTaskCallbacks != null) {
            mTaskCallbacks.onInitModelDone();
        }
        return mAlbumArrayList != null ;
    }
}
