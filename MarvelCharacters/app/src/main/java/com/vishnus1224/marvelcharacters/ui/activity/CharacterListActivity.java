package com.vishnus1224.marvelcharacters.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.di.component.ActivityComponent;
import com.vishnus1224.marvelcharacters.di.component.DaggerActivityComponent;
import com.vishnus1224.marvelcharacters.di.module.ActivityModule;
import com.vishnus1224.marvelcharacters.ui.adapter.CharacterListAdapter;

import javax.inject.Inject;

public class CharacterListActivity extends BaseActivity {

    /**
     * *******************************************************************************
     * View definitions.
     */
    private ListView characterListView;
    private ProgressBar progressBar;

    /**
     * ******************************************************************************
     */

    /**
     * Adapter for displaying a list of characters.
     */
    @Inject
    CharacterListAdapter characterListAdapter;

    /**
     * Activity component for injecting the dependencies.
     */
    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        setupViews();

        injectActivityComponent();

        setListViewAdapter();
    }

    private void setupViews() {

        characterListView = (ListView) findViewById(R.id.characterListView);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    private void injectActivityComponent() {

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

    }


    private void setListViewAdapter() {

        characterListView.setAdapter(characterListAdapter);
        
    }
}
