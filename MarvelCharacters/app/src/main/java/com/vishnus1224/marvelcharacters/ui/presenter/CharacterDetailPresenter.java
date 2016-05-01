package com.vishnus1224.marvelcharacters.ui.presenter;

import android.widget.ImageView;

import com.vishnus1224.marvelcharacters.di.scope.PerActivity;
import com.vishnus1224.marvelcharacters.model.CharacterResourceThumbnail;
import com.vishnus1224.marvelcharacters.ui.view.CharacterDetailView;
import com.vishnus1224.marvelcharacters.usecase.ImageResourceURIUseCase;
import com.vishnus1224.marvelcharacters.usecase.UseCase;
import com.vishnus1224.marvelcharacters.util.URLExtractor;
import com.vishnus1224.marvelcharacters.webservice.MarvelWebService;

import java.lang.ref.WeakReference;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by Vishnu on 5/1/2016.
 */
@PerActivity
public class CharacterDetailPresenter {

    private UseCase imageResourceURIUseCase;

    private URLExtractor urlExtractor;

    private CharacterDetailView characterDetailView;

    /**
     * Initialize the presenter.
     * @param characterDetailView View with which the presenter will communicate.
     */
    public void init(CharacterDetailView characterDetailView){
        this.characterDetailView = characterDetailView;
    }

    /**
     * Perform clean up.
     */
    public void destroy(){

        imageResourceURIUseCase.unSubscribe();

    }

    @Inject
    public CharacterDetailPresenter(@Named("ImageResourceURI") UseCase imageResourceURIUseCase, URLExtractor urlExtractor){

        this.imageResourceURIUseCase = imageResourceURIUseCase;

        this.urlExtractor = urlExtractor;

    }

    public void fetchImageData(String resourceURI, final WeakReference<ImageView> imageViewWeakReference){

        ((ImageResourceURIUseCase)imageResourceURIUseCase).setResourceURI(urlExtractor.removeStringFromURL(resourceURI, MarvelWebService.BASE_URL));

        imageResourceURIUseCase.execute(new Subscriber<CharacterResourceThumbnail>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onNext(CharacterResourceThumbnail characterResourceThumbnail) {

                //if the image view stored in the weak reference still exists.
                if(imageViewWeakReference.get() != null){

                    characterDetailView.loadImage(characterResourceThumbnail.getFinalPath(), imageViewWeakReference.get());

                }

            }
        });

    }
}
