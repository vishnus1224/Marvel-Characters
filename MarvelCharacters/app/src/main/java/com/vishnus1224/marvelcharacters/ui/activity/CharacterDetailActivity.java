package com.vishnus1224.marvelcharacters.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.util.Constants;

/**
 * Activity for displaying character details.
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterDetailActivity extends BaseActivity {

    private MarvelCharacter marvelCharacter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        Bundle extras = getIntent().getExtras();

        getCharacterFromBundle(extras);
    }

    private void getCharacterFromBundle(Bundle extras) {

        //check if bundle is not null and it contains the character key.
        if(extras != null && extras.containsKey(Constants.KEY_CHARACTER)){

            marvelCharacter = extras.getParcelable(Constants.KEY_CHARACTER);

        }

    }
}
