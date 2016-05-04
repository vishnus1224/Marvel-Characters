package com.vishnus1224.marvelcharacters.repository;

import com.vishnus1224.marvelcharacters.datastore.ImageResourceDataStore;
import com.vishnus1224.marvelcharacters.mapper.ImageResourceDataMapper;
import com.vishnus1224.marvelcharacters.model.CharacterResourceThumbnail;
import com.vishnus1224.marvelcharacters.model.ImageResourceDataWrapper;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class ImageResourceRepositoryImpl implements ImageResourceRepository {

    private ImageResourceDataStore imageResourceDataStore;

    private ImageResourceDataMapper imageResourceDataMapper;

    @Inject
    public ImageResourceRepositoryImpl(@Named("Cloud") ImageResourceDataStore imageResourceDataStore, ImageResourceDataMapper imageResourceDataMapper){

        this.imageResourceDataStore = imageResourceDataStore;

        this.imageResourceDataMapper = imageResourceDataMapper;
    }

    @Override
    public Observable<CharacterResourceThumbnail> getCharacterResourceThumbnail(String resourceURI) {
        return imageResourceDataStore.getImageResourceData(resourceURI)
                .map(new Func1<ImageResourceDataWrapper, CharacterResourceThumbnail>() {
                    @Override
                    public CharacterResourceThumbnail call(ImageResourceDataWrapper imageResourceDataWrapper) {

                        return imageResourceDataMapper.unwrapResourceThumbnail(imageResourceDataWrapper);
                    }
                });
    }
}
