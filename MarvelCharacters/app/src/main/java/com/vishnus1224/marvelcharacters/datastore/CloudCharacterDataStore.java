package com.vishnus1224.marvelcharacters.datastore;

import com.vishnus1224.marvelcharacters.api.RESTAPI;
import com.vishnus1224.marvelcharacters.cache.CharacterCache;
import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Implementation of character data store that fetches data from the cloud.
 * Created by Vishnu on 4/29/2016.
 */
public class CloudCharacterDataStore implements CharacterDataStore {

    /**
     * Reference to the rest api for fetching data.
     */
    private RESTAPI restApi;

    /**
     * Cache for storing characters.
     */
    private CharacterCache characterCache;

    @Inject
    public CloudCharacterDataStore(RESTAPI restApi, CharacterCache characterCache){

        this.restApi = restApi;

        this.characterCache = characterCache;
    }


    @Override
    public Observable<CharacterDataWrapper> fetchMarvelCharacters(int offset) {
        return restApi.fetchMarvelCharacters(offset);
    }

    @Override
    public Observable<CharacterDataWrapper> searchMarvelCharacters(String characterName) {
        return restApi.searchMarvelCharacters(characterName)
                .doOnNext(new Action1<CharacterDataWrapper>() {
                    @Override
                    public void call(CharacterDataWrapper characterDataWrapper) {

                        //save characters to cache.
                        saveCharactersToCache(characterDataWrapper);

                    }
                });
    }

    private void saveCharactersToCache(CharacterDataWrapper characterDataWrapper) {

        List<MarvelCharacter> marvelCharacters = characterDataWrapper.getCharacterDataHolder().getMarvelCharacters();

        for(MarvelCharacter marvelCharacter : marvelCharacters){

            characterCache.addEntry(marvelCharacter.getId(), marvelCharacter);
        }

    }

    @Override
    public Observable<MarvelCharacter> fetchCharacterByID(int characterID) {

        if(characterCache.containsCharacter(characterID)){

            return Observable.just(characterCache.get(characterID));

        }

        return null;
    }

}
