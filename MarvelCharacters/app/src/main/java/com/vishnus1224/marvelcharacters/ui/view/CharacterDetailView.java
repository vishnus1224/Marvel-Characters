package com.vishnus1224.marvelcharacters.ui.view;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by Vishnu on 5/1/2016.
 */
public interface CharacterDetailView {

    /**
     * Show the view.
     * @param view view to show.
     */
    void showView(View view);

    /**
     * Hide the view.
     * @param view view to hide.
     */
    void hideView(View view);

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

    /**
     * Show a placeholder image in the image view.
     * Will be called whenever an error occurs while fetching image URL.
     * @param imageView The image view to set the image in.
     */
    void showPlaceholderImage(ImageView imageView);
}
