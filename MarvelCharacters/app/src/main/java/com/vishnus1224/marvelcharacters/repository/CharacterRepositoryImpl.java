package com.vishnus1224.marvelcharacters.repository;

import android.database.Cursor;

import com.vishnus1224.marvelcharacters.datastore.CharacterDataStore;
import com.vishnus1224.marvelcharacters.mapper.CharacterDataMapper;
import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.functions.Func1;

/**
 * Implementation of the CharacterRepository for getting a list of MarvelCharacters.
 * Created by Vishnu on 4/29/2016.
 */
public class CharacterRepositoryImpl implements CharacterRepository {

    /**
     * Reference to the data store.
     */
    private CharacterDataStore characterDataStore;

    /**
     * Mapper for transforming data.
     */
    private CharacterDataMapper characterDataMapper;

    @Inject
    public CharacterRepositoryImpl(@Named("Cloud") CharacterDataStore characterDataStore, CharacterDataMapper characterDataMapper){

        this.characterDataStore = characterDataStore;

        this.characterDataMapper = characterDataMapper;

    }

    /**
     * Obtain a list of marvel characters by transforming the data using data mapper.
     * @return List of marvel characters.
     */
    @Override
    public Observable<List<MarvelCharacter>> fetchMarvelCharacters(int offset) {
        return characterDataStore.fetchMarvelCharacters(offset)
                .map(new Func1<CharacterDataWrapper, List<MarvelCharacter>>() {
                    @Override
                    public List<MarvelCharacter> call(CharacterDataWrapper characterDataWrapper) {

                        return characterDataMapper.unwrapMarvelCharacters(characterDataWrapper);

                    }
                });
    }

    @Override
    public Observable<Cursor> searchMarvelCharacters(String characterName) {
        return characterDataStore.searchMarvelCharacters(characterName)
                .map(new Func1<CharacterDataWrapper, Cursor>() {
                    @Override
                    public Cursor call(CharacterDataWrapper characterDataWrapper) {

                        return characterDataMapper.transformToCursor(characterDataWrapper);

                    }
                });
    }

    @Override
    public Observable<MarvelCharacter> fetchCharacterByID(int characterID) {
        return characterDataStore.fetchCharacterByID(characterID);
    }

}
