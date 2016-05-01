package com.vishnus1224.marvelcharacters.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.delegate.ImageLoadDelegate;
import com.vishnus1224.marvelcharacters.model.ComicSummary;

import java.util.List;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterComicsAdapter extends RecyclerView.Adapter<CharacterComicsAdapter.ComicsViewHolder> {

    private List<ComicSummary> comicSummaryList;

    private LayoutInflater layoutInflater;

    private ImageLoadDelegate imageLoadDelegate;


    public CharacterComicsAdapter(LayoutInflater layoutInflater, List<ComicSummary> comicSummaryList, ImageLoadDelegate imageLoadDelegate){

        this.comicSummaryList = comicSummaryList;

        this.layoutInflater = layoutInflater;

        this.imageLoadDelegate = imageLoadDelegate;

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

        //remove the bitmap from the image view to prevent wrong images from showing up
        //due to view reuse.
        holder.iconImageView.setImageBitmap(null);

        imageLoadDelegate.loadImageData(comicSummary.getResourceURI(), holder.iconImageView);

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
