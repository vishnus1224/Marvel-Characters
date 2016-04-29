package com.vishnus1224.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class SeriesSummary extends Summary implements Parcelable {

    protected SeriesSummary(Parcel in) {
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

    public static final Creator<SeriesSummary> CREATOR = new Creator<SeriesSummary>() {
        @Override
        public SeriesSummary createFromParcel(Parcel in) {
            return new SeriesSummary(in);
        }

        @Override
        public SeriesSummary[] newArray(int size) {
            return new SeriesSummary[size];
        }
    };
}
