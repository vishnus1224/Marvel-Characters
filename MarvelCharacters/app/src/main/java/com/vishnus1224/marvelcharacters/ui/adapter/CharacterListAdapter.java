package com.vishnus1224.marvelcharacters.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;

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

    @Inject
    public CharacterListAdapter(LayoutInflater layoutInflater){

        this.layoutInflater = layoutInflater;

    }

    /**
     * Update the data in the adapter with the new elements.
     * @param marvelCharacters List of MarvelCharacters.
     */
    public void updateDataSet(List<MarvelCharacter> marvelCharacters){

        //clear the list.
        this.marvelCharacters.clear();

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

        characterNameTextView.setText(marvelCharacter.getName());

        return view;
    }
}
