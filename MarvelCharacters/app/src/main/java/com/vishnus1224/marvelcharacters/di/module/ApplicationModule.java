package com.vishnus1224.marvelcharacters.di.module;

import android.app.Application;

import com.squareup.picasso.Picasso;
import com.vishnus1224.marvelcharacters.cache.ImageResourceCache;
import com.vishnus1224.marvelcharacters.cache.LruMemoryCharacterResourceThumbnailCache;
import com.vishnus1224.marvelcharacters.imageloader.ImageDownloader;
import com.vishnus1224.marvelcharacters.imageloader.PicassoImageDownloader;

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

    /**
     * Provides a single picasso instance to be used throughout the application.
     * @param application Current application instance.
     * @return Instance of Picasso.
     */
    @Provides @Singleton
    Picasso providePicasso(Application application){
        return Picasso.with(application);
    }

    /**
     * Provides a single image downloader instance.
     * @param picassoImageDownloader PicassoImageDownloader instance.
     * @return PicassoImageDownloader instance.
     */
    @Provides @Singleton
    ImageDownloader imageDownloader(PicassoImageDownloader picassoImageDownloader){
        return picassoImageDownloader;
    }

    /**
     * Provide image resource cache.
     * @param lruMemoryCharacterResourceThumbnailCache lruMemoryCharacterResourceThumbnailCache instance.
     * @return lruMemoryCharacterResourceThumbnailCache instance.
     */
    @Provides @Singleton
    ImageResourceCache provideImageResourceCache(LruMemoryCharacterResourceThumbnailCache lruMemoryCharacterResourceThumbnailCache){
        return lruMemoryCharacterResourceThumbnailCache;
    }
}
