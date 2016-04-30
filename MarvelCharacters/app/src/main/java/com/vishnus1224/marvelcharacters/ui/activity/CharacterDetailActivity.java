package com.vishnus1224.marvelcharacters.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
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
import com.vishnus1224.marvelcharacters.util.Constants;
import com.vishnus1224.marvelcharacters.util.ScreenSizeConversionUtil;

import javax.inject.Inject;

/**
 * Activity for displaying character details.
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterDetailActivity extends BaseActivity {

    //View declaration.
    //=========================================
    private ImageView characterImageView;

    private TextView characterDescriptionTextView;

    private TextView comicsTitleTextView;
    private TextView seriesTitleTextView;
    private TextView storiesTitleTextView;
    private TextView eventsTitleTextView;

    private RecyclerView comicsRecyclerView;
    private RecyclerView seriesRecyclerView;
    private RecyclerView storiesRecyclerView;
    private RecyclerView eventsRecyclerView;

    private ListView relatedLinksListView;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        setupViews();

        injectActivityComponent();

        Bundle extras = getIntent().getExtras();

        getCharacterFromBundle(extras);

        if (marvelCharacter != null) {

            setDataToViews();

            setupLayoutManager();

            setupAdapters();

        }
    }

    private void setupViews() {

        characterImageView = (ImageView) findViewById(R.id.characterDetailImage);

        characterDescriptionTextView = (TextView) findViewById(R.id.characterDescription);

        LinearLayout comicsLayout = (LinearLayout) findViewById(R.id.characterComicsLayout);
        comicsTitleTextView = (TextView) comicsLayout.findViewById(R.id.textViewLayoutTitle);
        comicsRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewItems);

        LinearLayout seriesLayout = (LinearLayout) findViewById(R.id.characterSeriesLayout);
        seriesTitleTextView = (TextView) seriesLayout.findViewById(R.id.textViewLayoutTitle);
        seriesRecyclerView = (RecyclerView) seriesLayout.findViewById(R.id.recyclerViewItems);

        LinearLayout storiesLayout = (LinearLayout) findViewById(R.id.characterStoriesLayout);
        storiesTitleTextView = (TextView) storiesLayout.findViewById(R.id.textViewLayoutTitle);
        storiesRecyclerView = (RecyclerView) storiesLayout.findViewById(R.id.recyclerViewItems);

        LinearLayout eventsLayout = (LinearLayout) findViewById(R.id.characterEventsLayout);
        eventsTitleTextView = (TextView) eventsLayout.findViewById(R.id.textViewLayoutTitle);
        eventsRecyclerView = (RecyclerView) eventsLayout.findViewById(R.id.recyclerViewItems);

        LinearLayout relatedLinksLayout = (LinearLayout) findViewById(R.id.characterRelatedLinksLayout);
        relatedLinksListView = (ListView) relatedLinksLayout.findViewById(R.id.listViewRelatedLinks);

    }


    private void injectActivityComponent() {

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

        activityComponent.inject(this);
    }


    private void getCharacterFromBundle(Bundle extras) {

        //check if bundle is not null and it contains the character key.
        if (extras != null && extras.containsKey(Constants.KEY_CHARACTER)) {

            marvelCharacter = extras.getParcelable(Constants.KEY_CHARACTER);

        }

    }


    private void setDataToViews() {

        //if description is not available then show no description available text.
        String description = marvelCharacter.getDescription().equals("") ? resources.getString(R.string.no_description_available) : marvelCharacter.getDescription();

        characterDescriptionTextView.setText(description);

        comicsTitleTextView.setText(resources.getString(R.string.comics_title));
        seriesTitleTextView.setText(resources.getString(R.string.series_title));
        storiesTitleTextView.setText(resources.getString(R.string.stories_title));
        eventsTitleTextView.setText(resources.getString(R.string.events_title));

        imageDownloader.downloadImage(marvelCharacter.getThumbnail().getFinalPath(), (int) screenSizeConversionUtil.convertDpToPixels(400),
                (int) screenSizeConversionUtil.convertDpToPixels(300), characterImageView);

    }


    private void setupLayoutManager() {

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        comicsRecyclerView.setLayoutManager(layoutManager);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        seriesRecyclerView.setLayoutManager(layoutManager);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        storiesRecyclerView.setLayoutManager(layoutManager);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        eventsRecyclerView.setLayoutManager(layoutManager);

    }


    private void setupAdapters() {

        comicsAdapter = new CharacterComicsAdapter(marvelCharacter.getComicContainer().getItems(), imageDownloader, screenSizeConversionUtil);

        comicsRecyclerView.setAdapter(comicsAdapter);

        seriesAdapter = new CharacterSeriesAdapter(marvelCharacter.getSeriesContainer().getItems(), imageDownloader, screenSizeConversionUtil);

        seriesRecyclerView.setAdapter(seriesAdapter);

        storiesAdapter = new CharacterStoriesAdapter(marvelCharacter.getStoryContainer().getItems(), imageDownloader, screenSizeConversionUtil);

        storiesRecyclerView.setAdapter(storiesAdapter);

        eventsAdapter = new CharacterEventsAdapter(marvelCharacter.getEventContainer().getItems(), imageDownloader, screenSizeConversionUtil);

        eventsRecyclerView.setAdapter(eventsAdapter);

        relatedLinksAdapter = new RelatedLinksAdapter(getLayoutInflater(), resources.getStringArray(R.array.related_links_array));

        relatedLinksListView.setAdapter(relatedLinksAdapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.stay, R.anim.slide_out_left);

    }
}
