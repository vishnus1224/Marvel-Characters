package com.vishnus1224.marvelcharacters.cache;

import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

/**
 * Cache abstraction for storing a marvel character.
 * Created by Vishnu on 5/1/2016.
 */
public interface CharacterCache {

    /**
     * Add a character instance to the cache.
     * @param id character id.
     * @param marvelCharacter Marvel character instance.
     */
    void addEntry(int id, MarvelCharacter marvelCharacter);

    /**
     * Get a character from the cache.
     * @param id character id.
     * @return Marvel character with the given id if it exists, null otherwise.
     */
    MarvelCharacter get(int id);

    /**
     * Check if the character with the id exists in the cache.
     * @param id character id.
     * @return true if character exists, else false.
     */
    boolean containsCharacter(int id);
}
