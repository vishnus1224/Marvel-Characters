package com.vishnus1224.marvelcharacters.model;

/**
 * Thumbnail for each character.
 * Created by Vishnu on 4/29/2016.
 */
public class MarvelCharacterThumbnail {

    /**
     * Thumbnail path.
     */
    private String path;

    /**
     * Thumbnail extension.
     */
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
