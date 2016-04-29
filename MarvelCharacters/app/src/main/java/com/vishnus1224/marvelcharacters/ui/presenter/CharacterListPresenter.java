package com.vishnus1224.marvelcharacters.ui.presenter;

import com.vishnus1224.marvelcharacters.di.scope.PerActivity;
import com.vishnus1224.marvelcharacters.ui.view.CharacterView;
import com.vishnus1224.marvelcharacters.usecase.UseCase;

import javax.inject.Inject;
import javax.inject.Named;

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
}
