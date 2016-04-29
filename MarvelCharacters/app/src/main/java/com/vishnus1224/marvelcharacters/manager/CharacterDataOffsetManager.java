package com.vishnus1224.marvelcharacters.manager;

import javax.inject.Inject;

/**
 * Manages the offset into the data set.
 * Created by Vishnu on 4/29/2016.
 */
public class CharacterDataOffsetManager {

    @Inject
    public CharacterDataOffsetManager(){

    }

    /**
     * Value of the current offset.
     */
    private int resultOffset;

    /**
     * Count of the total results that have been fetched.
     */
    private int totalResultCount;

    public int getResultOffset() {
        return resultOffset;
    }

    public int getTotalResultCount() {
        return totalResultCount;
    }


    /**
     * Updates the total result count and the offset value.
     * @param totalResultCount Total number of results returned.
     */
    public void updateTotalAndOffset(int totalResultCount){

        this.totalResultCount += totalResultCount;

        this.resultOffset = this.totalResultCount;

    }
}
