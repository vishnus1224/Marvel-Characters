package com.vishnus1224.marvelcharacters.ui.presenter;

import android.widget.ImageView;

import com.vishnus1224.marvelcharacters.di.scope.PerActivity;
import com.vishnus1224.marvelcharacters.usecase.UseCase;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Vishnu on 5/1/2016.
 */
@PerActivity
public class CharacterDetailPresenter {

    private UseCase imageResourceURIUseCase;

    @Inject
    public CharacterDetailPresenter(@Named("ImageResourceURI") UseCase imageResourceURIUseCase){

        this.imageResourceURIUseCase = imageResourceURIUseCase;

    }

    public void fetchImageData(String resourceURI, ImageView imageView){


    }
}
