package com.vishnus1224.marvelcharacters.ui.presenter;

import com.vishnus1224.marvelcharacters.di.scope.PerActivity;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.ui.view.CharacterView;
import com.vishnus1224.marvelcharacters.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by Vishnu on 4/29/2016.
 */
@PerActivity
public class CharacterListPresenter {

    /**
     * Reference to the character for passing UI events.
     */
    private CharacterView characterView;

    /**
     * Character list use case for requesting a list of marvel characters.
     */
    private UseCase characterListUseCase;

    @Inject
    public CharacterListPresenter(@Named("CharacterList") UseCase characterListUseCase){

        this.characterListUseCase = characterListUseCase;

    }

    /**
     * Initialize the presenter with the supplied view.
     * @param characterView Instance of characterView.
     */
    public void init(CharacterView characterView){

        this.characterView = characterView;
    }

    /**
     * Perform clean up.
     */
    public void destroy(){

        characterListUseCase.unSubscribe();

    }


    public void fetchCharacters(){

        characterListUseCase.execute(new Subscriber<List<MarvelCharacter>>(){

            @Override
            public void onCompleted() {

                characterView.hideProgressBar();

            }

            @Override
            public void onError(Throwable e) {

                characterView.hideProgressBar();

                characterView.showError(e.getMessage());

            }

            @Override
            public void onNext(List<MarvelCharacter> marvelCharacters) {

                characterView.showCharacterList(marvelCharacters);

            }
        });

    }

}
