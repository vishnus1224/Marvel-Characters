package com.vishnus1224.marvelcharacters.repository;

import android.database.Cursor;
import android.database.MatrixCursor;

import com.vishnus1224.marvelcharacters.datastore.CharacterDataStore;
import com.vishnus1224.marvelcharacters.mapper.CharacterDataMapper;
import com.vishnus1224.marvelcharacters.model.CharacterDataHolder;
import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by Vishnu on 5/3/2016.
 */
public class CharacterRepositoryImplTest {

    private static final int FAKE_OFFSET = 0;

    private static final int FAKE_CHARACTER_ID = 1002;
    private static final String FAKE_CHARACTER_NAME = "Hulk";
    private static final String FAKE_CHARACTER_DESCRIPTION = "Strongest of them all";

    @Mock
    CharacterDataStore characterDataStore;

    @Mock
    CharacterDataMapper characterDataMapper;

    private CharacterRepositoryImpl characterRepository;

    @Mock
    CharacterDataWrapper characterDataWrapper;

    @Mock
    CharacterDataHolder characterDataHolder;

    private List<MarvelCharacter> marvelCharacters;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        characterRepository = new CharacterRepositoryImpl(characterDataStore, characterDataMapper);

        marvelCharacters = createCharacterList();

        characterDataHolder.setMarvelCharacters(marvelCharacters);

        characterDataWrapper.setCharacterDataHolder(characterDataHolder);

    }

    @Test
    public void testFetchMarvelCharacters() throws Exception {

        given(characterDataStore.fetchMarvelCharacters(anyInt())).willReturn(Observable.just(characterDataWrapper));

        Observable<List<MarvelCharacter>> characterListObservable = characterRepository.fetchMarvelCharacters(FAKE_OFFSET);

        TestSubscriber<List<MarvelCharacter>> testSubscriber = new TestSubscriber<>();

        characterListObservable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();

        verify(characterDataStore).fetchMarvelCharacters(FAKE_OFFSET);
        verify(characterDataStore, times(1)).fetchMarvelCharacters(FAKE_OFFSET);
        verify(characterDataMapper).unwrapMarvelCharacters(characterDataWrapper);
        verify(characterDataMapper, times(1)).unwrapMarvelCharacters(characterDataWrapper);
    }

    @Test
    public void testSearchMarvelCharacters() throws Exception {

        Cursor cursor = new MatrixCursor(new String[]{FAKE_CHARACTER_NAME, FAKE_CHARACTER_DESCRIPTION});

        given(characterDataStore.searchMarvelCharacters(anyString())).willReturn(Observable.just(characterDataWrapper));

        Observable<Cursor> cursorObservable = characterRepository.searchMarvelCharacters(FAKE_CHARACTER_NAME);

        TestSubscriber<Cursor> cursorTestSubscriber = new TestSubscriber<>();

        cursorObservable.subscribe(cursorTestSubscriber);

        cursorTestSubscriber.assertNoErrors();
        cursorTestSubscriber.assertCompleted();

        verify(characterDataStore).searchMarvelCharacters(FAKE_CHARACTER_NAME);
        verify(characterDataStore, times(1)).searchMarvelCharacters(FAKE_CHARACTER_NAME);
        verify(characterDataMapper).transformToCursor(characterDataWrapper);
        verify(characterDataMapper, times(1)).transformToCursor(characterDataWrapper);
    }

    @Test
    public void testFetchCharacterByID() throws Exception {

        MarvelCharacter marvelCharacter = new MarvelCharacter(FAKE_CHARACTER_ID, FAKE_CHARACTER_NAME, FAKE_CHARACTER_DESCRIPTION);

        given(characterDataStore.fetchCharacterByID(anyInt())).willReturn(Observable.just(marvelCharacter));

        Observable<MarvelCharacter> marvelCharacterObservable = characterRepository.fetchCharacterByID(FAKE_CHARACTER_ID);

        TestSubscriber<MarvelCharacter> marvelCharacterTestSubscriber = new TestSubscriber<>();

        marvelCharacterObservable.subscribe(marvelCharacterTestSubscriber);

        marvelCharacterTestSubscriber.assertNoErrors();
        marvelCharacterTestSubscriber.assertCompleted();
        marvelCharacterTestSubscriber.assertValue(marvelCharacter);

        MarvelCharacter character = marvelCharacterTestSubscriber.getOnNextEvents().get(0);

        assertNotNull(character);
        assertEquals(character.getId(), marvelCharacter.getId());
        assertEquals(character.getName(), marvelCharacter.getName());
        assertEquals(character.getDescription(), marvelCharacter.getDescription());

        verify(characterDataStore).fetchCharacterByID(FAKE_CHARACTER_ID);
        verify(characterDataStore, times(1)).fetchCharacterByID(FAKE_CHARACTER_ID);

    }


    private List<MarvelCharacter> createCharacterList() {

        List<MarvelCharacter> marvelCharacters = new ArrayList<>();

        MarvelCharacter hulk = new MarvelCharacter(FAKE_CHARACTER_ID, FAKE_CHARACTER_NAME, FAKE_CHARACTER_DESCRIPTION);
        marvelCharacters.add(hulk);

        MarvelCharacter cyclops = new MarvelCharacter(3904, "Cyclops", "Can kill instantly");
        marvelCharacters.add(cyclops);

        return marvelCharacters;
    }
}