package com.vishnus1224.marvelcharacters.datastore;

import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import java.util.List;

import rx.Observable;

/**
 * Abstraction for the character data store.
 * Created by Vishnu on 4/29/2016.
 */
public interface CharacterDataStore {

    /**
     * Fetches a list of marvel characters wrapped into a data wrapper from the data store.
     * @param offset Number of results that the result set should be offset.
     * @return Observable that emits CharacterDataWrapper items.
     */
    Observable<CharacterDataWrapper> fetchMarvelCharacters(int offset);

    /**
     * Searches for characters and wraps them in a data wrapper.
     * @param characterName Name of the character to search.
     * @return Observable data wrapper containing list of characters.
     */
    Observable<CharacterDataWrapper> searchMarvelCharacters(String characterName);
}
