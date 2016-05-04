package com.vishnus1224.marvelcharacters.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class ImageResourceDataHolder {

    private int count;

    private int total;

    @SerializedName("results")
    private List<CharacterImageResource> characterImageResource;

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }

    public List<CharacterImageResource> getCharacterImageResource() {
        return characterImageResource;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setCharacterImageResource(List<CharacterImageResource> characterImageResource) {
        this.characterImageResource = characterImageResource;
    }
}
