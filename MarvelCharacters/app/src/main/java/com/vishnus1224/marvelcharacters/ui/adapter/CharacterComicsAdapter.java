package com.vishnus1224.marvelcharacters.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.model.ComicSummary;
import com.vishnus1224.marvelcharacters.util.ScreenSizeConversionUtil;

import java.util.List;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterComicsAdapter extends RecyclerView.Adapter<CharacterComicsAdapter.ComicsViewHolder> {

    private List<ComicSummary> comicSummaryList;

    private LayoutInflater layoutInflater;

    private int imageHeight;

    private int imageWidth;

    public CharacterComicsAdapter(LayoutInflater layoutInflater, List<ComicSummary> comicSummaryList, ScreenSizeConversionUtil screenSizeConversionUtil){

        this.comicSummaryList = comicSummaryList;

        this.layoutInflater = layoutInflater;

        imageWidth = (int) screenSizeConversionUtil.convertDpToPixels(200);

        imageHeight = (int) screenSizeConversionUtil.convertDpToPixels(150);
    }

    @Override
    public ComicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.adapter_character_details, parent, false);

        return new ComicsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComicsViewHolder holder, int position) {

        ComicSummary comicSummary = comicSummaryList.get(position);

        holder.titleTextView.setText(comicSummary.getName());


    }

    @Override
    public int getItemCount() {
        return comicSummaryList.size();
    }

    public static class ComicsViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView iconImageView;

        public ComicsViewHolder(View view) {
            super(view);

            titleTextView = (TextView) view.findViewById(R.id.adapterCharacterDetailTitle);
            iconImageView = (ImageView) view.findViewById(R.id.adapterCharacterDetailImage);
        }
    }

}
