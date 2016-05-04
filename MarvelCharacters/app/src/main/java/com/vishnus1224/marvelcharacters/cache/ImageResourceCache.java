package com.vishnus1224.marvelcharacters.cache;

import com.vishnus1224.marvelcharacters.model.ImageResourceDataWrapper;

/**
 * Abstraction for caching image resource data.
 * Created by Vishnu on 5/1/2016.
 */
public interface ImageResourceCache {

    /**
     * Add an entry to the cache.
     * @param key key to identify the entry.
     * @param imageResourceDataWrapper imageResourceDataWrapper instance.
     */
    void addEntry(String key, ImageResourceDataWrapper imageResourceDataWrapper);

    /**
     * Get the value associated with the given key if it is exists in the cache.
     * If key does not exists, then value will be null.
     * @param key key stored in the cache.
     */
    ImageResourceDataWrapper getEntry(String key);

    /**
     * Check if the given key is present in the cache.
     * @param key key to check for.
     * @return true if key present, false otherwise.
     */
    boolean containsKey(String key);

    /**
     *
     * Removes a single entry from the cache if it exists.
     * @param key key to remove.
     * @return The removed entry.
     */
    ImageResourceDataWrapper removeEntry(String key);

    /**
     * Clear the contents of the cache.
     */
    void clearCache();
}
