package com.vishnus1224.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class ComicSummary extends Summary implements Parcelable {

    protected ComicSummary(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ComicSummary> CREATOR = new Creator<ComicSummary>() {
        @Override
        public ComicSummary createFromParcel(Parcel in) {
            return new ComicSummary(in);
        }

        @Override
        public ComicSummary[] newArray(int size) {
            return new ComicSummary[size];
        }
    };
}
