package com.vishnus1224.marvelcharacters.ui.activity;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.model.CharacterResourceThumbnail;
import com.vishnus1224.marvelcharacters.model.ComicContainer;
import com.vishnus1224.marvelcharacters.model.ComicSummary;
import com.vishnus1224.marvelcharacters.model.EventContainer;
import com.vishnus1224.marvelcharacters.model.EventSummary;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.model.MarvelCharacterThumbnail;
import com.vishnus1224.marvelcharacters.model.SeriesContainer;
import com.vishnus1224.marvelcharacters.model.SeriesSummary;
import com.vishnus1224.marvelcharacters.model.StoryContainer;
import com.vishnus1224.marvelcharacters.model.StorySummary;
import com.vishnus1224.marvelcharacters.util.Constants;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

/**
 * Created by Vishnu on 5/4/2016.
 */
public class CharacterDetailActivityTest {

    private static final int FAKE_CHARACTER_ID = 1002;
    private static final String FAKE_CHARACTER_NAME = "Hulk";
    private static final String FAKE_CHARACTER_DESCRIPTION = "Strongest of them all";

    private static final String FAKE_PATH = "users/manuals/63";
    private static final String FAKE_EXTENSION = "tuw";

    @Rule
    public ActivityTestRule<CharacterDetailActivity> activityTestRule =
            new ActivityTestRule<>(CharacterDetailActivity.class);


    @Test
    public void displayCharacterNameTest(){

        MarvelCharacter marvelCharacter = createFakeCharacter();

        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_CHARACTER, marvelCharacter);

        activityTestRule.launchActivity(intent);

        //check if the correct text is displayed on the view.
        onView(withId(R.id.characterDetailName)).check(matches(withText(FAKE_CHARACTER_NAME)));

    }

    @Test
    public void displayCharacterDescriptionTest(){

        MarvelCharacter marvelCharacter = createFakeCharacter();

        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_CHARACTER, marvelCharacter);

        activityTestRule.launchActivity(intent);

        //check if the description displayed on the view is the one specified when creating the character.
        onView(withId(R.id.characterDescription)).check(matches(withText(FAKE_CHARACTER_DESCRIPTION)));
    }

    private MarvelCharacter createFakeCharacter(){

        ComicContainer comicContainer = new ComicContainer();
        comicContainer.setItems(new ArrayList<ComicSummary>());

        SeriesContainer seriesContainer = new SeriesContainer();
        seriesContainer.setItems(new ArrayList<SeriesSummary>());

        EventContainer eventContainer = new EventContainer();
        eventContainer.setItems(new ArrayList<EventSummary>());

        StoryContainer storyContainer = new StoryContainer();
        storyContainer.setItems(new ArrayList<StorySummary>());

        MarvelCharacterThumbnail marvelCharacterThumbnail = new MarvelCharacterThumbnail(FAKE_PATH, FAKE_EXTENSION);

        MarvelCharacter marvelCharacter = new MarvelCharacter(FAKE_CHARACTER_ID, FAKE_CHARACTER_NAME, FAKE_CHARACTER_DESCRIPTION);

        marvelCharacter.setThumbnail(marvelCharacterThumbnail);
        marvelCharacter.setComicContainer(comicContainer);
        marvelCharacter.setSeriesContainer(seriesContainer);
        marvelCharacter.setEventContainer(eventContainer);
        marvelCharacter.setStoryContainer(storyContainer);

        return marvelCharacter;
    }
}
