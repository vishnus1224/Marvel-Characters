package com.vishnus1224.marvelcharacters.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.imageloader.ImageDownloader;
import com.vishnus1224.marvelcharacters.model.ComicSummary;
import com.vishnus1224.marvelcharacters.util.ScreenSizeConversionUtil;

import java.util.List;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterComicsAdapter extends RecyclerView.Adapter<CharacterComicsAdapter.ViewHolder> {

    private List<ComicSummary> comicSummaryList;

    private ImageDownloader imageDownloader;

    private int imageHeight;

    private int imageWidth;

    public CharacterComicsAdapter(List<ComicSummary> comicSummaryList, ImageDownloader imageDownloader, ScreenSizeConversionUtil screenSizeConversionUtil){

        this.comicSummaryList = comicSummaryList;

        this.imageDownloader = imageDownloader;

        imageWidth = (int) screenSizeConversionUtil.convertDpToPixels(200);

        imageHeight = (int) screenSizeConversionUtil.convertDpToPixels(150);
    }

    @Override
    public CharacterComicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_character_details, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterComicsAdapter.ViewHolder holder, int position) {

        ComicSummary comicSummary = comicSummaryList.get(position);

        holder.titleTextView.setText(comicSummary.getName());


    }

    @Override
    public int getItemCount() {
        return comicSummaryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView iconImageView;

        public ViewHolder(View view) {
            super(view);

            titleTextView = (TextView) view.findViewById(R.id.adapterCharacterDetailTitle);
            iconImageView = (ImageView) view.findViewById(R.id.adapterCharacterDetailImage);
        }
    }

}
