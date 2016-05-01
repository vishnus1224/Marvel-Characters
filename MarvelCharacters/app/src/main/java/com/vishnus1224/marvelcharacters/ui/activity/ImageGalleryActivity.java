package com.vishnus1224.marvelcharacters.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.model.Summary;
import com.vishnus1224.marvelcharacters.ui.adapter.GalleryPagerAdapter;
import com.vishnus1224.marvelcharacters.ui.transformer.ZoomOutPageTransformer;
import com.vishnus1224.marvelcharacters.util.Constants;

import java.util.List;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class ImageGalleryActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton closeButton;

    private TextView imageNameTextView;
    private TextView imageNumberTextView;
    private TextView imageCountTextView;

    private ViewPager galleryViewPager;
    private GalleryPagerAdapter galleryPagerAdapter;

    private List<Summary> summaries;

    private int currentPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        setupViews();

        obtainDataFromBundle();

        setDataToViews();

        setupViewPager();

        setupPagerAdapter();

        setListenerOnViews();
    }

    private void setupViews() {

        closeButton = (ImageButton) findViewById(R.id.galleryCloseButton);

        imageNameTextView = (TextView) findViewById(R.id.galleryImageTitle);
        imageNumberTextView = (TextView) findViewById(R.id.galleryImageNumber);
        imageCountTextView = (TextView) findViewById(R.id.galleryTotalImageCount);

        galleryViewPager = (ViewPager) findViewById(R.id.galleryViewPager);
    }


    private void obtainDataFromBundle() {

        Bundle extras = getIntent().getExtras();

        currentPosition = extras.getInt(Constants.KEY_CURRENT_POSITION);

        summaries = extras.getParcelableArrayList(Constants.KEY_SUMMARIES);
    }


    private void setDataToViews() {

        imageNumberTextView.setText(String.valueOf(currentPosition));

        imageCountTextView.setText(String.valueOf(summaries.size()));
    }


    private void setupViewPager() {

        galleryViewPager.setClipToPadding(false);

        galleryViewPager.setPageMargin(20);

        galleryViewPager.setPageTransformer(false, new ZoomOutPageTransformer());

    }

    private void setupPagerAdapter() {

        galleryPagerAdapter = new GalleryPagerAdapter(getLayoutInflater(), summaries);

        galleryViewPager.setAdapter(galleryPagerAdapter);

    }


    private void setListenerOnViews() {

        closeButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.galleryCloseButton:

                finishActivity();

                break;
        }
    }

    private void finishActivity(){

        finish();
    }
}
