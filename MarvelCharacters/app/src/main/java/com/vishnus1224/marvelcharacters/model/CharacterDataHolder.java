package com.vishnus1224.marvelcharacters.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class CharacterDataHolder {

    private int count;

    private int total;

    @SerializedName("results")
    private List<MarvelCharacter> marvelCharacters;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MarvelCharacter> getMarvelCharacters() {
        return marvelCharacters;
    }

    public void setMarvelCharacters(List<MarvelCharacter> marvelCharacters) {
        this.marvelCharacters = marvelCharacters;
    }
}
