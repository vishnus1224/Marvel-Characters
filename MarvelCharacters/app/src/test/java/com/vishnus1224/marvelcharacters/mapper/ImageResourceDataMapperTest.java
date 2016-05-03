package com.vishnus1224.marvelcharacters.mapper;

import com.vishnus1224.marvelcharacters.model.CharacterResourceThumbnail;
import com.vishnus1224.marvelcharacters.model.ImageResourceDataWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by Vishnu on 5/3/2016.
 */
public class ImageResourceDataMapperTest {

    private static final String FAKE_PATH = "volumes/users/me";
    private static final String FAKE_EXTENSION = ".lpy";

    @Mock
    ImageResourceDataMapper imageResourceDataMapper;

    @Mock
    ImageResourceDataWrapper imageResourceDataWrapper;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testUnwrapResourceThumbnail() throws Exception {

        CharacterResourceThumbnail characterResourceThumbnail = new CharacterResourceThumbnail(FAKE_PATH, FAKE_EXTENSION);

        given(imageResourceDataMapper.unwrapResourceThumbnail(imageResourceDataWrapper)).willReturn(characterResourceThumbnail);

        CharacterResourceThumbnail thumbnail = imageResourceDataMapper.unwrapResourceThumbnail(imageResourceDataWrapper);

        String finalPath = FAKE_PATH + "." + FAKE_EXTENSION;

        assertNotNull(thumbnail);
        assertEquals(thumbnail.getPath(), FAKE_PATH);
        assertEquals(thumbnail.getExtension(), FAKE_EXTENSION);
        assertEquals(thumbnail.getFinalPath(), finalPath);
    }
}