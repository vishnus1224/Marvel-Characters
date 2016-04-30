package com.vishnus1224.marvelcharacters.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.delegate.ListViewScrollDelegate;
import com.vishnus1224.marvelcharacters.delegate.SearchViewQueryObservableDelegate;
import com.vishnus1224.marvelcharacters.di.component.ActivityComponent;
import com.vishnus1224.marvelcharacters.di.component.DaggerActivityComponent;
import com.vishnus1224.marvelcharacters.di.module.ActivityModule;
import com.vishnus1224.marvelcharacters.imageloader.ImageDownloader;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.ui.adapter.CharacterListAdapter;
import com.vishnus1224.marvelcharacters.ui.adapter.CharacterSearchSuggestionsAdapter;
import com.vishnus1224.marvelcharacters.ui.presenter.CharacterListPresenter;
import com.vishnus1224.marvelcharacters.ui.view.CharacterView;
import com.vishnus1224.marvelcharacters.util.Constants;
import com.vishnus1224.marvelcharacters.util.ScreenSizeConversionUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class CharacterListActivity extends BaseActivity implements CharacterView, ListViewScrollDelegate.BottomHitListener, MenuItemCompat.OnActionExpandListener{


    // *******************************************************************************
    // View definitions.
    private ListView characterListView;
    private ProgressBar progressBar;

    private View listViewFooter;
    private ProgressBar footerProgressBar;

    private SearchView searchView;

    //******************************************************************************
    // View definition end.

    /**
     * Inject imageDownloader for passing to the suggestions adapter.
     */
    @Inject
    ImageDownloader imageDownloader;

    /**
     * Inject conversion util for passing to the suggestions adapter.
     */
    @Inject
    ScreenSizeConversionUtil screenSizeConversionUtil;

    /**
     * Inject layout inflater for inflating the list view footer.
     */
    @Inject
    LayoutInflater layoutInflater;

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
     * Inject the observable delegate to listen for query text update.
     */
    @Inject
    SearchViewQueryObservableDelegate searchViewQueryObservableDelegate;

    /**
     * Activity component for injecting the dependencies.
     */
    private ActivityComponent activityComponent;

    /**
     * ListView scroll delegate instance.
     */
    private ListViewScrollDelegate listViewScrollDelegate;

    /**
     * Adapter for providing suggestions in the search view.
     */
    private CharacterSearchSuggestionsAdapter characterSearchSuggestionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        setupViews();

        injectActivityComponent();

        setupListViewFooter();

        initializePresenter();

        setListViewAdapter();

        fetchCharacters();

        setListViewScrollDelegate();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.character_search_menu, menu);

        //Associate the searchable info with the search view.
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem searchItem = menu.findItem(R.id.characterSearch);

        searchView = (SearchView) searchItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        //add expand and collapse listener to the search view.
        MenuItemCompat.setOnActionExpandListener(searchItem, this);

        //set the subscriber to listen for query change event.
        //subscriber's onNext event will be called when query does not change for 500 milliseconds.
        searchViewQueryObservableDelegate.queryTextChangeObservable(searchView, 500L, queryChangeSubscriber);

        //create the suggestions adapter and set it on the search view.
        characterSearchSuggestionsAdapter = new CharacterSearchSuggestionsAdapter(this, R.layout.adapter_character_search, null,
                Constants.SUGGESTIONS_ADAPTER_COLUMNS, null, 0, imageDownloader, screenSizeConversionUtil);

        searchView.setSuggestionsAdapter(characterSearchSuggestionsAdapter);

        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        characterListPresenter.destroy();

        searchViewQueryObservableDelegate.unSubscribe();
    }

    private void setupViews() {

        characterListView = (ListView) findViewById(R.id.characterListView);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    private void setupListViewFooter() {

        listViewFooter = layoutInflater.inflate(R.layout.character_listview_footer, characterListView, false);

        footerProgressBar = (ProgressBar) listViewFooter.findViewById(R.id.progressBar);

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

    private void hideSoftKeyboard(){

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

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

        //update total item count in the scroll delegate.
        listViewScrollDelegate.addToTotalItemCount(marvelCharacters.size());

        characterListAdapter.updateDataSet(marvelCharacters);

    }

    @Override
    public void showError(String message) {

        Toast.makeText(CharacterListActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void addListViewFooter() {

        //Add footer view if one does not already exist.
        if(characterListView.getFooterViewsCount() == 0) {
            characterListView.addFooterView(listViewFooter);
        }

    }

    @Override
    public void removeListViewFooter() {

        if(listViewFooter != null && characterListView.getFooterViewsCount() > 0){

            characterListView.removeFooterView(listViewFooter);

        }

    }

    @Override
    public void showFooterProgress() {

        footerProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideFooterProgress() {

        footerProgressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void setSuggestionsAdapter(Cursor cursor) {

        characterSearchSuggestionsAdapter.changeCursor(cursor);

    }

    //View Method End.
    //==============================================================================================


    //ListView scroll delegate method.
    @Override
    public void onBottomHit() {

        addListViewFooter();

        showFooterProgress();

        characterListPresenter.fetchCharacters();

    }

    //ListView scroll delegate method end.

    //Search view action callback method.
    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {

        //show the keyboard when search icon is clicked.
        searchView.setIconified(false);

        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {

        //hide the keyboard.
        hideSoftKeyboard();

        return true;
    }
    //Search view action callback method end.


    private final Subscriber<String> queryChangeSubscriber = new Subscriber<String>() {

        @Override
        public void onCompleted() {


        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

            characterListPresenter.searchCharactersByName(s);

        }
    };
}
