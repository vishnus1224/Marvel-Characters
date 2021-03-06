package com.vishnus1224.marvelcharacters.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.imageloader.ImageDownloader;
import com.vishnus1224.marvelcharacters.util.Constants;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterSearchSuggestionsAdapter extends SimpleCursorAdapter{

    private ImageDownloader imageDownloader;

    public CharacterSearchSuggestionsAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags, ImageDownloader imageDownloader) {
        super(context, layout, c, from, to, flags);

        this.imageDownloader = imageDownloader;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView characterImageView = (ImageView) view.findViewById(R.id.adapterSearchCharacterImage);

        TextView characterNameTextView = (TextView) view.findViewById(R.id.adapterSearchCharacterName);

        String characterName = cursor.getString(cursor.getColumnIndex(Constants.SUGGESTIONS_ADAPTER_COLUMNS[1]));

        String characterIconURL = cursor.getString(cursor.getColumnIndex(Constants.SUGGESTIONS_ADAPTER_COLUMNS[2]));

        characterNameTextView.setText(characterName);

        imageDownloader.downloadImage(characterIconURL, characterImageView);

    }
}
