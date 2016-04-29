package com.vishnus1224.marvelcharacters.presenter;

import com.vishnus1224.marvelcharacters.di.scope.PerActivity;
import com.vishnus1224.marvelcharacters.ui.view.CharacterView;

import javax.inject.Inject;

/**
 * Created by Vishnu on 4/29/2016.
 */
@PerActivity
public class CharacterListPresenter {

    private CharacterView characterView;

    @Inject
    public CharacterListPresenter(){

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

    }
}
