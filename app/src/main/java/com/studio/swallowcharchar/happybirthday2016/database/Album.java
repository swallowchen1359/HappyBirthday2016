package com.studio.swallowcharchar.happybirthday2016.database;

/**
 * Created by Swallow on 7/8/16.
 */
public class Album {
    /**
     * database_album.json :
     * -----------------------------------------------------------
     * * albumResName : Img file name, excluding path.           *
     * * source       : 0: from mipmap, 1: internal 2: external. *
     * * title        : Title for album.                         *
     * * description  : Description for album.                   *
     * * photoResIds  : Index of photos contained in the ablum.  *
     * * place        : Where album is created.                  *
     * * time         : When album is create.                    *
     * * tags         : Tags of album (union of tags of photos). *
     * -----------------------------------------------------------
     */

    public static final int SOURCE_DRAWABLE = 0x0;
    public static final int SOURCE_INTERNAL = 0x1;
    public static final int SOURCE_EXTERNAL = 0x2;

    /**
     * The names are used for auto data filled in by GSON
     * */
    private String albumResName;
    private int source;
    private String title;
    private String description;
    private int[] photoResIds;
    private String place;
    private int[] time;
    private String[] tags;

    public Album(String albumResName, int source, String title, String description, int[] photoResIds, String place, int[] time, String[] tags) {
        this.albumResName = albumResName;
        this.source = source;
        this.title = title;
        this.description = description;
        this.photoResIds = photoResIds;
        this.place = place;
        this.time = time;
        this.tags = tags;
    }

    public void setAlbumResName(String albumResName) {
        this.albumResName = albumResName;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoResIds(int[] albumImgResIds) {
        this.photoResIds = albumImgResIds;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(int[] time) {
        this.time = time;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getAlbumResName() {
        return albumResName;
    }

    public int getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int[] getPhotoResIds() {
        return photoResIds;
    }

    public String getPlace() {
        return place;
    }

    public int[] getTime() {
        return time;
    }

    public String[] getTags() {
        return tags;
    }
}
