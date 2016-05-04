package com.vishnus1224.marvelcharacters.ui.view;

import android.database.Cursor;

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

    /**
     * Add a footer to the ListView.
     */
    void addListViewFooter();

    /**
     * Remove footer from the ListView.
     */
    void removeListViewFooter();

    /**
     * Show progress bar in the footer view.
     */
    void showFooterProgress();

    /**
     * Hide progress bar in the footer view.
     */
    void hideFooterProgress();

    /**
     * Show search suggestions to the user.
     * @param cursor Cursor containing the data.
     */
    void setSuggestionsAdapter(Cursor cursor);

    /**
     * Show character details activity.
     * @param marvelCharacter MarvelCharacter instance.
     */
    void showCharacterDetails(MarvelCharacter marvelCharacter);

    /**
     * Provide user with a retry option.
     * @param message message to be displayed.
     */
    void showRetryOption(String message);
}
