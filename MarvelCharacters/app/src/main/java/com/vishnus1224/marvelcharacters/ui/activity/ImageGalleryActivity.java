package com.vishnus1224.marvelcharacters.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.delegate.ImageLoadDelegate;
import com.vishnus1224.marvelcharacters.di.component.ActivityComponent;
import com.vishnus1224.marvelcharacters.di.component.DaggerActivityComponent;
import com.vishnus1224.marvelcharacters.di.module.ActivityModule;
import com.vishnus1224.marvelcharacters.imageloader.ImageDownloader;
import com.vishnus1224.marvelcharacters.model.Summary;
import com.vishnus1224.marvelcharacters.ui.adapter.GalleryPagerAdapter;
import com.vishnus1224.marvelcharacters.ui.presenter.GalleryPresenter;
import com.vishnus1224.marvelcharacters.ui.transformer.ZoomOutPageTransformer;
import com.vishnus1224.marvelcharacters.ui.view.GalleryView;
import com.vishnus1224.marvelcharacters.util.Constants;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class ImageGalleryActivity extends BaseActivity implements View.OnClickListener, GalleryView, ImageLoadDelegate {

    private ImageButton closeButton;

    private TextView imageNameTextView;
    private TextView imageNumberTextView;
    private TextView imageCountTextView;

    private ViewPager galleryViewPager;
    private GalleryPagerAdapter galleryPagerAdapter;

    private List<Summary> summaries;

    private int currentPosition;

    @Inject
    GalleryPresenter galleryPresenter;

    @Inject
    ImageDownloader imageDownloader;

    private ActivityComponent activityComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        setupViews();

        injectActivityComponent();

        obtainDataFromBundle();

        setDataToViews();

        initializePresenter();

        setupViewPager();

        setupPagerAdapter();

        setListenerOnViews();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        galleryPresenter.destroy();

    }

    private void injectActivityComponent() {


        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

        activityComponent.inject(this);
    }

    private void initializePresenter() {

        galleryPresenter.init(this);
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

        imageNumberTextView.setText(String.valueOf(currentPosition + 1));

        imageCountTextView.setText(String.valueOf(summaries.size()));
    }


    private void setupViewPager() {

        galleryViewPager.setClipToPadding(false);

        galleryViewPager.setPageMargin(20);

        galleryViewPager.setPageTransformer(false, new ZoomOutPageTransformer());

    }

    private void setupPagerAdapter() {

        galleryPagerAdapter = new GalleryPagerAdapter(getLayoutInflater(), summaries, this);

        galleryViewPager.setAdapter(galleryPagerAdapter);
        
        galleryViewPager.setCurrentItem(currentPosition);

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

    //Gallery view methods.
    //***********************

    @Override
    public void showError(String message) {

        Toast.makeText(ImageGalleryActivity.this, message , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadImage(String imageURL, ImageView imageView) {

        imageDownloader.downloadImage(imageURL, 500, 500, imageView);

    }

    @Override
    public void showPlaceholderImage(ImageView imageView) {

        imageView.setImageResource(R.mipmap.ic_launcher);

    }

    //********************
    //Gallery view methods end.


    @Override
    public void loadImageData(String resourceURI, ImageView imageView) {

        galleryPresenter.fetchImageData(resourceURI, new WeakReference<ImageView>(imageView));

    }

}
