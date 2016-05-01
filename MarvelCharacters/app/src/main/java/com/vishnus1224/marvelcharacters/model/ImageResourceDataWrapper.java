package com.vishnus1224.marvelcharacters.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class ImageResourceDataWrapper {

    private int code;

    private String status;

    @SerializedName("data")
    private ImageResourceDataHolder imageResourceDataHolder;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public ImageResourceDataHolder getImageResourceDataHolder() {
        return imageResourceDataHolder;
    }
}
