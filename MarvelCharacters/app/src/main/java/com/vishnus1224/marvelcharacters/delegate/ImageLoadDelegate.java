package com.vishnus1224.marvelcharacters.delegate;

import android.widget.ImageView;

/**
 * Delegate for fetching image data to load.
 * Created by Vishnu on 5/1/2016.
 */
public interface ImageLoadDelegate {

    /**
     * Load image data using the resourceURI.
     * @param resourceURI The resourceURI containing image data.
     * @param imageView ImageView to load the image into.
     */
    void loadImageData(String resourceURI, ImageView imageView);
}
