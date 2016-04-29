package com.vishnus1224.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Thumbnail for each character.
 * Created by Vishnu on 4/29/2016.
 */
public class MarvelCharacterThumbnail implements Parcelable{

    /**
     * Thumbnail path.
     */
    private String path;

    /**
     * Thumbnail extension.
     */
    private String extension;

    protected MarvelCharacterThumbnail(Parcel in) {
        path = in.readString();
        extension = in.readString();
    }

    public static final Creator<MarvelCharacterThumbnail> CREATOR = new Creator<MarvelCharacterThumbnail>() {
        @Override
        public MarvelCharacterThumbnail createFromParcel(Parcel in) {
            return new MarvelCharacterThumbnail(in);
        }

        @Override
        public MarvelCharacterThumbnail[] newArray(int size) {
            return new MarvelCharacterThumbnail[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(path);
        parcel.writeString(extension);
    }
}
