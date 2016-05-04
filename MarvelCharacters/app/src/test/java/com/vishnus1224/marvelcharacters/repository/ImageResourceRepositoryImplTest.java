package com.vishnus1224.marvelcharacters.repository;

import com.vishnus1224.marvelcharacters.datastore.ImageResourceDataStore;
import com.vishnus1224.marvelcharacters.mapper.ImageResourceDataMapper;
import com.vishnus1224.marvelcharacters.model.CharacterResourceThumbnail;
import com.vishnus1224.marvelcharacters.model.ImageResourceDataWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by Vishnu on 5/3/2016.
 */
public class ImageResourceRepositoryImplTest {

    private static final String FAKE_RESOURCE_ID = "public/images/78422";

    @Mock
    ImageResourceDataStore imageResourceDataStore;

    @Mock
    ImageResourceDataMapper imageResourceDataMapper;

    @Mock
    ImageResourceDataWrapper imageResourceDataWrapper;

    @Mock
    CharacterResourceThumbnail characterResourceThumbnail;

    private ImageResourceRepositoryImpl imageResourceRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        imageResourceRepository = new ImageResourceRepositoryImpl(imageResourceDataStore, imageResourceDataMapper);
    }

    @Test
    public void testGetCharacterResourceThumbnail() throws Exception {

        given(imageResourceDataStore.getImageResourceData(anyString())).willReturn(Observable.just(imageResourceDataWrapper));

        Observable<CharacterResourceThumbnail> wrapperObservable = imageResourceRepository.getCharacterResourceThumbnail(FAKE_RESOURCE_ID);

        TestSubscriber<CharacterResourceThumbnail> testSubscriber = new TestSubscriber<>();

        wrapperObservable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();

        verify(imageResourceDataStore).getImageResourceData(FAKE_RESOURCE_ID);
        verify(imageResourceDataStore, times(1)).getImageResourceData(FAKE_RESOURCE_ID);
        verify(imageResourceDataMapper).unwrapResourceThumbnail(imageResourceDataWrapper);
        verify(imageResourceDataMapper, times(1)).unwrapResourceThumbnail(imageResourceDataWrapper);

    }
}