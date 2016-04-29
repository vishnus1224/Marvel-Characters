package com.vishnus1224.marvelcharacters.datastore;

import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.webservice.MarvelWebService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Implementation of character data store that fetches data from the cloud.
 * Created by Vishnu on 4/29/2016.
 */
public class CloudCharacterDataStore implements CharacterDataStore {

    /**
     * Reference to the marvel web service for fetching data from the rest API.
     */
    private MarvelWebService marvelWebService;

    @Inject
    public CloudCharacterDataStore(MarvelWebService marvelWebService){

        this.marvelWebService = marvelWebService;

    }


    @Override
    public Observable<List<MarvelCharacter>> fetchMarvelCharacters() {
        return null;
    }
}
