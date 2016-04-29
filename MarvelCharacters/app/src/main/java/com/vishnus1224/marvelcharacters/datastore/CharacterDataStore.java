package com.vishnus1224.marvelcharacters.datastore;

import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import java.util.List;

import rx.Observable;

/**
 * Abstraction for the character data store.
 * Created by Vishnu on 4/29/2016.
 */
public interface CharacterDataStore {

    /**
     * Fetches a list of marvel characters from the data store.
     * @return Observable that emits a list of marvel characters.
     */
    Observable<List<MarvelCharacter>> fetchMarvelCharacters();
}
