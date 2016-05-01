package com.vishnus1224.marvelcharacters.util;

import com.vishnus1224.marvelcharacters.model.Summary;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Helper class for extracting resource URIs from summaries.
 * Created by Vishnu on 5/1/2016.
 */
public class ResourceURIExtractor {

    @Inject
    public ResourceURIExtractor(){

    }

    /**
     * Extract a list of resource URIs from the list of summaries.
     * @param summaries List of summaries.
     * @return List of resource URIs.
     */
    public List<String> extractResourceURI(List<? extends Summary> summaries){

        List<String> resourceURIList = new ArrayList<>();

        for(Summary summary : summaries){

            resourceURIList.add(summary.getResourceURI());

        }

        return resourceURIList;

    }
}
