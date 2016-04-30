package com.vishnus1224.marvelcharacters.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.util.Constants;

/**
 * Activity for displaying character details.
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterDetailActivity extends BaseActivity {

    //View declaration.
    //=========================================
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


    private MarvelCharacter marvelCharacter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        setupViews();

        Bundle extras = getIntent().getExtras();

        getCharacterFromBundle(extras);
    }

    private void setupViews() {

        characterDescriptionTextView = (TextView) findViewById(R.id.characterDescription);

        LinearLayout comicsLayout = (LinearLayout) findViewById(R.id.characterComicsLayout);
        comicsTitleTextView = (TextView) comicsLayout.findViewById(R.id.textViewLayoutTitle);
        comicsRecyclerView = (RecyclerView) comicsLayout.findViewById(R.id.recyclerViewItems);

        LinearLayout seriesLayout = (LinearLayout) findViewById(R.id.characterSeriesLayout);
        seriesTitleTextView = (TextView) seriesLayout.findViewById(R.id.textViewLayoutTitle);
        seriesRecyclerView = (RecyclerView) seriesLayout.findViewById(R.id.recyclerViewItems);

        LinearLayout storiesLayout = (LinearLayout) findViewById(R.id.characterStoriesLayout);
        storiesTitleTextView = (TextView) storiesLayout.findViewById(R.id.textViewLayoutTitle);
        seriesRecyclerView = (RecyclerView) storiesLayout.findViewById(R.id.recyclerViewItems);

        LinearLayout eventsLayout = (LinearLayout) findViewById(R.id.characterEventsLayout);
        eventsTitleTextView = (TextView) eventsLayout.findViewById(R.id.textViewLayoutTitle);
        eventsRecyclerView = (RecyclerView) eventsLayout.findViewById(R.id.recyclerViewItems);

        LinearLayout relatedLinksLayout = (LinearLayout) findViewById(R.id.characterRelatedLinksLayout);
        relatedLinksListView = (ListView) relatedLinksLayout.findViewById(R.id.listViewRelatedLinks);

    }

    private void getCharacterFromBundle(Bundle extras) {

        //check if bundle is not null and it contains the character key.
        if(extras != null && extras.containsKey(Constants.KEY_CHARACTER)){

            marvelCharacter = extras.getParcelable(Constants.KEY_CHARACTER);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.stay, R.anim.slide_out_left);

    }
}
