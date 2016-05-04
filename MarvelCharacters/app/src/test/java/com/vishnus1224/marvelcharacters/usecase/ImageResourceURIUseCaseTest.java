package com.vishnus1224.marvelcharacters.usecase;

import com.vishnus1224.marvelcharacters.repository.ImageResourceRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Vishnu on 5/3/2016.
 */
public class ImageResourceURIUseCaseTest {

    private static final String FAKE_RESOURCE_ID = "5422";

    @Mock
    ImageResourceRepository imageResourceRepository;

    private ImageResourceURIUseCase imageResourceURIUseCase;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        imageResourceURIUseCase = new ImageResourceURIUseCase(imageResourceRepository);
    }

    @Test
    public void testBuildUseCase() throws Exception {

        imageResourceURIUseCase.setResourceURI(FAKE_RESOURCE_ID);

        imageResourceURIUseCase.buildUseCase();

        verify(imageResourceRepository).getCharacterResourceThumbnail(FAKE_RESOURCE_ID);
        verify(imageResourceRepository, times(1)).getCharacterResourceThumbnail(FAKE_RESOURCE_ID);

        verifyZeroInteractions(imageResourceRepository);

    }
}