package com.vishnus1224.marvelcharacters.ui.presenter;

import android.database.Cursor;

import com.vishnus1224.marvelcharacters.di.scope.PerActivity;
import com.vishnus1224.marvelcharacters.manager.CharacterDataOffsetManager;
import com.vishnus1224.marvelcharacters.model.MarvelCharacter;
import com.vishnus1224.marvelcharacters.ui.view.CharacterView;
import com.vishnus1224.marvelcharacters.usecase.CharacterListUseCase;
import com.vishnus1224.marvelcharacters.usecase.CharacterSearchByIDUseCase;
import com.vishnus1224.marvelcharacters.usecase.CharacterSearchUseCase;
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
     * Manages the offset for fetching new data.
     */
    private CharacterDataOffsetManager characterDataOffsetManager;

    /**
     * Reference to the character for passing UI events.
     */
    private CharacterView characterView;

    /**
     * Character list use case for requesting a list of marvel characters.
     */
    private UseCase characterListUseCase;

    /**
     * Used for searching a character by name.
     */
    private UseCase characterSearchUseCase;

    /**
     * Use case for getting character details by id.
     */
    private UseCase characterSearchByIDUseCase;

    @Inject
    public CharacterListPresenter(@Named("CharacterList") UseCase characterListUseCase, @Named("CharacterSearch") UseCase characterSearchUseCase,
                    @Named("CharacterByID") UseCase characterSearchByIDUseCase, CharacterDataOffsetManager characterDataOffsetManager){

        this.characterListUseCase = characterListUseCase;

        this.characterSearchUseCase = characterSearchUseCase;

        this.characterSearchByIDUseCase = characterSearchByIDUseCase;

        this.characterDataOffsetManager = characterDataOffsetManager;

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

        //set the number of values that the result should be offset.
        ((CharacterListUseCase)characterListUseCase).setResultOffset(characterDataOffsetManager.getResultOffset());

        //get the offset from the manager and pass it to the use case.
        characterListUseCase.execute(new Subscriber<List<MarvelCharacter>>() {
            @Override
            public void onCompleted() {

                characterView.hideProgressBar();

                characterView.hideFooterProgress();

                characterView.removeListViewFooter();

            }

            @Override
            public void onError(Throwable e) {

                characterView.hideProgressBar();

                characterView.hideFooterProgress();

                characterView.removeListViewFooter();

                characterView.showError(e.getMessage());
            }

            @Override
            public void onNext(List<MarvelCharacter> marvelCharacters) {

                characterView.showCharacterList(marvelCharacters);

                //update the offset manager's data.
                characterDataOffsetManager.updateTotalAndOffset(marvelCharacters.size());
            }
        });

    }

    public void searchCharactersByName(String characterName){

        ((CharacterSearchUseCase)characterSearchUseCase).setCharacterName(characterName);

        characterSearchUseCase.execute(new Subscriber<Cursor>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Cursor cursor) {

                characterView.setSuggestionsAdapter(cursor);

            }
        });

    }

    public void searchCharacterByID(int characterID){

        ((CharacterSearchByIDUseCase) characterSearchByIDUseCase).setCharacterID(characterID);

        characterSearchByIDUseCase.execute(new Subscriber<MarvelCharacter>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                characterView.showError("Could not fetch character details");
            }

            @Override
            public void onNext(MarvelCharacter marvelCharacter) {

                characterView.showCharacterDetails(marvelCharacter);
            }
        });
    }

}
