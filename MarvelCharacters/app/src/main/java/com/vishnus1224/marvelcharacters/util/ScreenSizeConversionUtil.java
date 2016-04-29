package com.vishnus1224.marvelcharacters.util;

import android.util.DisplayMetrics;

import com.vishnus1224.marvelcharacters.di.scope.PerActivity;

import javax.inject.Inject;

/**
 * Utility class for converting dp to pixels and vice versa.
 * Created by Vishnu on 4/29/2016.
 */
@PerActivity
public class ScreenSizeConversionUtil {

    private DisplayMetrics displayMetrics;

    @Inject
    public ScreenSizeConversionUtil(DisplayMetrics displayMetrics){

        this.displayMetrics = displayMetrics;

    }

    /**
     * Convert dp to pixels.
     * @param dp Value in dp.
     * @return Converted pixel value.
     */
    public float convertDpToPixels(float dp){

        float px = dp * ( (float) displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        return px;
    }

    /**
     * Convert pixels to dp.
     * @param px Value in pixels.
     * @return Converted dp value.
     */
    public float convertPixelsToDp(float px){

        float dp = px / ( (float) displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        return dp;

    }


}
