package com.vishnus1224.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Base class for summary objects.
 * Created by Vishnu on 4/29/2016.
 */
public class Summary implements Parcelable{

    /**
     * ResourceURI of the summary.
     */
    protected String resourceURI;

    /**
     * Summary name.
     */
    protected String name;

    protected Summary(Parcel in) {
        resourceURI = in.readString();
        name = in.readString();
    }

    public static final Creator<Summary> CREATOR = new Creator<Summary>() {
        @Override
        public Summary createFromParcel(Parcel in) {
            return new Summary(in);
        }

        @Override
        public Summary[] newArray(int size) {
            return new Summary[size];
        }
    };

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(resourceURI);
        parcel.writeString(name);
    }
}
