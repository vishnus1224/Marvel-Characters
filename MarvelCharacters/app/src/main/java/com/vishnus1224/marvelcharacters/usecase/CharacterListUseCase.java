package com.vishnus1224.marvelcharacters.usecase;

import com.vishnus1224.marvelcharacters.repository.CharacterRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class CharacterListUseCase extends UseCase {

    /**
     * Reference to the character repository.
     */
    private CharacterRepository characterRepository;

    @Inject
    public CharacterListUseCase(CharacterRepository characterRepository){

        this.characterRepository = characterRepository;

    }

    /**
     * Fetch a list of characters from the repository.
     * @return Observable emitting a list of characters.
     */
    @Override
    protected Observable buildUseCase() {
        return characterRepository.fetchMarvelCharacters(offset);
    }
}
