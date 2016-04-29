package com.vishnus1224.marvelcharacters.model;

import java.util.List;

/**
 * Base container for holding a list of items.
 * Created by Vishnu on 4/29/2016.
 */
public class BaseContainer<T> {

    /**
     * Total number of results available.
     */
    private int available;

    /**
     * Total number of results returned.
     */
    private int returned;

    /**
     * The collection URI.
     */
    private int collectionURI;

    /**
     * List of items.
     */
    private List<T> items;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(int collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
