package com.vishnus1224.marvelcharacters.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Holds a list of stories.
 * Created by Vishnu on 4/29/2016.
 */
public class StoryContainer {

    private int available;

    @SerializedName("items")
    private List<StorySummary> stories;

    public int getAvailable() {
        return available;
    }

    public List<StorySummary> getStories() {
        return stories;
    }
}
