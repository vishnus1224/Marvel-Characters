package com.vishnus1224.marvelcharacters.api;

import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.webservice.MarvelWebService;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class MarvelRESTAPI implements RESTAPI {

    private MarvelWebService marvelWebService;

    @Inject
    public MarvelRESTAPI(MarvelWebService marvelWebService){
        this.marvelWebService = marvelWebService;
    }

    @Override
    public Observable<CharacterDataWrapper> fetchMarvelCharacters(int offset) {
        return marvelWebService.fetchMarvelCharacters(offset);
    }
}
