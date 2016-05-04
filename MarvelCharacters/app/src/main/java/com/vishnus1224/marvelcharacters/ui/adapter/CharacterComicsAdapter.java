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
import com.vishnus1224.marvelcharacters.model.ComicSummary;
import com.vishnus1224.marvelcharacters.util.ItemType;

import java.util.List;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterComicsAdapter extends RecyclerView.Adapter<CharacterComicsAdapter.ComicsViewHolder> {

    private List<ComicSummary> comicSummaryList;

    private LayoutInflater layoutInflater;

    private ImageLoadDelegate imageLoadDelegate;

    private OnImageClickListener onImageClickListener;

    public CharacterComicsAdapter(LayoutInflater layoutInflater, List<ComicSummary> comicSummaryList, ImageLoadDelegate imageLoadDelegate
                            , OnImageClickListener onImageClickListener){

        this.comicSummaryList = comicSummaryList;

        this.layoutInflater = layoutInflater;

        this.imageLoadDelegate = imageLoadDelegate;

        this.onImageClickListener = onImageClickListener;

    }

    @Override
    public ComicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.adapter_character_details, parent, false);

        return new ComicsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComicsViewHolder holder, final int position) {

        final ComicSummary comicSummary = comicSummaryList.get(position);

        holder.titleTextView.setText(comicSummary.getName());

        //remove the bitmap from the image view to prevent wrong images from showing up
        //due to view reuse.
        holder.iconImageView.setImageBitmap(null);

        imageLoadDelegate.loadImageData(comicSummary.getResourceURI(), holder.iconImageView);

        holder.iconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onImageClickListener != null){

                    onImageClickListener.onImageClick(position, ItemType.COMICS);

                }
            }
        });

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
