package com.vishnus1224.marvelcharacters.api;

import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.ImageResourceDataWrapper;

import rx.Observable;

/**
 * REST API abstraction.
 * Created by Vishnu on 4/29/2016.
 */
public interface RESTAPI {

    /**
     *
     * Fetch marvel characters using the REST API.
     * @param offset Number of results that the result set should be offset.
     * @return Observable that emits CharacterDataWrapper items.
     */
    Observable<CharacterDataWrapper> fetchMarvelCharacters(int offset);

    /**
     * Searches for characters and wraps them in a data wrapper.
     * @param characterName Name of the character to search.
     * @return Observable data wrapper containing list of characters.
     */
    Observable<CharacterDataWrapper> searchMarvelCharacters(String characterName);

    /**
     * Get image resource data.
     * @param resourceURI resourceURI of the image.
     * @return Observable emitting wrapper of image resource data.
     */
    Observable<ImageResourceDataWrapper> getImageResourceData(String resourceURI);
}
