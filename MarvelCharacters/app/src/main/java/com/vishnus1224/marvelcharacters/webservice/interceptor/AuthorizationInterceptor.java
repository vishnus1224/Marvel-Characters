package com.vishnus1224.marvelcharacters.webservice.interceptor;

import com.vishnus1224.marvelcharacters.exception.HashGenerationException;
import com.vishnus1224.marvelcharacters.util.Constants;
import com.vishnus1224.marvelcharacters.util.HashGenerator;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Intercepts the web service request and adds authorization parameters.
 * Created by Vishnu on 4/29/2016.
 */
public class AuthorizationInterceptor implements Interceptor {

    private static final String KEY_TIMESTAMP = "ts";
    private static final String KEY_HASH = "hash";
    private static final String KEY_APIKEY = "apikey";

    private HashGenerator hashGenerator;

    @Inject
    public AuthorizationInterceptor(HashGenerator hashGenerator){

        this.hashGenerator = hashGenerator;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        //Get the current system time and convert it to a string.
        String timestamp = String.valueOf(System.currentTimeMillis());

        String hash = null;
        try {
            //generate the hash using the hash generator.
            hash = hashGenerator.generateHash(Constants.ZZY, Constants.ZZX, timestamp);
        } catch (HashGenerationException e) {
            e.printStackTrace();
        }

        //get the original request.
        Request request = chain.request();

        //add query parameters to the request for authorization.
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter(KEY_TIMESTAMP, timestamp)
                .addQueryParameter(KEY_APIKEY, Constants.ZZX)
                .addQueryParameter(KEY_HASH, hash)
                .build();

        //build a new request.
        request = request.newBuilder().url(url).build();

        //proceed with the new request.
        return chain.proceed(request);

    }
}
