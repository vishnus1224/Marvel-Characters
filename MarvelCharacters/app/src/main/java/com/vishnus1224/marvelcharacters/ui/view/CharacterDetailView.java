package com.vishnus1224.marvelcharacters.ui.view;

import android.widget.ImageView;

/**
 * Created by Vishnu on 5/1/2016.
 */
public interface CharacterDetailView {

    /**
     * Show error to the user.
     * @param message error message to be shown.
     */
    void showError(String message);

    /**
     * Load image in the image view from the specified url.
     * @param imageURL URL of the image.
     * @param imageView ImageView to load the image into.
     */
    void loadImage(String imageURL, ImageView imageView);
}
