package com.vishnus1224.marvelcharacters.usecase;

import com.vishnus1224.marvelcharacters.repository.CharacterRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Use case for getting a single character details.
 * Created by Vishnu on 5/1/2016.
 */
public class CharacterSearchByIDUseCase extends UseCase {

    private CharacterRepository characterRepository;

    private int characterID;

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    @Inject
    public CharacterSearchByIDUseCase(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    protected Observable buildUseCase() {
        return characterRepository.fetchCharacterByID(characterID);
    }
}
