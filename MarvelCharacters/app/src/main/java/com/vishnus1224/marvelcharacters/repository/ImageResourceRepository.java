package com.vishnus1224.marvelcharacters.repository;

import com.vishnus1224.marvelcharacters.model.CharacterResourceThumbnail;

import rx.Observable;

/**
 * Repository containing data for image resources.
 * Created by Vishnu on 5/1/2016.
 */
public interface ImageResourceRepository {


    /**
     * Get the thumbnail for the resourceURI.
     * @param resourceURI resourceURI to fetch the thumbnail from.
     * @return Observable emitting characterResourceThumbnail instance.
     */
    Observable<CharacterResourceThumbnail> getCharacterResourceThumbnail(String resourceURI);
}
