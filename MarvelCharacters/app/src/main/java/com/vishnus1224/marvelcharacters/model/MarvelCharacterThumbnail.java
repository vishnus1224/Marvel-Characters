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

    /**
     * String builder instance for generating final image path.
     */
    private static StringBuilder stringBuilder = new StringBuilder();

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

    /**
     * Combines the path and extension of the thumbnail.
     * @return Final path of the image.
     */
    public String getFinalPath() {

        //reset the contents of the string builder.
        stringBuilder.setLength(0);

        return stringBuilder.append(path)
                .append(".")
                .append(extension)
                .toString();
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
