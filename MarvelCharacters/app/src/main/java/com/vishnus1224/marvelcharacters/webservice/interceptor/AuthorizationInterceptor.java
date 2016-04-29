package com.vishnus1224.marvelcharacters.webservice.interceptor;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Intercepts the web service request and adds authorization parameters.
 * Created by Vishnu on 4/29/2016.
 */
public class AuthorizationInterceptor implements Interceptor {

    @Inject
    public AuthorizationInterceptor(){

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
