package com.vishnus1224.marvelcharacters.mapper;

import android.database.Cursor;
import android.database.MatrixCursor;

import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.util.Constants;

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

    /**
     * Transforms data to a cursor.
     * @param characterDataWrapper Data to be transformed.
     * @return Cursor with the mapped data.
     */
    public Cursor transformToCursor(CharacterDataWrapper characterDataWrapper){

        List<MarvelCharacter> characterList = unwrapMarvelCharacters(characterDataWrapper);

        int characterListSize = characterList.size();

        MatrixCursor matrixCursor = new MatrixCursor(Constants.SUGGESTIONS_ADAPTER_COLUMNS, characterListSize);

        for(int i = 0; i < characterListSize; i++){

            MarvelCharacter marvelCharacter = characterList.get(i);

            matrixCursor.addRow(makeDatabaseRow(marvelCharacter));

        }

        return matrixCursor;
    }

    /**
     * Create a row to be added to the cursor.
     * @param marvelCharacter Character holding the data.
     * @return Cursor row with String data.
     */
    private String[] makeDatabaseRow(MarvelCharacter marvelCharacter) {

        String[] row = new String[3];

        row[0] = String.valueOf(marvelCharacter.getId());

        row[1] = marvelCharacter.getName();

        row[2] = marvelCharacter.getThumbnail().getFinalPath();

        return row;
    }
}
