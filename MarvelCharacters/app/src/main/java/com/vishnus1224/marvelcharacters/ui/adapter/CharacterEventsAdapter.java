package com.vishnus1224.marvelcharacters.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.delegate.ImageLoadDelegate;
import com.vishnus1224.marvelcharacters.model.EventSummary;

import java.util.List;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterEventsAdapter extends RecyclerView.Adapter<CharacterEventsAdapter.EventsViewHolder> {

    private List<EventSummary> eventSummaryList;

    private LayoutInflater layoutInflater;

    private ImageLoadDelegate imageLoadDelegate;


    public CharacterEventsAdapter(LayoutInflater layoutInflater, List<EventSummary> eventSummaryList, ImageLoadDelegate imageLoadDelegate){

        this.eventSummaryList = eventSummaryList;

        this.layoutInflater = layoutInflater;

        this.imageLoadDelegate = imageLoadDelegate;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.adapter_character_details, parent, false);

        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {

        EventSummary eventSummary = eventSummaryList.get(position);

        holder.titleTextView.setText(eventSummary.getName());

        //remove the bitmap from the image view to prevent wrong images from showing up
        //due to view reuse.
        holder.iconImageView.setImageBitmap(null);

        imageLoadDelegate.loadImageData(eventSummary.getResourceURI(), holder.iconImageView);
    }

    @Override
    public int getItemCount() {
        return eventSummaryList.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTextView;
        public ImageView iconImageView;

        public EventsViewHolder(View view) {
            super(view);

            titleTextView = (TextView) view.findViewById(R.id.adapterCharacterDetailTitle);
            iconImageView = (ImageView) view.findViewById(R.id.adapterCharacterDetailImage);
        }
    }

}
