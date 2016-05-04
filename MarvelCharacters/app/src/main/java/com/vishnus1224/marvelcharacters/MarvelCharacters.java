package com.vishnus1224.marvelcharacters;

import android.app.Application;

import com.vishnus1224.marvelcharacters.di.component.ApplicationComponent;
import com.vishnus1224.marvelcharacters.di.component.DaggerApplicationComponent;
import com.vishnus1224.marvelcharacters.di.module.ApplicationModule;
import com.vishnus1224.marvelcharacters.di.module.NetModule;
import com.vishnus1224.marvelcharacters.webservice.MarvelWebService;

/**
 * Custom application class.
 * Created by Vishnu on 4/28/2016.
 */
public class MarvelCharacters extends Application {

    /**
     * Application component for injecting application level dependencies.
     */
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        injectApplicationComponent();
    }

    /**
     * Inject the application component for providing application level dependencies.
     */
    private void injectApplicationComponent() {

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule(MarvelWebService.BASE_URL))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
