package com.vishnus1224.marvelcharacters.repository;

import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import java.util.List;

import rx.Observable;

/**
 * Created by Vishnu on 4/29/2016.
 */
public interface CharacterRepository {

    /**
     * Fetch a list of marvel characters from the data store.
     * @return Observable emitting a list of MarvelCharacter objects.
     */
    Observable<List<MarvelCharacter>> fetchMarvelCharacters();
}
