package com.studio.swallowcharchar.happybirthday2016.database;

/**
 * Created by Swallow on 7/8/16.
 */
public class Album {

    /**
     * The names are used for auto data filled in by GSON
     * */
    private String albumResName;
    private String title;
    private String description;
    private int[] photoResIds;
    private String place;
    private int[] time;
    private String[] tags;

    public Album(String albumResName, String title, String description, int[] photoResIds, String place, int[] time, String[] tags) {
        this.albumResName = albumResName;
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
