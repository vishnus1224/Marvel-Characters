package com.vishnus1224.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class Comic implements Parcelable {

    private String resourceURI;

    private String name;

    protected Comic(Parcel in) {
        resourceURI = in.readString();
        name = in.readString();
    }

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel in) {
            return new Comic(in);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
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
