package com.vishnus1224.marvelcharacters.webservice;

import com.vishnus1224.marvelcharacters.model.CharacterDataWrapper;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Vishnu on 4/28/2016.
 */
public interface MarvelWebService {

    String BASE_URL = "https://gateway.marvel.com/";

    @GET("v1/public/characters")
    Observable<CharacterDataWrapper> fetchMarvelCharacters(@Query("offset") int offset);
}
