package com.vishnus1224.marvelcharacters.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class CharacterImageResource {

    @SerializedName("thumbnail")
    private CharacterResourceThumbnail characterResourceThumbnail;

    public CharacterResourceThumbnail getCharacterResourceThumbnail() {
        return characterResourceThumbnail;
    }
}
