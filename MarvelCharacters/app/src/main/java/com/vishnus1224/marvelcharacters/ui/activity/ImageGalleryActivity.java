package com.vishnus1224.marvelcharacters.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.ui.adapter.GalleryPagerAdapter;
import com.vishnus1224.marvelcharacters.ui.transformer.ZoomOutPageTransformer;
import com.vishnus1224.marvelcharacters.util.Constants;

import java.util.List;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class ImageGalleryActivity extends BaseActivity {

    private ImageButton closeButton;

    private TextView imageNameTextView;
    private TextView imageNumberTextView;
    private TextView imageCountTextView;

    private ViewPager galleryViewPager;
    private GalleryPagerAdapter galleryPagerAdapter;

    private List<String> resourceURIList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        setupViews();

        obtainResourceURIListFromBundle();

        setupViewPager();

        setupPagerAdapter();
    }

    private void setupViews() {

        closeButton = (ImageButton) findViewById(R.id.galleryCloseButton);

        imageNameTextView = (TextView) findViewById(R.id.galleryImageTitle);
        imageNumberTextView = (TextView) findViewById(R.id.galleryImageNumber);
        imageCountTextView = (TextView) findViewById(R.id.galleryTotalImageCount);

        galleryViewPager = (ViewPager) findViewById(R.id.galleryViewPager);
    }


    private void obtainResourceURIListFromBundle() {

        resourceURIList = getIntent().getExtras().getStringArrayList(Constants.KEY_RESOURCE_URI_LIST);
    }


    private void setupViewPager() {

        galleryViewPager.setPageMargin(50);

        galleryViewPager.setPageTransformer(false, new ZoomOutPageTransformer());

    }

    private void setupPagerAdapter() {

        galleryPagerAdapter = new GalleryPagerAdapter(getLayoutInflater(), resourceURIList);

        galleryViewPager.setAdapter(galleryPagerAdapter);

    }

}
