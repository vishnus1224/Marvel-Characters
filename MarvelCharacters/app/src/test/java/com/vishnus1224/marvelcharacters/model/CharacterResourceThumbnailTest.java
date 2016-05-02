package com.vishnus1224.marvelcharacters.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vishnu on 5/2/2016.
 */
public class CharacterResourceThumbnailTest {

    private static final String FAKE_PATH = "users/user/127";
    private static final String FAKE_EXTENSION = "ttf";

    private CharacterResourceThumbnail characterResourceThumbnail;

    @Before
    public void setUp() throws Exception {

        characterResourceThumbnail = new CharacterResourceThumbnail();

    }

    @Test
    public void testGetFinalPath() throws Exception {

        characterResourceThumbnail.setPath(FAKE_PATH);
        characterResourceThumbnail.setExtension(FAKE_EXTENSION);

        String actualPath = FAKE_PATH + "." + FAKE_EXTENSION;

        String finalPath = characterResourceThumbnail.getFinalPath();

        assertNotNull(finalPath);
        assertEquals(finalPath, actualPath);

    }
}