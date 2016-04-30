package com.vishnus1224.marvelcharacters.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.imageloader.ImageDownloader;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.model.MarvelCharacterThumbnail;
import com.vishnus1224.marvelcharacters.util.ScreenSizeConversionUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vishnu on 4/29/2016.
 */
public class CharacterListAdapter extends BaseAdapter {

    /**
     * List of marvel characters to be displayed.
     */
    private List<MarvelCharacter> marvelCharacters = new ArrayList<>();

    /**
     * Layout inflater to inflate the layout for each row.
     */
    private LayoutInflater layoutInflater;

    /**
     * ImageDownloader for downloading images from the server.
     */
    private ImageDownloader imageDownloader;

    /**
     * Used for converting image dimensions.
     */
    private ScreenSizeConversionUtil screenSizeConversionUtil;

    /**
     * Final width of the image in pixels.
     */
    private int imageWidth;

    /**
     * Final height of the image in pixels.
     */
    private int imageHeight;

    @Inject
    public CharacterListAdapter(LayoutInflater layoutInflater, ImageDownloader imageDownloader, ScreenSizeConversionUtil screenSizeConversionUtil){

        this.layoutInflater = layoutInflater;

        this.imageDownloader = imageDownloader;

        this.screenSizeConversionUtil = screenSizeConversionUtil;

        imageWidth = (int) screenSizeConversionUtil.convertDpToPixels(500f);

        imageHeight = (int) screenSizeConversionUtil.convertDpToPixels(200f);

    }

    /**
     * Update the data in the adapter with the new elements.
     * @param marvelCharacters List of MarvelCharacters.
     */
    public void updateDataSet(List<MarvelCharacter> marvelCharacters){

        //add new elements to the list.
        this.marvelCharacters.addAll(marvelCharacters);

        //notify the adapter.
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return marvelCharacters.size();
    }

    @Override
    public Object getItem(int i) {
        return marvelCharacters.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //inflate the row only if it is null.
        if(view == null){

            view = layoutInflater.inflate(R.layout.adapter_character_list, viewGroup, false);

        }

        ImageView characterImageView = (ImageView) view.findViewById(R.id.adapterCharacterImage);
        TextView characterNameTextView = (TextView) view.findViewById(R.id.adapterCharacterName);

        //Get the character associated with the row.
        MarvelCharacter marvelCharacter = (MarvelCharacter) getItem(i);

        MarvelCharacterThumbnail thumbnail = marvelCharacter.getThumbnail();

        //download the image and set it on the image view.
        imageDownloader.downloadImage(thumbnail.getFinalPath(), imageWidth, imageHeight, characterImageView);

        characterNameTextView.setText(marvelCharacter.getName());

        return view;
    }
}
