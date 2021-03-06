package com.vishnus1224.marvelcharacters.di.module;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;

import com.vishnus1224.marvelcharacters.datastore.CharacterDataStore;
import com.vishnus1224.marvelcharacters.datastore.CloudCharacterDataStore;
import com.vishnus1224.marvelcharacters.datastore.CloudImageResourceDataStore;
import com.vishnus1224.marvelcharacters.datastore.ImageResourceDataStore;
import com.vishnus1224.marvelcharacters.di.scope.PerActivity;
import com.vishnus1224.marvelcharacters.repository.CharacterRepository;
import com.vishnus1224.marvelcharacters.repository.CharacterRepositoryImpl;
import com.vishnus1224.marvelcharacters.repository.ImageResourceRepository;
import com.vishnus1224.marvelcharacters.repository.ImageResourceRepositoryImpl;
import com.vishnus1224.marvelcharacters.usecase.CharacterListUseCase;
import com.vishnus1224.marvelcharacters.usecase.CharacterSearchByIDUseCase;
import com.vishnus1224.marvelcharacters.usecase.CharacterSearchUseCase;
import com.vishnus1224.marvelcharacters.usecase.ImageResourceURIUseCase;
import com.vishnus1224.marvelcharacters.usecase.UseCase;

import javax.inject.Named;

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

    /**
     * Provide the character list use case.
     * @param characterListUseCase CharacterListUseCase instance.
     * @return CharacterListUseCase instance.
     */
    @Provides @PerActivity @Named("CharacterList")
    UseCase provideCharacterListUseCase(CharacterListUseCase characterListUseCase){
        return characterListUseCase;
    }

    /**
     * Provide the character search use case for searching characters by name.
     * @param characterSearchUseCase CharacterSearchUseCase instance.
     * @return CharacterSearchUseCase instance.
     */
    @Provides @PerActivity @Named("CharacterSearch")
    UseCase provideCharacterSearchUseCase(CharacterSearchUseCase characterSearchUseCase){
        return characterSearchUseCase;
    }

    /**
     * Provide an use case for searching a character by id.
     * @param characterSearchByIDUseCase characterSearchByIDUseCase instance.
     * @return characterSearchByIDUseCase instance.
     */
    @Provides @PerActivity @Named("CharacterByID")
    UseCase provideCharacterSearchByIDUseCase(CharacterSearchByIDUseCase characterSearchByIDUseCase){
        return characterSearchByIDUseCase;
    }

    /**
     * Provide character repository.
     * @param characterRepositoryImpl Instance of CharacterRepositoryImpl.
     * @return Instance of CharacterRepositoryImpl.
     */
    @Provides @PerActivity
    CharacterRepository provideCharacterRepository(CharacterRepositoryImpl characterRepositoryImpl){
        return characterRepositoryImpl;
    }

    /**
     * Provide a cloud data store for fetching character data.
     * @param cloudCharacterDataStore CloudCharacterDataStore instance.
     * @return CloudCharacterDataStore instance.
     */
    @Provides @PerActivity @Named("Cloud")
    CharacterDataStore provideCharacterDataStore(CloudCharacterDataStore cloudCharacterDataStore){
        return cloudCharacterDataStore;
    }


    /**
     * Provide the display metrics associated with the current display.
     * @param resources The resources instance.
     * @return DisplayMetrics instance.
     */
    @Provides @PerActivity
    DisplayMetrics provideDisplayMetrics(Activity activity){
        return activity.getResources().getDisplayMetrics();
    }

    /**
     * Provide use case for fetching image data from resource URI.
     * @param imageResourceURIUseCase Instance of ImageResourceURIUseCase.
     * @return Instance of ImageResourceURIUseCase.
     */
    @Provides @PerActivity @Named("ImageResourceURI")
    UseCase provideImageResourceURIUseCase(ImageResourceURIUseCase imageResourceURIUseCase){
        return imageResourceURIUseCase;
    }

    @Provides @PerActivity
    ImageResourceRepository provideImageResourceRepository(ImageResourceRepositoryImpl imageResourceRepositoryImpl){
        return imageResourceRepositoryImpl;
    }

    /**
     * Provide image resource data store.
     * @param cloudImageResourceDataStore CloudImageResourceDataStore instance.
     * @return CloudImageResourceDataStore instance.
     */
    @Provides @PerActivity @Named("Cloud")
    ImageResourceDataStore provideImageResourceDataStore(CloudImageResourceDataStore cloudImageResourceDataStore){
        return cloudImageResourceDataStore;
    }
}
