package com.vishnus1224.marvelcharacters.di.component;

import com.vishnus1224.marvelcharacters.di.module.ActivityModule;
import com.vishnus1224.marvelcharacters.di.scope.PerActivity;
import com.vishnus1224.marvelcharacters.ui.activity.CharacterDetailActivity;
import com.vishnus1224.marvelcharacters.ui.activity.CharacterListActivity;
import com.vishnus1224.marvelcharacters.ui.activity.ImageGalleryActivity;

import dagger.Component;

/**
 * Component for injecting activity level dependencies.
 * Created by Vishnu on 4/29/2016.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(CharacterListActivity characterListActivity);

    void inject(CharacterDetailActivity characterDetailActivity);

    void inject(ImageGalleryActivity imageGalleryActivity);
}
