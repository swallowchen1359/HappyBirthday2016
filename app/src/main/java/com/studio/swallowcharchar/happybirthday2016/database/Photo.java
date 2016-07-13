package com.studio.swallowcharchar.happybirthday2016.database;

/**
 * Created by Swallow on 7/8/16.
 */
public class Photo {
    /**
     * database_photo.json :
     * -----------------------------------------------------------
     * * photoId      : Index for Album to identified photos.    *
     * * photoResName : Img file name, excluding path.           *
     * * source       : 0: from mipmap, 1: internal 2: external. *
     * * title        : Title for photo.                         *
     * * description  : Description for photo.                   *
     * * tags         : Tags of photos (for future use).         *
     * -----------------------------------------------------------
     */

    public static final int SOURCE_MIPMAP = 0x0;
    public static final int SOURCE_INTERNAL = 0x1;
    public static final int SOURCE_EXTERNAL = 0x2;

    /**
     * The names are used for auto data filled in by GSON
     * */
    private int photoId;
    private String photoResName;
    private int source;
    private String tilte;
    private String description;

    public Photo(int photoId, String photoResName, int source, String tilte, String description) {
        this.photoId = photoId;
        this.photoResName = photoResName;
        this.source = source;
        this.tilte = tilte;
        this.description = description;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setPhotoResName(String photoResName) {
        this.photoResName = photoResName;
    }

    public void setSource(int source) {
        this.source = source;
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

    public int getSource() {
        return source;
    }

    public String getTilte() {
        return tilte;
    }

    public String getDescription() {
        return description;
    }
}
