package com.vishnus1224.marvelcharacters.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class CharacterDataWrapper {

    private int code;

    private String status;

    @SerializedName("data")
    private CharacterDataHolder characterDataHolder;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public CharacterDataHolder getCharacterDataHolder() {
        return characterDataHolder;
    }
}
