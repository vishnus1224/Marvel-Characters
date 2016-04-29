package com.vishnus1224.marvelcharacters.di.component;

import com.vishnus1224.marvelcharacters.MarvelCharacters;
import com.vishnus1224.marvelcharacters.api.RESTAPI;
import com.vishnus1224.marvelcharacters.di.module.ApplicationModule;
import com.vishnus1224.marvelcharacters.di.module.NetModule;
import com.vishnus1224.marvelcharacters.webservice.MarvelWebService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vishnu on 4/28/2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {

    //exposed to dependent components.
    MarvelWebService marvelWebService();
    RESTAPI restApi();

    /**
     * Inject dependencies in the MarvelCharacters class.
     * @param marvelCharacters Instance of the current application class.
     */
    void inject(MarvelCharacters marvelCharacters);
}
