package com.vishnus1224.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class StorySummary extends Summary implements Parcelable {

    protected StorySummary(Parcel in) {
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

    public static final Creator<StorySummary> CREATOR = new Creator<StorySummary>() {
        @Override
        public StorySummary createFromParcel(Parcel in) {
            return new StorySummary(in);
        }

        @Override
        public StorySummary[] newArray(int size) {
            return new StorySummary[size];
        }
    };
}
