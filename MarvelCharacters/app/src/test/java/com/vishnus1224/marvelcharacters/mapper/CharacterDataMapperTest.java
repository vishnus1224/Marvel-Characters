package com.vishnus1224.marvelcharacters.mapper;

import android.database.Cursor;
import android.database.MatrixCursor;

import com.vishnus1224.marvelcharacters.model.CharacterDataHolder;
import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.model.MarvelCharacterThumbnail;
import com.vishnus1224.marvelcharacters.util.Constants;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by Vishnu on 5/3/2016.
 */
public class CharacterDataMapperTest {

    private static final int FAKE_CHARACTER_ID = 1002;
    private static final String FAKE_CHARACTER_NAME = "Hulk";
    private static final String FAKE_CHARACTER_DESCRIPTION = "Strongest of them all";

    private CharacterDataMapper characterDataMapper;

    private List<MarvelCharacter> marvelCharacterList;

    private CharacterDataHolder characterDataHolder;

    private CharacterDataWrapper characterDataWrapper;

    @Before
    public void setUp() throws Exception {

        characterDataMapper = new CharacterDataMapper();


        marvelCharacterList = createCharacterList();

        characterDataHolder = createCharacterDataHolder(marvelCharacterList);

        characterDataWrapper = createCharacterDataWrapper(characterDataHolder);
    }

    @Test
    public void testUnwrapMarvelCharacters() throws Exception {

        List<MarvelCharacter> unwrappedCharacters = characterDataMapper.unwrapMarvelCharacters(characterDataWrapper);

        MarvelCharacter marvelCharacter = unwrappedCharacters.get(0);

        assertNotNull(unwrappedCharacters);
        assertEquals(unwrappedCharacters.size(), 2);


        assertEquals(marvelCharacter.getId(), FAKE_CHARACTER_ID);
        assertEquals(marvelCharacter.getName(), FAKE_CHARACTER_NAME);
        assertEquals(marvelCharacter.getDescription(), FAKE_CHARACTER_DESCRIPTION);

    }

    @Test
    public void testTransformToCursor() throws Exception {

        Cursor cursor = characterDataMapper.transformToCursor(characterDataWrapper);

        assertNotNull(cursor);
        assertNotEquals(cursor.getCount(), 2);
        assertNotEquals(cursor.getColumnCount(), 3);
    }

    private CharacterDataWrapper createCharacterDataWrapper(CharacterDataHolder characterDataHolder) {

        CharacterDataWrapper characterDataWrapper = new CharacterDataWrapper();
        characterDataWrapper.setCharacterDataHolder(characterDataHolder);

        return characterDataWrapper;
    }

    private CharacterDataHolder createCharacterDataHolder(List<MarvelCharacter> marvelCharacterList) {

        CharacterDataHolder characterDataHolder = new CharacterDataHolder();
        characterDataHolder.setMarvelCharacters(marvelCharacterList);

        return characterDataHolder;
    }

    private List<MarvelCharacter> createCharacterList() {

        List<MarvelCharacter> marvelCharacters = new ArrayList<>();

        MarvelCharacter hulk = new MarvelCharacter(FAKE_CHARACTER_ID, FAKE_CHARACTER_NAME, FAKE_CHARACTER_DESCRIPTION);
        hulk.setThumbnail(new MarvelCharacterThumbnail());
        marvelCharacters.add(hulk);

        MarvelCharacter cyclops = new MarvelCharacter(3904, "Cyclops", "Can kill instantly");
        cyclops.setThumbnail(new MarvelCharacterThumbnail());
        marvelCharacters.add(cyclops);

        return marvelCharacters;
    }
}