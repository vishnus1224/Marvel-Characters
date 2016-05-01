package com.vishnus1224.marvelcharacters.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.delegate.ImageLoadDelegate;
import com.vishnus1224.marvelcharacters.di.component.ActivityComponent;
import com.vishnus1224.marvelcharacters.di.component.DaggerActivityComponent;
import com.vishnus1224.marvelcharacters.di.module.ActivityModule;
import com.vishnus1224.marvelcharacters.imageloader.ImageDownloader;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.ui.adapter.CharacterComicsAdapter;
import com.vishnus1224.marvelcharacters.ui.adapter.CharacterEventsAdapter;
import com.vishnus1224.marvelcharacters.ui.adapter.CharacterSeriesAdapter;
import com.vishnus1224.marvelcharacters.ui.adapter.CharacterStoriesAdapter;
import com.vishnus1224.marvelcharacters.ui.adapter.RelatedLinksAdapter;
import com.vishnus1224.marvelcharacters.ui.presenter.CharacterDetailPresenter;
import com.vishnus1224.marvelcharacters.ui.view.CharacterDetailView;
import com.vishnus1224.marvelcharacters.util.Constants;
import com.vishnus1224.marvelcharacters.util.ScreenSizeConversionUtil;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Activity for displaying character details.
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterDetailActivity extends BaseActivity implements CharacterDetailView, View.OnClickListener, ImageLoadDelegate{

    private static final int CHARACTER_IMAGE_WIDTH = 400;
    private static final int CHARACTER_IMAGE_HEIGHT = 300;

    private static final int RESOURCE_IMAGE_WIDTH = 150;
    private static final int RESOURCE_IMAGE_HEIGHT = 150;

    //View declaration.
    //=========================================
    private ImageView characterImageView;

    private TextView characterDetailNameTextView;

    private TextView characterDescriptionTextView;

    private TextView comicsTitleTextView;
    private TextView seriesTitleTextView;
    private TextView storiesTitleTextView;
    private TextView eventsTitleTextView;

    private TextView noComicsAvailableTextView;
    private TextView noSeriesAvailableTextView;
    private TextView noStoriesAvailableTextView;
    private TextView noEventsAvailableTextView;

    private RecyclerView comicsRecyclerView;
    private RecyclerView seriesRecyclerView;
    private RecyclerView storiesRecyclerView;
    private RecyclerView eventsRecyclerView;

    private ListView relatedLinksListView;

    private ImageButton backButton;

    //=========================================
    //View declaration end.

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.Adapter comicsAdapter;
    private RecyclerView.Adapter seriesAdapter;
    private RecyclerView.Adapter storiesAdapter;
    private RecyclerView.Adapter eventsAdapter;

    private RelatedLinksAdapter relatedLinksAdapter;

    private MarvelCharacter marvelCharacter;

    private ActivityComponent activityComponent;

    @Inject
    Resources resources;

    @Inject
    ImageDownloader imageDownloader;

    @Inject
    ScreenSizeConversionUtil screenSizeConversionUtil;

    @Inject
    CharacterDetailPresenter characterDetailPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        setupViews();

        injectActivityComponent();

        initializePresenter();

        Bundle extras = getIntent().getExtras();

        getCharacterFromBundle(extras);

        if (marvelCharacter != null) {

            setDataToViews();

            setupLayoutManagerAndAdapters();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        characterDetailPresenter.destroy();
    }

    private void setupViews() {

        characterImageView = (ImageView) findViewById(R.id.characterDetailImage);

        characterDetailNameTextView = (TextView) findViewById(R.id.characterDetailName);

        characterDescriptionTextView = (TextView) findViewById(R.id.characterDescription);

        LinearLayout comicsLayout = (LinearLayout) findViewById(R.id.characterComicsLayout);
        comicsTitleTextView = (TextView) comicsLayout.findViewById(R.id.textViewLayoutTitle);
        comicsRecyclerView = (RecyclerView) comicsLayout.findViewById(R.id.recyclerViewItems);
        noComicsAvailableTextView = (TextView) comicsLayout.findViewById(R.id.noContentAvailableTextView);

        LinearLayout seriesLayout = (LinearLayout) findViewById(R.id.characterSeriesLayout);
        seriesTitleTextView = (TextView) seriesLayout.findViewById(R.id.textViewLayoutTitle);
        seriesRecyclerView = (RecyclerView) seriesLayout.findViewById(R.id.recyclerViewItems);
        noSeriesAvailableTextView = (TextView) seriesLayout.findViewById(R.id.noContentAvailableTextView);

        LinearLayout storiesLayout = (LinearLayout) findViewById(R.id.characterStoriesLayout);
        storiesTitleTextView = (TextView) storiesLayout.findViewById(R.id.textViewLayoutTitle);
        storiesRecyclerView = (RecyclerView) storiesLayout.findViewById(R.id.recyclerViewItems);
        noStoriesAvailableTextView = (TextView) storiesLayout.findViewById(R.id.noContentAvailableTextView);

        LinearLayout eventsLayout = (LinearLayout) findViewById(R.id.characterEventsLayout);
        eventsTitleTextView = (TextView) eventsLayout.findViewById(R.id.textViewLayoutTitle);
        eventsRecyclerView = (RecyclerView) eventsLayout.findViewById(R.id.recyclerViewItems);
        noEventsAvailableTextView = (TextView) eventsLayout.findViewById(R.id.noContentAvailableTextView);

        LinearLayout relatedLinksLayout = (LinearLayout) findViewById(R.id.characterRelatedLinksLayout);
        relatedLinksListView = (ListView) relatedLinksLayout.findViewById(R.id.listViewRelatedLinks);

        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

    }


    private void injectActivityComponent() {

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

        activityComponent.inject(this);
    }


    private void initializePresenter() {

        characterDetailPresenter.init(this);

    }


    private void getCharacterFromBundle(Bundle extras) {

        //check if bundle is not null and it contains the character key.
        if (extras != null && extras.containsKey(Constants.KEY_CHARACTER)) {

            marvelCharacter = extras.getParcelable(Constants.KEY_CHARACTER);

        }

    }


    private void setDataToViews() {

        characterDetailNameTextView.setText(marvelCharacter.getName());

        //if description is not available then show no description available text.
        String description = marvelCharacter.getDescription().equals("") ? resources.getString(R.string.no_description_available) : marvelCharacter.getDescription();

        characterDescriptionTextView.setText(description);

        comicsTitleTextView.setText(resources.getString(R.string.comics_title));
        seriesTitleTextView.setText(resources.getString(R.string.series_title));
        storiesTitleTextView.setText(resources.getString(R.string.stories_title));
        eventsTitleTextView.setText(resources.getString(R.string.events_title));

        noComicsAvailableTextView.setText(getResources().getString(R.string.no_comics_available));
        noSeriesAvailableTextView.setText(getResources().getString(R.string.no_series_available));
        noStoriesAvailableTextView.setText(getResources().getString(R.string.no_stories_available));
        noEventsAvailableTextView.setText(getResources().getString(R.string.no_events_available));

        imageDownloader.downloadImage(marvelCharacter.getThumbnail().getFinalPath(), (int) screenSizeConversionUtil.convertDpToPixels(CHARACTER_IMAGE_WIDTH),
                (int) screenSizeConversionUtil.convertDpToPixels(CHARACTER_IMAGE_HEIGHT), characterImageView);

    }


    private void setupLayoutManagerAndAdapters() {

        setupComicsView();

        setupSeriesView();

        setupStoriesView();

        setupEventsView();

        setupRelatedLinksView();

    }


    private void setupComicsView() {

        //if comic list is not empty then set the adapter.

        if(!marvelCharacter.getComicContainer().getItems().isEmpty()) {

            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

            comicsRecyclerView.setLayoutManager(layoutManager);

            comicsAdapter = new CharacterComicsAdapter(getLayoutInflater(), marvelCharacter.getComicContainer().getItems(), this);

            comicsRecyclerView.setAdapter(comicsAdapter);

        }else{

            hideView(comicsRecyclerView);

            showView(noComicsAvailableTextView);

        }
    }


    private void setupSeriesView() {

        if(!marvelCharacter.getSeriesContainer().getItems().isEmpty()){

            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            seriesRecyclerView.setLayoutManager(layoutManager);

            seriesAdapter = new CharacterSeriesAdapter(getLayoutInflater(), marvelCharacter.getSeriesContainer().getItems(), this);

            seriesRecyclerView.setAdapter(seriesAdapter);

        }else{

            hideView(seriesRecyclerView);

            showView(noSeriesAvailableTextView);
        }

    }

    private void setupStoriesView(){


        if(!marvelCharacter.getStoryContainer().getItems().isEmpty()){

            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            storiesRecyclerView.setLayoutManager(layoutManager);

            storiesAdapter = new CharacterStoriesAdapter(getLayoutInflater(), marvelCharacter.getStoryContainer().getItems(), this);

            storiesRecyclerView.setAdapter(storiesAdapter);

        }else{

            hideView(storiesRecyclerView);

            showView(noStoriesAvailableTextView);
        }
    }

    private void setupEventsView(){

        if(!marvelCharacter.getEventContainer().getItems().isEmpty()){

            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            eventsRecyclerView.setLayoutManager(layoutManager);

            eventsAdapter = new CharacterEventsAdapter(getLayoutInflater(), marvelCharacter.getEventContainer().getItems(), this);

            eventsRecyclerView.setAdapter(eventsAdapter);

        }else{

            hideView(eventsRecyclerView);

            showView(noEventsAvailableTextView);
        }


    }


    private void setupRelatedLinksView() {

        relatedLinksAdapter = new RelatedLinksAdapter(getLayoutInflater(), resources.getStringArray(R.array.related_links_array));

        relatedLinksListView.setAdapter(relatedLinksAdapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.stay, R.anim.slide_out_left);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.backButton:

                navigateToPreviousActivity();

                break;
        }

    }

    private void navigateToPreviousActivity() {

        onBackPressed();

    }


    //Image load delegate method.
    //**************************
    @Override
    public void loadImageData(String resourceURI, ImageView imageView) {

        characterDetailPresenter.fetchImageData(resourceURI, new WeakReference<>(imageView));

    }

    //***************************
    //Image load delegate method end.

    //View methods.
    //***********************

    @Override
    public void showView(View view) {

        view.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideView(View view) {

        view.setVisibility(View.GONE);

    }

    @Override
    public void showError(String message) {

        Toast.makeText(CharacterDetailActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loadImage(String imageURL, ImageView imageView) {

        imageDownloader.downloadImage(imageURL, (int) screenSizeConversionUtil.convertDpToPixels(RESOURCE_IMAGE_WIDTH),
                (int) screenSizeConversionUtil.convertDpToPixels(RESOURCE_IMAGE_HEIGHT), imageView);

    }

    @Override
    public void showPlaceholderImage(ImageView imageView) {

        //setting the android icon as a placeholder.
        imageView.setImageResource(R.mipmap.ic_launcher);

    }

    //**********************
    //View methods end.
}
