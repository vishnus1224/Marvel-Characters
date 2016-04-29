package com.vishnus1224.marvelcharacters.api;

import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;

import rx.Observable;

/**
 * REST API abstraction.
 * Created by Vishnu on 4/29/2016.
 */
public interface RESTAPI {

    /**
     * Fetch marvel characters using the REST API.
     * @return Observable that emits CharacterDataWrapper items.
     */
    Observable<CharacterDataWrapper> fetchMarvelCharacters();
}
