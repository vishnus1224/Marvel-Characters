package com.vishnus1224.marvelcharacters.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.imageloader.ImageDownloader;
import com.vishnus1224.marvelcharacters.model.EventSummary;
import com.vishnus1224.marvelcharacters.util.ScreenSizeConversionUtil;

import java.util.List;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterEventsAdapter extends RecyclerView.Adapter<CharacterEventsAdapter.EventsViewHolder> {

    private List<EventSummary> eventSummaryList;

    private ImageDownloader imageDownloader;

    private int imageHeight;

    private int imageWidth;

    public CharacterEventsAdapter(List<EventSummary> eventSummaryList, ImageDownloader imageDownloader, ScreenSizeConversionUtil screenSizeConversionUtil){

        this.eventSummaryList = eventSummaryList;

        this.imageDownloader = imageDownloader;

        imageWidth = (int) screenSizeConversionUtil.convertDpToPixels(200);

        imageHeight = (int) screenSizeConversionUtil.convertDpToPixels(150);
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_character_details, parent, false);

        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {

        EventSummary eventSummary = eventSummaryList.get(position);

        holder.titleTextView.setText(eventSummary.getName());
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
