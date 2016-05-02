package com.vishnus1224.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class MarvelCharacter implements Parcelable{

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

    public MarvelCharacter(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    protected MarvelCharacter(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        thumbnail = in.readParcelable(MarvelCharacterThumbnail.class.getClassLoader());
        comicContainer = in.readParcelable(ComicContainer.class.getClassLoader());
        storyContainer = in.readParcelable(StoryContainer.class.getClassLoader());
        eventContainer = in.readParcelable(EventContainer.class.getClassLoader());
        seriesContainer = in.readParcelable(SeriesContainer.class.getClassLoader());
    }

    public static final Creator<MarvelCharacter> CREATOR = new Creator<MarvelCharacter>() {
        @Override
        public MarvelCharacter createFromParcel(Parcel in) {
            return new MarvelCharacter(in);
        }

        @Override
        public MarvelCharacter[] newArray(int size) {
            return new MarvelCharacter[size];
        }
    };

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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(MarvelCharacterThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setComicContainer(ComicContainer comicContainer) {
        this.comicContainer = comicContainer;
    }

    public void setStoryContainer(StoryContainer storyContainer) {
        this.storyContainer = storyContainer;
    }

    public void setEventContainer(EventContainer eventContainer) {
        this.eventContainer = eventContainer;
    }

    public void setSeriesContainer(SeriesContainer seriesContainer) {
        this.seriesContainer = seriesContainer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeParcelable(thumbnail, i);
        parcel.writeParcelable(comicContainer, i);
        parcel.writeParcelable(storyContainer, i);
        parcel.writeParcelable(eventContainer, i);
        parcel.writeParcelable(seriesContainer, i);
    }
}
