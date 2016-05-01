package com.vishnus1224.marvelcharacters.datastore;

import com.vishnus1224.marvelcharacters.model.ImageResourceDataWrapper;

import rx.Observable;

/**
 * Image resource data store abstraction.
 * Created by Vishnu on 5/1/2016.
 */
public interface ImageResourceDataStore {

    /**
     * Get image resource data.
     * @param resourceURI resourceURI of the image.
     * @return Observable emitting wrapper of image resource data.
     */
    Observable<ImageResourceDataWrapper> getImageResourceData(String resourceURI);
}
