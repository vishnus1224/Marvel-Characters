package com.vishnus1224.marvelcharacters.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


/**
 * Created by Vishnu on 5/2/2016.
 */
public class MarvelCharacterThumbnailTest {

    private static final String FAKE_PATH = "files/data/myFile";
    private static final String FAKE_EXTENSION = "img";

    MarvelCharacterThumbnail marvelCharacterThumbnail;

    @Before
    public void setUp() throws Exception {

        marvelCharacterThumbnail = new MarvelCharacterThumbnail();
    }


    @Test
    public void testGetFinalPath() throws Exception {

        marvelCharacterThumbnail.setPath(FAKE_PATH);
        marvelCharacterThumbnail.setExtension(FAKE_EXTENSION);

        String correctPath = FAKE_PATH + "." + FAKE_EXTENSION;

        String finalPath = marvelCharacterThumbnail.getFinalPath();

        assertNotNull(finalPath);
        assertEquals(finalPath, correctPath);

    }
}