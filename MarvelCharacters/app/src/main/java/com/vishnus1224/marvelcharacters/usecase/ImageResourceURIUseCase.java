package com.vishnus1224.marvelcharacters.usecase;

import com.vishnus1224.marvelcharacters.repository.ImageResourceRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class ImageResourceURIUseCase extends UseCase {

    private String resourceURI;

    private ImageResourceRepository imageResourceRepository;

    @Inject
    public ImageResourceURIUseCase(ImageResourceRepository imageResourceRepository){

        this.imageResourceRepository = imageResourceRepository;

    }

    public void setResourceURI(String resourceURI){
        this.resourceURI = resourceURI;
    }

    @Override
    protected Observable buildUseCase() {

        return imageResourceRepository.getCharacterResourceThumbnail(resourceURI);

    }
}
