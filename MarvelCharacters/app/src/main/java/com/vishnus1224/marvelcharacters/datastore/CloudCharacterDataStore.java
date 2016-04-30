package com.vishnus1224.marvelcharacters.datastore;

import com.vishnus1224.marvelcharacters.api.RESTAPI;
import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;

import javax.inject.Inject;

import rx.Observable;

/**
 * Implementation of character data store that fetches data from the cloud.
 * Created by Vishnu on 4/29/2016.
 */
public class CloudCharacterDataStore implements CharacterDataStore {

    /**
     * Reference to the rest api for fetching data.
     */
    private RESTAPI restApi;

    @Inject
    public CloudCharacterDataStore(RESTAPI restApi){

        this.restApi = restApi;

    }


    @Override
    public Observable<CharacterDataWrapper> fetchMarvelCharacters(int offset) {
        return restApi.fetchMarvelCharacters(offset);
    }

    @Override
    public Observable<CharacterDataWrapper> searchMarvelCharacters(String characterName) {
        return restApi.searchMarvelCharacters(characterName);
    }

}
