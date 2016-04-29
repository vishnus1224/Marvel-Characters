package com.vishnus1224.marvelcharacters.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.di.component.ActivityComponent;
import com.vishnus1224.marvelcharacters.di.component.DaggerActivityComponent;
import com.vishnus1224.marvelcharacters.di.module.ActivityModule;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.ui.adapter.CharacterListAdapter;
import com.vishnus1224.marvelcharacters.ui.presenter.CharacterListPresenter;
import com.vishnus1224.marvelcharacters.ui.view.CharacterView;

import java.util.List;

import javax.inject.Inject;

public class CharacterListActivity extends BaseActivity implements CharacterView{

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
     * Inject the presenter.
     */
    @Inject
    CharacterListPresenter characterListPresenter;

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

        initializePresenter();

        setListViewAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        characterListPresenter.destroy();
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

        activityComponent.inject(this);

    }


    private void setListViewAdapter() {

        characterListView.setAdapter(characterListAdapter);
        
    }


    private void initializePresenter() {

        characterListPresenter.init(this);

    }

    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showCharacterList(List<MarvelCharacter> marvelCharacters) {

        characterListAdapter.updateDataSet(marvelCharacters);

    }

    @Override
    public void showError(String message) {

    }
}
