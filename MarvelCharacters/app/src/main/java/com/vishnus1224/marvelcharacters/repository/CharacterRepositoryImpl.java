package com.vishnus1224.marvelcharacters.repository;

import com.vishnus1224.marvelcharacters.datastore.CharacterDataStore;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;

/**
 * Implementation of the CharacterRepository for getting a list of MarvelCharacters.
 * Created by Vishnu on 4/29/2016.
 */
public class CharacterRepositoryImpl implements CharacterRepository {

    /**
     * Reference to the data store.
     */
    private CharacterDataStore characterDataStore;

    @Inject
    public CharacterRepositoryImpl(@Named("Cloud") CharacterDataStore characterDataStore){

        this.characterDataStore = characterDataStore;

    }

    @Override
    public Observable<List<MarvelCharacter>> fetchMarvelCharacters() {
        return characterDataStore.fetchMarvelCharacters();
    }
}
