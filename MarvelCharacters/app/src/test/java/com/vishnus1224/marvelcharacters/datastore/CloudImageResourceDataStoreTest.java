package com.vishnus1224.marvelcharacters.datastore;

import com.vishnus1224.marvelcharacters.api.RESTAPI;
import com.vishnus1224.marvelcharacters.cache.ImageResourceCache;
import com.vishnus1224.marvelcharacters.model.CharacterImageResource;
import com.vishnus1224.marvelcharacters.model.CharacterResourceThumbnail;
import com.vishnus1224.marvelcharacters.model.ImageResourceDataHolder;
import com.vishnus1224.marvelcharacters.model.ImageResourceDataWrapper;

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
 * Created by Vishnu on 5/2/2016.
 */
public class CloudImageResourceDataStoreTest {

    private static final String FAKE_RESOURCE_URI = "api/resources/206";

    @Mock
    RESTAPI restapi;

    @Mock
    ImageResourceCache imageResourceCache;

    private CloudImageResourceDataStore cloudImageResourceDataStore;

    private ImageResourceDataWrapper imageResourceDataWrapper;

    private ImageResourceDataHolder imageResourceDataHolder;

    private List<CharacterImageResource> characterImageResourceList;

    private CharacterResourceThumbnail characterResourceThumbnail;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        cloudImageResourceDataStore = new CloudImageResourceDataStore(restapi, imageResourceCache);

        characterResourceThumbnail = new CharacterResourceThumbnail();

        characterImageResourceList = characterImageResourceList(characterResourceThumbnail);

        imageResourceDataHolder = createImageResourceDataHolder(characterImageResourceList);

        imageResourceDataWrapper = createImageResourceDataWrapper(imageResourceDataHolder);

    }

    @Test
    public void testGetImageResourceData() throws Exception {

        //given that the entry exists in the cache.
        given(imageResourceCache.containsKey(anyString())).willReturn(true);

        //return mock observable when the test method is called.
        when(imageResourceCache.getEntry(anyString())).thenReturn(imageResourceDataWrapper);

        Observable<ImageResourceDataWrapper> imageResourceDataWrapperObservable = cloudImageResourceDataStore.getImageResourceData(FAKE_RESOURCE_URI);

        TestSubscriber<ImageResourceDataWrapper> testSubscriber = new TestSubscriber<>();

        imageResourceDataWrapperObservable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValues(imageResourceDataWrapper);

        verify(imageResourceCache).containsKey(FAKE_RESOURCE_URI);
        verify(imageResourceCache).getEntry(FAKE_RESOURCE_URI);
        verify(imageResourceCache, times(1)).getEntry(FAKE_RESOURCE_URI);

    }

    public List<CharacterImageResource> characterImageResourceList(CharacterResourceThumbnail characterResourceThumbnail){

        List<CharacterImageResource> characterImageResources = new ArrayList<>();

        CharacterImageResource characterImageResource = new CharacterImageResource();
        characterImageResource.setCharacterResourceThumbnail(characterResourceThumbnail);

        characterImageResources.add(characterImageResource);

        return characterImageResources;
    }

    public ImageResourceDataHolder createImageResourceDataHolder(List<CharacterImageResource> characterImageResourceList){

        ImageResourceDataHolder imageResourceDataHolder = new ImageResourceDataHolder();

        imageResourceDataHolder.setCharacterImageResource(characterImageResourceList);

        return imageResourceDataHolder;

    }

    public ImageResourceDataWrapper createImageResourceDataWrapper(ImageResourceDataHolder imageResourceDataHolder){

        ImageResourceDataWrapper imageResourceDataWrapper = new ImageResourceDataWrapper();

        imageResourceDataWrapper.setImageResourceDataHolder(imageResourceDataHolder);

        return imageResourceDataWrapper;

    }
}