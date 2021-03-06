package com.vishnus1224.marvelcharacters.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.delegate.ImageLoadDelegate;
import com.vishnus1224.marvelcharacters.listener.OnImageClickListener;
import com.vishnus1224.marvelcharacters.model.SeriesSummary;
import com.vishnus1224.marvelcharacters.util.ItemType;

import java.util.List;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterSeriesAdapter extends RecyclerView.Adapter<CharacterSeriesAdapter.SeriesViewHolder>{

    private List<SeriesSummary> seriesSummaryList;

    private LayoutInflater layoutInflater;

    private ImageLoadDelegate imageLoadDelegate;

    private OnImageClickListener onImageClickListener;

    public CharacterSeriesAdapter(LayoutInflater layoutInflater, List<SeriesSummary> seriesSummaryList,
                                  ImageLoadDelegate imageLoadDelegate, OnImageClickListener onImageClickListener){

        this.seriesSummaryList = seriesSummaryList;

        this.layoutInflater = layoutInflater;

        this.imageLoadDelegate = imageLoadDelegate;

        this.onImageClickListener = onImageClickListener;
    }

    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.adapter_character_details, parent, false);

        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeriesViewHolder holder, final int position) {

        SeriesSummary seriesSummary = seriesSummaryList.get(position);

        holder.titleTextView.setText(seriesSummary.getName());

        //remove the bitmap from the image view to prevent wrong images from showing up
        //due to view reuse.
        holder.iconImageView.setImageBitmap(null);

        imageLoadDelegate.loadImageData(seriesSummary.getResourceURI(), holder.iconImageView);


        holder.iconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onImageClickListener != null){

                    onImageClickListener.onImageClick(position, ItemType.SERIES);

                }
            }
        });
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
