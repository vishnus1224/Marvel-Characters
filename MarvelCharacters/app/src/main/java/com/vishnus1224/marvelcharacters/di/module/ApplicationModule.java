package com.vishnus1224.marvelcharacters.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module for providing application level dependencies.
 * Created by Vishnu on 4/28/2016.
 */
@Module
public class ApplicationModule {

    /**
     * Reference to the application instance.
     */
    private Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }

    /**
     * Provides a single application instance whenever it is requested.
     * @return The current instance of the application.
     */
    @Provides @Singleton
    Application provideApplication(){
        return application;
    }
}
