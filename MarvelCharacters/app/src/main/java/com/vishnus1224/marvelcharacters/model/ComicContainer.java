package com.vishnus1224.marvelcharacters.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Container holding a list of comics.
 * Created by Vishnu on 4/29/2016.
 */
public class ComicContainer {

    /**
     * Total number of available comics.
     */
    private int available;

    /**
     * List of comics.
     */
    @SerializedName("items")
    private List<Comic> comicList;

    public int getAvailable() {
        return available;
    }

    public List<Comic> getComicList() {
        return comicList;
    }
}
