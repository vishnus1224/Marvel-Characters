package com.vishnus1224.marvelcharacters.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class MarvelCharacter {

    /**
     * Character id.
     */
    private int id;

    /**
     * Character name.
     */
    private String name;

    /**
     * Character description.
     */
    private String description;

    /**
     * Character thumbnail image.
     */
    private MarvelCharacterThumbnail thumbnail;

    @SerializedName("comics")
    private ComicContainer comicContainer;

    @SerializedName("stories")
    private StoryContainer storyContainer;

    @SerializedName("events")
    private EventContainer eventContainer;

    @SerializedName("series")
    private SeriesContainer seriesContainer;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public MarvelCharacterThumbnail getThumbnail() {
        return thumbnail;
    }

    public ComicContainer getComicContainer() {
        return comicContainer;
    }

    public StoryContainer getStoryContainer() {
        return storyContainer;
    }

    public EventContainer getEventContainer() {
        return eventContainer;
    }

    public SeriesContainer getSeriesContainer() {
        return seriesContainer;
    }
}
