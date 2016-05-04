package com.vishnus1224.marvelcharacters.cache;

import android.util.LruCache;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import javax.inject.Inject;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class LruMemoryCharacterCache implements CharacterCache {

    private LruCache<Integer, MarvelCharacter> lruCache;

    /**
     * Size of the cache. i.e. the number of entries in the cache.
     */
    private static final int CACHE_SIZE = 100;

    @Inject
    public LruMemoryCharacterCache(){

        lruCache = new LruCache<>(CACHE_SIZE);

    }

    @Override
    public void addEntry(int id, MarvelCharacter marvelCharacter) {

        lruCache.put(id, marvelCharacter);
    }

    @Override
    public MarvelCharacter get(int id) {
        return lruCache.get(id);
    }

    @Override
    public boolean containsCharacter(int id) {
        return lruCache.get(id) != null;
    }
}
