package com.vishnus1224.marvelcharacters.imageloader;

import android.widget.ImageView;

/**
 * Base class for handling image loading operations.
 * Created by Vishnu on 4/29/2016.
 */
public interface ImageDownloader {

    /**
     * Load the image from the URL into the image view resizing it if necessary.
     * @param imageURL Image URL.
     * @param imageView ImageView reference to load the image into.
     */
    void downloadImage(String imageURL, ImageView imageView);

}
