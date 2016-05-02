package com.vishnus1224.marvelcharacters.imageloader;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vishnus1224.marvelcharacters.R;

import javax.inject.Inject;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class PicassoImageDownloader implements ImageDownloader {

    private Picasso picasso;

    @Inject
    public PicassoImageDownloader(Picasso picasso){

        this.picasso = picasso;

    }

    @Override
    public void downloadImage(String imageURL, ImageView imageView) {

        picasso.load(imageURL).fit().error(R.mipmap.ic_launcher).into(imageView);

    }
}
