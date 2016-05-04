package com.vishnus1224.marvelcharacters.ui.presenter;

import android.widget.ImageView;

import com.vishnus1224.marvelcharacters.model.CharacterResourceThumbnail;
import com.vishnus1224.marvelcharacters.ui.view.GalleryView;
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
public class GalleryPresenter {

    private UseCase imageResourceURIUseCase;

    private URLExtractor urlExtractor;

    private GalleryView galleryView;

    public void init(GalleryView galleryView){

        this.galleryView = galleryView;

    }

    /**
     * Perform clean up.
     */
    public void destroy(){

        imageResourceURIUseCase.unSubscribe();

    }

    @Inject
    public GalleryPresenter(@Named("ImageResourceURI") UseCase imageResourceURIUseCase, URLExtractor urlExtractor){

        this.imageResourceURIUseCase = imageResourceURIUseCase;

        this.urlExtractor = urlExtractor;
    }

    public void fetchImageData(final String resourceURI, final WeakReference<ImageView> imageViewWeakReference){

        ((ImageResourceURIUseCase)imageResourceURIUseCase).setResourceURI(urlExtractor.removeStringFromURL(resourceURI, MarvelWebService.BASE_URL));

        imageResourceURIUseCase.execute(new Subscriber<CharacterResourceThumbnail>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                if(imageViewWeakReference.get() != null){

                    galleryView.showPlaceholderImage(imageViewWeakReference.get());

                }

            }

            @Override
            public void onNext(CharacterResourceThumbnail characterResourceThumbnail) {

                //if the image view stored in the weak reference still exists.
                if(imageViewWeakReference.get() != null){

                    galleryView.loadImage(characterResourceThumbnail.getFinalPath(), imageViewWeakReference.get());

                }

            }
        });

    }
}
