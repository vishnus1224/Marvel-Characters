package com.vishnus1224.marvelcharacters.di.module;

import com.vishnus1224.marvelcharacters.webservice.MarvelWebService;
import com.vishnus1224.marvelcharacters.webservice.interceptor.AuthorizationInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module that provides dependencies for making web service calls.
 * Created by Vishnu on 4/28/2016.
 */
@Module
public class NetModule {

    /**
     * Base web service URL.
     */
    private String baseURL;

    public NetModule(String baseURL){
        this.baseURL = baseURL;
    }

    /**
     * Provide interceptor and adding authorization parameters to the web service request.
     * @param authorizationInterceptor AuthorizationInterceptor instance.
     * @return AuthorizationInterceptor instance.
     */
    @Provides @Singleton
    Interceptor provideAuthorizationInterceptor(AuthorizationInterceptor authorizationInterceptor){
        return authorizationInterceptor;
    }

    /**
     * Provides a single instance of OkHttpClient throughout the application.
     * @return Instance of OkHttpClient.
     */
    @Provides @Singleton
    OkHttpClient provideOkHttpClient(Interceptor authorizationInterceptor){
        return new OkHttpClient.Builder().addInterceptor(authorizationInterceptor).build();
    }

    /**
     * Provides a single instance of GSON converter factory required by retrofit to map JSON string to JAVA objects.
     * @return Instance of GSONConverterFactory.
     */
    @Provides @Singleton
    Converter.Factory provideGSONConverterFactory(){
        return GsonConverterFactory.create();
    }

    /**
     * Provides a single instance of RxJavaCallAdapterFactory needed by retrofit to return Observables.
     * @return RxJavaCallAdapterFactory instance.
     */
    @Provides @Singleton
    CallAdapter.Factory provideRxCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }

    /**
     * Provides a single instance of Retrofit to be used throughout the application.
     * @param okHttpClient OkHttpClient instance.
     * @param converterFactory ConverterFactory instance.
     * @param callAdapterFactory CallAdapterFactory instance.
     * @return Retrofit instance.
     */
    @Provides @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    /**
     * Provides MarvelWebService instance for accessing the Marvel API.
     * @param retrofit Retrofit instance.
     * @return MarvelWebService instance.
     */
    @Provides @Singleton
    MarvelWebService provideMarvelWebService(Retrofit retrofit){
        return retrofit.create(MarvelWebService.class);
    }
}
