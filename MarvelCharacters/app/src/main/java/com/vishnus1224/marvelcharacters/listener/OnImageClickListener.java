package com.vishnus1224.marvelcharacters.listener;

import com.vishnus1224.marvelcharacters.model.Summary;
import com.vishnus1224.marvelcharacters.util.ItemType;

/**
 * Created by Vishnu on 5/1/2016.
 */
public interface OnImageClickListener {

    void onImageClick(int position, ItemType itemType, Summary summary);
}
