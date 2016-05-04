package com.vishnus1224.marvelcharacters.usecase;

import com.vishnus1224.marvelcharacters.repository.CharacterRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Vishnu on 5/3/2016.
 */
public class CharacterListUseCaseTest {

    private static final int FAKE_OFFSET = 54;

    @Mock
    CharacterRepository characterRepository;

    private CharacterListUseCase characterListUseCase;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        characterListUseCase = new CharacterListUseCase(characterRepository);
    }

    @Test
    public void testBuildUseCase() throws Exception {

        characterListUseCase.setResultOffset(FAKE_OFFSET);

        characterListUseCase.buildUseCase();

        verify(characterRepository).fetchMarvelCharacters(FAKE_OFFSET);
        verify(characterRepository, times(1)).fetchMarvelCharacters(FAKE_OFFSET);

        verifyNoMoreInteractions(characterRepository);

    }
}