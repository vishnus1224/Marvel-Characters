package com.vishnus1224.marvelcharacters.datastore;

import com.vishnus1224.marvelcharacters.api.RESTAPI;
import com.vishnus1224.marvelcharacters.cache.CharacterCache;
import com.vishnus1224.marvelcharacters.model.CharacterDataHolder;
import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;


/**
 * Created by Vishnu on 5/2/2016.
 */
public class CloudCharacterDataStoreTest {

    private static final int FAKE_OFFSET = 0;

    private static final int FAKE_CHARACTER_ID = 1002;
    private static final String FAKE_CHARACTER_NAME = "Hulk";
    private static final String FAKE_CHARACTER_DESCRIPTION = "Strongest of them all";

    @Mock
    private RESTAPI restapi;

    @Mock
    private CharacterCache characterCache;

    private CloudCharacterDataStore cloudCharacterDataStore;

    private List<MarvelCharacter> marvelCharacterList;

    private CharacterDataHolder characterDataHolder;

    private CharacterDataWrapper characterDataWrapper;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        cloudCharacterDataStore = new CloudCharacterDataStore(restapi, characterCache);

        marvelCharacterList = createCharacterList();

        characterDataHolder = createCharacterDataHolder(marvelCharacterList);

        characterDataWrapper = createCharacterDataWrapper(characterDataHolder);


    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testFetchMarvelCharacters() throws Exception {


        //return mock observable when the rest api method is called.
        when(restapi.fetchMarvelCharacters(anyInt())).thenReturn(Observable.just(characterDataWrapper));

        //get observable from the rest api.
        Observable<CharacterDataWrapper> marvelCharacterObservable = restapi.fetchMarvelCharacters(FAKE_OFFSET);

        TestSubscriber<CharacterDataWrapper> testSubscriber = new TestSubscriber<>();

        marvelCharacterObservable.subscribe(testSubscriber);

        //verify that the method is called.
        verify(restapi).fetchMarvelCharacters(FAKE_OFFSET);

        //verify that the method is called just once.
        verify(restapi, times(1)).fetchMarvelCharacters(FAKE_OFFSET);

        //assert that no errors occurred.
        testSubscriber.assertNoErrors();

        //assert the number of observables emitted.
        testSubscriber.assertValueCount(1);

        //assert that the observable completed successfully.
        testSubscriber.assertCompleted();

        testSubscriber.assertValue(characterDataWrapper);

        List<CharacterDataWrapper> characterDataWrapperList = testSubscriber.getOnNextEvents();

        assertEquals(characterDataWrapperList.get(0).getCharacterDataHolder(), characterDataHolder);

        assertEquals(characterDataWrapperList.get(0).getCharacterDataHolder().getMarvelCharacters(), marvelCharacterList);

        List<MarvelCharacter> marvelCharacters = characterDataWrapperList.get(0).getCharacterDataHolder().getMarvelCharacters();

        assertNotNull(marvelCharacters);

        MarvelCharacter hulk = marvelCharacters.get(0);

        assertEquals(hulk.getId(), FAKE_CHARACTER_ID);
        assertEquals(hulk.getName(), FAKE_CHARACTER_NAME);
        assertEquals(hulk.getDescription(), FAKE_CHARACTER_DESCRIPTION);

    }

    @Test
    public void testSearchMarvelCharacters() throws Exception {

        when(restapi.searchMarvelCharacters(anyString())).thenReturn(Observable.just(characterDataWrapper));

        Observable<CharacterDataWrapper> characterDataWrapperObservable = restapi.searchMarvelCharacters(FAKE_CHARACTER_NAME);

        TestSubscriber<CharacterDataWrapper> testSubscriber = new TestSubscriber<>();

        characterDataWrapperObservable.subscribe(testSubscriber);

        //verify that the method is called.
        verify(restapi).searchMarvelCharacters(FAKE_CHARACTER_NAME);

        //verify that the method is called just once.
        verify(restapi, times(1)).searchMarvelCharacters(FAKE_CHARACTER_NAME);

        //assert that no errors occurred.
        testSubscriber.assertNoErrors();

        //assert the number of observables emitted.
        testSubscriber.assertValueCount(1);

        //assert that the observable completed successfully.
        testSubscriber.assertCompleted();

        testSubscriber.assertValue(characterDataWrapper);

        List<CharacterDataWrapper> characterDataWrapperList = testSubscriber.getOnNextEvents();

        assertEquals(characterDataWrapperList.get(0).getCharacterDataHolder(), characterDataHolder);

        assertEquals(characterDataWrapperList.get(0).getCharacterDataHolder().getMarvelCharacters(), marvelCharacterList);

        List<MarvelCharacter> marvelCharacters = characterDataWrapperList.get(0).getCharacterDataHolder().getMarvelCharacters();

        assertNotNull(marvelCharacters);

        MarvelCharacter hulk = marvelCharacters.get(0);

        assertEquals(hulk.getId(), FAKE_CHARACTER_ID);
        assertEquals(hulk.getName(), FAKE_CHARACTER_NAME);
        assertEquals(hulk.getDescription(), FAKE_CHARACTER_DESCRIPTION);
    }

    @Test
    public void testFetchCharacterByID() throws Exception {

        //given that the character id exists in the cache.
        given(characterCache.containsCharacter(anyInt())).willReturn(true);

        //when the get method on the cache is called, return the first element in the list.
        when(characterCache.get(FAKE_CHARACTER_ID)).thenReturn(marvelCharacterList.get(0));

        Observable<MarvelCharacter> marvelCharacterObservable = cloudCharacterDataStore.fetchCharacterByID(FAKE_CHARACTER_ID);

        TestSubscriber<MarvelCharacter> marvelCharacterTestSubscriber = new TestSubscriber<>();

        marvelCharacterObservable.subscribe(marvelCharacterTestSubscriber);

        verify(characterCache).containsCharacter(FAKE_CHARACTER_ID);
        verify(characterCache, times(1)).containsCharacter(FAKE_CHARACTER_ID);
        verify(characterCache).get(FAKE_CHARACTER_ID);
        verify(characterCache, times(1)).get(FAKE_CHARACTER_ID);

        marvelCharacterTestSubscriber.assertNoErrors();
        marvelCharacterTestSubscriber.assertCompleted();
        marvelCharacterTestSubscriber.assertValueCount(1);

        MarvelCharacter marvelCharacter = marvelCharacterTestSubscriber.getOnNextEvents().get(0);

        assertNotNull(marvelCharacter);
        assertEquals(marvelCharacter.getId(), FAKE_CHARACTER_ID);
        assertEquals(marvelCharacter.getName(), FAKE_CHARACTER_NAME);
        assertEquals(marvelCharacter.getDescription(), FAKE_CHARACTER_DESCRIPTION);

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
        marvelCharacters.add(hulk);

        MarvelCharacter cyclops = new MarvelCharacter(3904, "Cyclops", "Can kill instantly");
        marvelCharacters.add(cyclops);

        return marvelCharacters;
    }


}