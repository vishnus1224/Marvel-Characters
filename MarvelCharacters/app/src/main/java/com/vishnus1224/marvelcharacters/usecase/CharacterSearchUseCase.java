package com.vishnus1224.marvelcharacters.usecase;

import com.vishnus1224.marvelcharacters.repository.CharacterRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Use case for searching for given character name.
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterSearchUseCase extends UseCase {

    private String characterName;

    private CharacterRepository characterRepository;

    public void setCharacterName(String characterName){

        this.characterName = characterName;

    }

    @Inject
    public CharacterSearchUseCase(CharacterRepository characterRepository){

        this.characterRepository = characterRepository;

    }

    @Override
    protected Observable buildUseCase() {
        return characterRepository.searchMarvelCharacters(characterName);
    }

}
