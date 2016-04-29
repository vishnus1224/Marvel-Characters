package com.vishnus1224.marvelcharacters.exception;

/**
 * Thrown when an error occurs while generating the hash.
 * Created by Vishnu on 4/29/2016.
 */
public class HashGenerationException extends Exception {

    public HashGenerationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
