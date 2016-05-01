package com.vishnus1224.marvelcharacters.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.delegate.ImageLoadDelegate;
import com.vishnus1224.marvelcharacters.model.SeriesSummary;
import com.vishnus1224.marvelcharacters.util.ScreenSizeConversionUtil;

import java.util.List;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterSeriesAdapter extends RecyclerView.Adapter<CharacterSeriesAdapter.SeriesViewHolder>{

    private List<SeriesSummary> seriesSummaryList;

    private LayoutInflater layoutInflater;

    private ImageLoadDelegate imageLoadDelegate;

    private int imageHeight;

    private int imageWidth;

    public CharacterSeriesAdapter(LayoutInflater layoutInflater, List<SeriesSummary> seriesSummaryList,
                                  ScreenSizeConversionUtil screenSizeConversionUtil, ImageLoadDelegate imageLoadDelegate){

        this.seriesSummaryList = seriesSummaryList;

        this.layoutInflater = layoutInflater;

        this.imageLoadDelegate = imageLoadDelegate;

        imageWidth = (int) screenSizeConversionUtil.convertDpToPixels(200);

        imageHeight = (int) screenSizeConversionUtil.convertDpToPixels(150);
    }

    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.adapter_character_details, parent, false);

        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeriesViewHolder holder, int position) {

        SeriesSummary seriesSummary = seriesSummaryList.get(position);

        holder.titleTextView.setText(seriesSummary.getName());

        imageLoadDelegate.loadImageData(seriesSummary.getResourceURI(), holder.iconImageView);
    }

    @Override
    public int getItemCount() {
        return seriesSummaryList.size();
    }

    public static class SeriesViewHolder extends CharacterComicsAdapter.ComicsViewHolder {

        public TextView titleTextView;
        public ImageView iconImageView;

        public SeriesViewHolder(View view) {
            super(view);

            titleTextView = (TextView) view.findViewById(R.id.adapterCharacterDetailTitle);
            iconImageView = (ImageView) view.findViewById(R.id.adapterCharacterDetailImage);
        }
    }
}
