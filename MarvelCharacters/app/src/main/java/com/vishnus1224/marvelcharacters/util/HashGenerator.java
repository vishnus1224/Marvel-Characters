package com.vishnus1224.marvelcharacters.util;

import com.vishnus1224.marvelcharacters.exception.MarvelApiException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Generates hash required by the marvel API for authorizing requests.
 * Created by Vishnu on 4/29/2016.
 */
@Singleton
public class HashGenerator {

    @Inject
    public HashGenerator(){

    }

    /**
     * Generates hash using the supplied parameters.
     * @param privateKey Marvel API private key.
     * @param publicKey Marvel API public key.
     * @param timestamp Timestamp.
     * @return String representation of the hash.
     * @throws MarvelApiException Thrown if hash could not be generated.
     */
    public String generateHash(String privateKey, String publicKey, String timestamp) throws MarvelApiException {

        try {

            String value = timestamp + privateKey + publicKey;
            MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5Encoder.digest(value.getBytes());

            StringBuilder md5 = new StringBuilder();

            for (int i = 0; i < md5Bytes.length; ++i) {
                md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));
            }

            return md5.toString();

        }catch (NoSuchAlgorithmException e) {
            throw new MarvelApiException("cannot generate the hash", e);

        }
    }

}
