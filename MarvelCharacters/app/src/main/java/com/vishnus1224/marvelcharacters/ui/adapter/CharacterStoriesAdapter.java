package com.vishnus1224.marvelcharacters.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.delegate.ImageLoadDelegate;
import com.vishnus1224.marvelcharacters.model.StorySummary;
import com.vishnus1224.marvelcharacters.util.ScreenSizeConversionUtil;

import java.util.List;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterStoriesAdapter extends RecyclerView.Adapter<CharacterStoriesAdapter.StoriesViewHolder>{

    private List<StorySummary> storiesSummaryList;

    private LayoutInflater layoutInflater;

    private ImageLoadDelegate imageLoadDelegate;

    private int imageHeight;

    private int imageWidth;

    public CharacterStoriesAdapter(LayoutInflater layoutInflater, List<StorySummary> storiesSummaryList,
                                   ScreenSizeConversionUtil screenSizeConversionUtil, ImageLoadDelegate imageLoadDelegate){

        this.storiesSummaryList = storiesSummaryList;

        this.layoutInflater = layoutInflater;

        this.imageLoadDelegate = imageLoadDelegate;

        imageWidth = (int) screenSizeConversionUtil.convertDpToPixels(200);

        imageHeight = (int) screenSizeConversionUtil.convertDpToPixels(150);
    }

    @Override
    public StoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.adapter_character_details, parent, false);

        return new StoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoriesViewHolder holder, int position) {

        StorySummary storySummary = storiesSummaryList.get(position);

        holder.titleTextView.setText(storySummary.getName());

        imageLoadDelegate.loadImageData(storySummary.getResourceURI(), holder.iconImageView);
    }

    @Override
    public int getItemCount() {
        return storiesSummaryList.size();
    }

    public static class StoriesViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTextView;
        public ImageView iconImageView;

        public StoriesViewHolder(View view) {
            super(view);

            titleTextView = (TextView) view.findViewById(R.id.adapterCharacterDetailTitle);
            iconImageView = (ImageView) view.findViewById(R.id.adapterCharacterDetailImage);
        }
    }
}
