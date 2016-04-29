package com.vishnus1224.marvelcharacters.mapper;

import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class for transforming data obtained from the rest api
 * to a format that is required by the application.
 * Created by Vishnu on 4/29/2016.
 */
public class CharacterDataMapper {

    @Inject
    public CharacterDataMapper(){

    }

    /**
     * Unwraps the container to find the required data.
     * @param characterDataWrapper Wrapper holding data results.
     * @return List of marvel characters.
     */
    public List<MarvelCharacter> unwrapMarvelCharacters(CharacterDataWrapper characterDataWrapper){

        return characterDataWrapper.getCharacterDataHolder().getMarvelCharacters();

    }
}
