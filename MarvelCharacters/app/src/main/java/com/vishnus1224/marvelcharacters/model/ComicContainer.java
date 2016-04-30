package com.vishnus1224.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Container holding a list of comics.
 * Created by Vishnu on 4/29/2016.
 */
public class ComicContainer implements Parcelable{


    /**
     * Total number of results available.
     */
    private int available;

    /**
     * Total number of results returned.
     */
    private int returned;

    /**
     * The collection URI.
     */
    private String collectionURI;

    /**
     * List of items.
     */
    private List<ComicSummary> items;

    protected ComicContainer(Parcel in) {
        available = in.readInt();
        returned = in.readInt();
        collectionURI = in.readString();
        items = in.createTypedArrayList(ComicSummary.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(available);
        dest.writeInt(returned);
        dest.writeString(collectionURI);
        dest.writeTypedList(items);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ComicContainer> CREATOR = new Creator<ComicContainer>() {
        @Override
        public ComicContainer createFromParcel(Parcel in) {
            return new ComicContainer(in);
        }

        @Override
        public ComicContainer[] newArray(int size) {
            return new ComicContainer[size];
        }
    };

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<ComicSummary> getItems() {
        return items;
    }

    public void setItems(List<ComicSummary> items) {
        this.items = items;
    }
}
