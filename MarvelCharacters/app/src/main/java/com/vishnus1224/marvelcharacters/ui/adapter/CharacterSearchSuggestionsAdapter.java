package com.vishnus1224.marvelcharacters.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;
import com.vishnus1224.marvelcharacters.imageloader.PicassoImageDownloader;
import com.vishnus1224.marvelcharacters.util.Constants;
import com.vishnus1224.marvelcharacters.util.ScreenSizeConversionUtil;

import javax.inject.Inject;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class CharacterSearchSuggestionsAdapter extends SimpleCursorAdapter{

    @Inject
    PicassoImageDownloader picassoImageDownloader;

    @Inject
    ScreenSizeConversionUtil screenSizeConversionUtil;

    private int imageDimension;

    public CharacterSearchSuggestionsAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);

        imageDimension = (int) screenSizeConversionUtil.convertDpToPixels(50f);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView characterImageView = (ImageView) view.findViewById(R.id.adapterSearchCharacterImage);

        TextView characterNameTextView = (TextView) view.findViewById(R.id.adapterSearchCharacterName);

        String characterName = cursor.getString(cursor.getColumnIndex(Constants.SUGGESTIONS_ADAPTER_COLUMNS[1]));

        String characterIconURL = cursor.getString(cursor.getColumnIndex(Constants.SUGGESTIONS_ADAPTER_COLUMNS[2]));

        characterNameTextView.setText(characterName);

        picassoImageDownloader.downloadImage(characterIconURL, imageDimension, imageDimension, characterImageView);

    }
}
