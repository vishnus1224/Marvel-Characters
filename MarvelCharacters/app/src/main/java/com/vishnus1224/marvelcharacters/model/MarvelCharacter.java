package com.vishnus1224.marvelcharacters.model;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class MarvelCharacter extends BaseModel {

    private String name;
    private String imageURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
