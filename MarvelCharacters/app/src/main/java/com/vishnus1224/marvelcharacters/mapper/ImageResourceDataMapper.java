package com.vishnus1224.marvelcharacters.mapper;

import com.vishnus1224.marvelcharacters.model.CharacterResourceThumbnail;
import com.vishnus1224.marvelcharacters.model.ImageResourceDataWrapper;

import javax.inject.Inject;

/**
 * Mapper for transforming data to desired format.
 * Created by Vishnu on 5/1/2016.
 */
public class ImageResourceDataMapper {

    @Inject
    public ImageResourceDataMapper(){

    }

    /**
     * Unwraps the wrapper and returns the thumbnail.
     * @param imageResourceDataWrapper Wrapper containing thumbnail data.
     * @return CharacterResourceThumbnail instance.
     */
    public CharacterResourceThumbnail unwrapResourceThumbnail(ImageResourceDataWrapper imageResourceDataWrapper){

        return imageResourceDataWrapper.getImageResourceDataHolder().getCharacterResourceThumbnail();

    }
}
