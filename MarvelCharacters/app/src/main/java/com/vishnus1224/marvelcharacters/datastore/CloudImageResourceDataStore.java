package com.vishnus1224.marvelcharacters.datastore;

import com.vishnus1224.marvelcharacters.api.RESTAPI;
import com.vishnus1224.marvelcharacters.cache.ImageResourceCache;
import com.vishnus1224.marvelcharacters.model.ImageResourceDataWrapper;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class CloudImageResourceDataStore implements ImageResourceDataStore  {

    private ImageResourceCache imageResourceCache;

    private RESTAPI restapi;

    @Inject
    public CloudImageResourceDataStore(RESTAPI restapi, ImageResourceCache imageResourceCache){

        this.restapi = restapi;

        this.imageResourceCache = imageResourceCache;
    }

    @Override
    public Observable<ImageResourceDataWrapper> getImageResourceData(final String resourceURI) {

        //get the entry from the cache if it exists.
        if(imageResourceCache.containsKey(resourceURI)){

            return Observable.just(imageResourceCache.getEntry(resourceURI));

        }else {

            return restapi.getImageResourceData(resourceURI)
                    .doOnNext(new Action1<ImageResourceDataWrapper>() {
                @Override
                public void call(ImageResourceDataWrapper imageResourceDataWrapper) {

                    //save the entry in the cache.
                    imageResourceCache.addEntry(resourceURI, imageResourceDataWrapper);

                }
            });

        }
    }
}
