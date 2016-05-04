package com.vishnus1224.marvelcharacters.usecase;

import com.vishnus1224.marvelcharacters.repository.CharacterRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by Vishnu on 5/3/2016.
 */
public class CharacterSearchUseCaseTest {

    private static final String FAKE_CHARACTER_NAME = "Ron";

    @Mock
    private CharacterRepository characterRepository;

    private CharacterSearchUseCase characterSearchUseCase;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        characterSearchUseCase = new CharacterSearchUseCase(characterRepository);
    }

    @Test
    public void testBuildUseCase() throws Exception {

        characterSearchUseCase.setCharacterName(FAKE_CHARACTER_NAME);

        characterSearchUseCase.buildUseCase();

        verify(characterRepository).searchMarvelCharacters(FAKE_CHARACTER_NAME);
        verify(characterRepository, times(1)).searchMarvelCharacters(FAKE_CHARACTER_NAME);

        verifyNoMoreInteractions(characterRepository);

    }
}