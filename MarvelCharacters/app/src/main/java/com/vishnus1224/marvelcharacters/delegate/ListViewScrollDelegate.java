package com.vishnus1224.marvelcharacters.delegate;

import android.widget.AbsListView;

/**
 * Delegate to detect if the list view has scrolled to its bottom.
 * Created by Vishnu on 4/29/2016.
 */
public class ListViewScrollDelegate implements AbsListView.OnScrollListener {

    private BottomHitListener bottomHitListener;

    /**
     * The position of the visible item. Used to prevent multiple calls to onBottomHit.
     */
    private int lastItemPosition;

    /**
     * Total items displayed by the ListView's adapter.
     */
    private int totalItemCount;

    /**
     * The offset from the last item at which the onBottomHit method will fire.
     * The onBottomHit method will be called when the list view has scrolled past the third last item.
     */
    private int lastItemOffset = 2;

    public ListViewScrollDelegate(BottomHitListener bottomHitListener){

        this.bottomHitListener = bottomHitListener;

    }

    /**
     * Reset the last item position. Will be used if an error occurs while fetching data.
     * If it is not reset, then the delegate will no signal that scroll has reached its bottom.
     */
    public void resetLastItemPosition(){

        lastItemPosition = 0;

    }

    /**
     * Add count to the totalItemCount.
     * @param count Count value.
     */
    public void addToTotalItemCount(int count){

        totalItemCount += count;

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        int currentPosition = firstVisibleItem + visibleItemCount;

        //calculate the offset position from the specified offset.
        //using member variable instead of local totalItemCount as the local increases
        //by 1 when footer gets added.
        int offsetPosition = this.totalItemCount - lastItemOffset;

        if(currentPosition == offsetPosition && totalItemCount > 0){

            if(lastItemPosition != currentPosition) {

                lastItemPosition = currentPosition;

                if (bottomHitListener != null) {

                    bottomHitListener.onBottomHit();

                }

            }

        }
    }

    /**
     * Notifies the calling the class that the list view has reached its bottom.
     */
    public interface BottomHitListener {

        /**
         * ListView has hit its bottom.
         */
        void onBottomHit();

    }
}
