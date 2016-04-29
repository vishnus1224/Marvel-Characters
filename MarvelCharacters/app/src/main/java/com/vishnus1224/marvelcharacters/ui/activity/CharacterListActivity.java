package com.vishnus1224.marvelcharacters.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.delegate.ListViewScrollDelegate;
import com.vishnus1224.marvelcharacters.di.component.ActivityComponent;
import com.vishnus1224.marvelcharacters.di.component.DaggerActivityComponent;
import com.vishnus1224.marvelcharacters.di.module.ActivityModule;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.ui.adapter.CharacterListAdapter;
import com.vishnus1224.marvelcharacters.ui.presenter.CharacterListPresenter;
import com.vishnus1224.marvelcharacters.ui.view.CharacterView;

import java.util.List;

import javax.inject.Inject;

public class CharacterListActivity extends BaseActivity implements CharacterView, ListViewScrollDelegate.BottomHitListener{


    // *******************************************************************************
    // View definitions.
    private ListView characterListView;
    private ProgressBar progressBar;

    //******************************************************************************
    // View definition end.

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

    /**
     * ListView scroll delegate instance.
     */
    private ListViewScrollDelegate listViewScrollDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        setupViews();

        injectActivityComponent();

        initializePresenter();

        setListViewAdapter();

        fetchCharacters();

        setListViewScrollDelegate();
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


    private void fetchCharacters() {

        showProgressBar();

        characterListPresenter.fetchCharacters();

    }


    private void setListViewScrollDelegate() {

        listViewScrollDelegate = new ListViewScrollDelegate(this);

        characterListView.setOnScrollListener(listViewScrollDelegate);

    }

    // View Methods.
    //===========================================================================================

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

        Toast.makeText(CharacterListActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    //View Method End.
    //==============================================================================================


    //ListView scroll delegate method.
    @Override
    public void onBottomHit() {

        Toast.makeText(CharacterListActivity.this, "Bottom", Toast.LENGTH_SHORT).show();

    }

}
