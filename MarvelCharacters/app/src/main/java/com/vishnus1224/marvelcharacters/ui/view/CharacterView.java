package com.vishnus1224.marvelcharacters.ui.view;

import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

import java.util.List;

/**
 * Handles UI operations related to the character list view.
 * Created by Vishnu on 4/29/2016.
 */
public interface CharacterView {

    /**
     * Show the progress bar.
     */
    void showProgressBar();

    /**
     * Hide the progress bar.
     */
    void hideProgressBar();

    /**
     * Show the list of characters in the UI.
     * @param marvelCharacters List of MarvelCharacters.
     */
    void showCharacterList(List<MarvelCharacter>marvelCharacters);

    /**
     * Inform user that an error has occurred.
     * @param message Error message.
     */
    void showError(String message);
}
