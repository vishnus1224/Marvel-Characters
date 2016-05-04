package com.vishnus1224.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class EventContainer implements Parcelable{

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
    private List<EventSummary> items;

    public EventContainer(){

    }

    protected EventContainer(Parcel in) {
        available = in.readInt();
        returned = in.readInt();
        collectionURI = in.readString();
        items = in.createTypedArrayList(EventSummary.CREATOR);
    }

    public static final Creator<EventContainer> CREATOR = new Creator<EventContainer>() {
        @Override
        public EventContainer createFromParcel(Parcel in) {
            return new EventContainer(in);
        }

        @Override
        public EventContainer[] newArray(int size) {
            return new EventContainer[size];
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

    public List<EventSummary> getItems() {
        return items;
    }

    public void setItems(List<EventSummary> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(available);
        parcel.writeInt(returned);
        parcel.writeString(collectionURI);
        parcel.writeTypedList(items);
    }
}
