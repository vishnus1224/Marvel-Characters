package com.vishnus1224.marvelcharacters.datastore;

import com.vishnus1224.marvelcharacters.api.MarvelRESTAPI;
import com.vishnus1224.marvelcharacters.api.RESTAPI;
import com.vishnus1224.marvelcharacters.cache.CharacterCache;
import com.vishnus1224.marvelcharacters.cache.LruMemoryCharacterCache;
import com.vishnus1224.marvelcharacters.model.CharacterDataHolder;
import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.*;


/**
 * Created by Vishnu on 5/2/2016.
 */
public class CloudCharacterDataStoreTest {

    private RESTAPI restapi;

    private CharacterCache characterCache;

    private CloudCharacterDataStore cloudCharacterDataStore;

    private List<MarvelCharacter> marvelCharacterList;

    private CharacterDataHolder characterDataHolder;

    private CharacterDataWrapper characterDataWrapper;

    @Before
    public void setUp() throws Exception {

        restapi = mock(MarvelRESTAPI.class);

        characterCache = mock(LruMemoryCharacterCache.class);

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
        when(restapi.fetchMarvelCharacters(0)).thenReturn(Observable.just(characterDataWrapper));

        //get observable from the rest api.
        Observable<CharacterDataWrapper> marvelCharacterObservable = restapi.fetchMarvelCharacters(0);

        TestSubscriber<CharacterDataWrapper> testSubscriber = new TestSubscriber<>();

        marvelCharacterObservable.subscribe(testSubscriber);

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

        assertEquals(hulk.getId(), 1002);
        assertEquals(hulk.getName(), "Hulk");
        assertEquals(hulk.getDescription(), "Strongest of them all");

    }

    @Test
    public void testSearchMarvelCharacters() throws Exception {

    }

    @Test
    public void testFetchCharacterByID() throws Exception {

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

        MarvelCharacter hulk = new MarvelCharacter(1002, "Hulk", "Strongest of them all");
        marvelCharacters.add(hulk);

        MarvelCharacter cyclops = new MarvelCharacter(3904, "Cyclops", "Can kill instantly");
        marvelCharacters.add(cyclops);

        return marvelCharacters;
    }


}