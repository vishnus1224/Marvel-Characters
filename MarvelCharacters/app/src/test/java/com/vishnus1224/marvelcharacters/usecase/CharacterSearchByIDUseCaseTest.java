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
public class CharacterSearchByIDUseCaseTest {

    private static final int FAKE_CHARACTER_ID = 492268;

    @Mock
    private CharacterRepository characterRepository;

    private CharacterSearchByIDUseCase characterSearchByIDUseCase;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        characterSearchByIDUseCase = new CharacterSearchByIDUseCase(characterRepository);
    }

    @Test
    public void testBuildUseCase() throws Exception {

        characterSearchByIDUseCase.setCharacterID(FAKE_CHARACTER_ID);

        characterSearchByIDUseCase.buildUseCase();

        verify(characterRepository).fetchCharacterByID(FAKE_CHARACTER_ID);
        verify(characterRepository, times(1)).fetchCharacterByID(FAKE_CHARACTER_ID);

        verifyNoMoreInteractions(characterRepository);

    }
}