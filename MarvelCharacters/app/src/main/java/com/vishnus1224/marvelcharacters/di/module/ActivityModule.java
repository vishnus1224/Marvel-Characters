package com.vishnus1224.marvelcharacters.di.module;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import com.vishnus1224.marvelcharacters.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vishnu on 4/29/2016.
 */
@Module
public class ActivityModule {

    /**
     * Reference to the current activity.
     */
    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    /**
     * Provide the current activity.
     * @return Activity instance.
     */
    @Provides @PerActivity
    Activity provideActivity(){
        return activity;
    }

    /**
     * Provide a layout inflater instance per activity.
     * @param activity The context.
     * @return Layout inflater instance.
     */
    @Provides @PerActivity
    LayoutInflater provideLayoutInflater(Activity activity){
        return (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
