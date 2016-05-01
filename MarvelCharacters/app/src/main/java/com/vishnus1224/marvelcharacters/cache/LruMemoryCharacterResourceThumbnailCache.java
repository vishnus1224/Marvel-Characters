package com.vishnus1224.marvelcharacters.cache;

import android.util.LruCache;

import com.vishnus1224.marvelcharacters.model.CharacterResourceThumbnail;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * In memory cache for storing character resource thumbnails.
 * Created by Vishnu on 5/1/2016.
 */
@Singleton
public class LruMemoryCharacterResourceThumbnailCache implements ImageResourceCache {

    private LruCache<String, CharacterResourceThumbnail> lruCache;

    /**
     * Size of the cache. i.e. the number of entries in the cache.
     */
    private static final int CACHE_SIZE = 100;

    @Inject
    public LruMemoryCharacterResourceThumbnailCache(){

        lruCache = new LruCache<>(CACHE_SIZE);
    }

    @Override
    public void addEntry(String key, CharacterResourceThumbnail characterResourceThumbnail) {

        lruCache.put(key, characterResourceThumbnail);

    }

    @Override
    public CharacterResourceThumbnail getEntry(String key) {

        return lruCache.get(key);

    }

    @Override
    public boolean containsKey(String key) {
        return lruCache.get(key) != null;
    }

    @Override
    public CharacterResourceThumbnail removeEntry(String key) {

        return lruCache.remove(key);

    }

    @Override
    public void clearCache() {

        lruCache.evictAll();

    }
}
