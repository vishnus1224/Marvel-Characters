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
    public void downloadImage(String imageURL, int finalWidth, int finalHeight, ImageView imageView) {

        if(finalWidth <= 0 || finalHeight <= 0){
            throw new IllegalStateException("Width or height should be greater than 0");
        }

        picasso.load(imageURL).resize(finalWidth, finalHeight).error(R.mipmap.ic_launcher).into(imageView);

    }
}
