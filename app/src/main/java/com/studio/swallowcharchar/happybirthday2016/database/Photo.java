package com.studio.swallowcharchar.happybirthday2016.database;

/**
 * Created by Swallow on 7/8/16.
 */
public class Photo {
    private int photoId;
    private String photoResName;
    private String tilte;
    private String description;

    public Photo(int photoId, String photoResName, String tilte, String description) {
        this.photoId = photoId;
        this.photoResName = photoResName;
        this.tilte = tilte;
        this.description = description;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setPhotoResName(String photoResName) {
        this.photoResName = photoResName;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getPhotoResName() {
        return photoResName;
    }

    public String getTilte() {
        return tilte;
    }

    public String getDescription() {
        return description;
    }
}
