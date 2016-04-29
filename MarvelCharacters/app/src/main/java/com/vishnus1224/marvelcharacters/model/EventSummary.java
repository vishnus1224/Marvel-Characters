package com.vishnus1224.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class EventSummary extends Summary implements Parcelable{

    protected EventSummary(Parcel in) {
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

    public static final Creator<EventSummary> CREATOR = new Creator<EventSummary>() {
        @Override
        public EventSummary createFromParcel(Parcel in) {
            return new EventSummary(in);
        }

        @Override
        public EventSummary[] newArray(int size) {
            return new EventSummary[size];
        }
    };
}
