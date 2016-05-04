package com.vishnus1224.marvelcharacters.ui.activity;

import android.support.v7.app.AppCompatActivity;

import com.vishnus1224.marvelcharacters.MarvelCharacters;
import com.vishnus1224.marvelcharacters.di.component.ApplicationComponent;

/**
 * Base activity to be extended by all activities in the application.
 * Created by Vishnu on 4/28/2016.
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Get the Application instance.
     * @return MarvelCharacters instance.
     */
    protected MarvelCharacters getMarvelCharacters(){
        return (MarvelCharacters) getApplication();
    }

    /**
     * Get the application component from the application class for providing it to dependent components.
     * @return ApplicationComponent instance.
     */
    protected ApplicationComponent getApplicationComponent(){
        return getMarvelCharacters().getApplicationComponent();
    }


}
