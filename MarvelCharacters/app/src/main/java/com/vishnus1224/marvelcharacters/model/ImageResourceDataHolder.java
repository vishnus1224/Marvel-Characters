package com.vishnus1224.marvelcharacters.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class ImageResourceDataHolder {

    private int count;

    private int total;

    @SerializedName("results")
    private CharacterResourceThumbnail characterResourceThumbnail;

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }

    public CharacterResourceThumbnail getCharacterResourceThumbnail() {
        return characterResourceThumbnail;
    }
}
