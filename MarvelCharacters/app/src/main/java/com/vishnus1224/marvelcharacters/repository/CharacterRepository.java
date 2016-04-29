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
     * @param offset Number of results that the result set should be offset.
     * @return Observable that emits CharacterDataWrapper items.
     */
    Observable<List<MarvelCharacter>> fetchMarvelCharacters(int offset);
}
