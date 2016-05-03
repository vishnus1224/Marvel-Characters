package com.vishnus1224.marvelcharacters.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.delegate.ImageLoadDelegate;
import com.vishnus1224.marvelcharacters.model.Summary;

import java.util.List;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class GalleryPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;

    private List<Summary> summaries;

    private ImageLoadDelegate imageLoadDelegate;

    public GalleryPagerAdapter(LayoutInflater layoutInflater, List<Summary> summaries, ImageLoadDelegate imageLoadDelegate){

        this.layoutInflater = layoutInflater;

        this.summaries = summaries;

        this.imageLoadDelegate = imageLoadDelegate;

    }

    @Override
    public int getCount() {
        return summaries.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = layoutInflater.inflate(R.layout.adapter_gallery_pager, container, false);

        ImageView galleryImage = (ImageView) view.findViewById(R.id.adapterGalleryImage);

        Summary summary = summaries.get(position);

        galleryImage.setImageBitmap(null);

        imageLoadDelegate.loadImageData(summary.getResourceURI(), galleryImage);

        container.addView(galleryImage);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);

    }

    @Override
    public float getPageWidth(int position) {
        return 0.9f;
    }
}
