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
    private String[] albumImgResName;

    public Album(String albumResName, String title, String description, String[] albumImgResName) {
        this.albumResName = albumResName;
        this.title = title;
        this.description = description;
        this.albumImgResName = albumImgResName;
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

    public void setAlbumImgResName(String[] albumImgResName) {
        this.albumImgResName = albumImgResName;
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

    public String[] getAlbumImgResName() {
        return albumImgResName;
    }
}
