package com.vishnus1224.marvelcharacters.util;

import javax.inject.Inject;

/**
 * Helper class for extracting relevant parts of an URL.
 * Created by Vishnu on 5/1/2016.
 */
public class URLExtractor {

    @Inject
    public URLExtractor(){

    }

    /**
     * Remove the stringToRemove from the completeURL.
     * @param completeURL The complete URL string containing stringToRemove.
     * @param stringToRemove The string that needs to be removed.
     * @return A new string with stringToRemove removed.
     */
    public String removeStringFromURL(String completeURL, String stringToRemove){

        return completeURL.replace(stringToRemove, "");

    }
}
