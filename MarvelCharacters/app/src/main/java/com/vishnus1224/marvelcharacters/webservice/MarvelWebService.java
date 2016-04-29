package com.vishnus1224.marvelcharacters.webservice;

import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Vishnu on 4/28/2016.
 */
public interface MarvelWebService {

    String BASE_URL = "https://gateway.marvel.com/";

    @GET("v1/public/characters")
    Observable<List<MarvelCharacter>> fetchMarvelCharacters();
}
