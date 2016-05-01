package com.vishnus1224.marvelcharacters.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vishnus1224.marvelcharacters.R;

import java.util.List;

/**
 * Created by Vishnu on 5/1/2016.
 */
public class GalleryPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;

    private List<String> resourceURIList;

    public GalleryPagerAdapter(LayoutInflater layoutInflater, List<String> resourceURIList){

        this.layoutInflater = layoutInflater;

        this.resourceURIList = resourceURIList;

    }

    @Override
    public int getCount() {
        return resourceURIList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = layoutInflater.inflate(R.layout.adapter_gallery_pager, container, false);

        ImageView galleryImage = (ImageView) view.findViewById(R.id.adapterGalleryImage);

        galleryImage.setImageResource(R.mipmap.ic_launcher);

        container.addView(galleryImage);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);

    }
}
