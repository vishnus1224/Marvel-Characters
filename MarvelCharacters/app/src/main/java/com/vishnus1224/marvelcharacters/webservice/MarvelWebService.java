package com.vishnus1224.marvelcharacters.webservice;

import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;
import com.vishnus1224.marvelcharacters.model.ImageResourceDataWrapper;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Vishnu on 4/28/2016.
 */
public interface MarvelWebService {

    String BASE_URL = "http://gateway.marvel.com/";

    @GET("v1/public/characters")
    Observable<CharacterDataWrapper> fetchMarvelCharacters(@Query("offset") int offset);

    @GET("v1/public/characters")
    Observable<CharacterDataWrapper> searchMarvelCharacters(@Query("nameStartsWith") String characterName, @Query("limit") int limit);

    @GET("{resourceURI}")
    Observable<ImageResourceDataWrapper> getImageResourceData(@Path(value = "resourceURI", encoded = true) String resourceURI);
}
