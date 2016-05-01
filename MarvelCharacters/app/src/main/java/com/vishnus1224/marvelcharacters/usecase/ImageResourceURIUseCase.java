package com.vishnus1224.marvelcharacters.usecase;

import rx.Observable;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class ImageResourceURIUseCase extends UseCase {

    private String resourceURI;

    public void setResourceURI(String resourceURI){
        this.resourceURI = resourceURI;
    }

    @Override
    protected Observable buildUseCase() {
        return null;
    }
}
