package com.vishnus1224.marvelcharacters.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vishnus1224.marvelcharacters.R;

/**
 * Created by Vishnu on 4/30/2016.
 */
public class RelatedLinksAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private String[] relatedLinks;

    public RelatedLinksAdapter(LayoutInflater layoutInflater, String[] relatedLinks){

        this.layoutInflater = layoutInflater;
        this.relatedLinks = relatedLinks;
    }

    @Override
    public int getCount() {
        return relatedLinks.length;
    }

    @Override
    public Object getItem(int i) {
        return relatedLinks[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){

            view = layoutInflater.inflate(R.layout.adapter_related_links, viewGroup, false);

        }

        TextView relatedLinkNameTextView = (TextView) view.findViewById(R.id.adapterRelatedLinkName);

        relatedLinkNameTextView.setText(relatedLinks[i]);

        return view;
    }
}
