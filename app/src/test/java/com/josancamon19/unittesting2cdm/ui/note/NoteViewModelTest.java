package com.josancamon19.unittesting2cdm.ui.note;

import com.josancamon19.unittesting2cdm.data.NoteRepository;
import com.josancamon19.unittesting2cdm.models.Note;
import com.josancamon19.unittesting2cdm.ui.InstantExecutorExtension;
import com.josancamon19.unittesting2cdm.ui.Resource;
import com.josancamon19.unittesting2cdm.util.LiveDataTestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import io.reactivex.internal.operators.single.SingleToFlowable;

import static com.josancamon19.unittesting2cdm.data.NoteRepository.INSERT_SUCCESS;
import static com.josancamon19.unittesting2cdm.util.TestUtil.TEST_NOTE_1;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(InstantExecutorExtension.class)
public class NoteViewModelTest {

    private NoteViewModel noteViewModel;
    @Mock
    private NoteRepository noteRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        noteViewModel = new NoteViewModel(noteRepository);
    }


    @Test
    public void observeNote_whenNotSet() throws Exception {
        // Arrange
        LiveDataTestUtil<Note> liveDataTestUtil = new LiveDataTestUtil<>();
        // Act
        Note note = liveDataTestUtil.getValue(noteViewModel.observeNote());
        // Assert
        assertNull(note);
    }

    @Test
    public void observeNote_whenSet() throws Exception {
        // Arrange
        Note note = new Note(TEST_NOTE_1);
        LiveDataTestUtil<Note> liveDataTestUtil = new LiveDataTestUtil<>();
        // Act
        noteViewModel.setNote(note);
        Note observedNote = liveDataTestUtil.getValue(noteViewModel.observeNote());
        // Assert
        assertEquals(note, observedNote);
    }

    @Test
    public void insertRow_returnRow() throws Exception {
        // Arrange
        Note note = new Note(TEST_NOTE_1);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        final int insertedRow = 1;
        Flowable<Resource<Integer>> returnedData = SingleToFlowable.just(Resource.success(insertedRow, INSERT_SUCCESS));
        when(noteRepository.insertNote(any(Note.class))).thenReturn(returnedData);
        // Act
        noteViewModel.setNote(note);
        // Assert
        Resource<Integer> returnedValue = liveDataTestUtil.getValue(noteViewModel.insertNote());
        assertEquals(Resource.success(insertedRow, INSERT_SUCCESS), returnedValue);
    }

    @Test
    public void dontReturnInsertRowWithoutObserver() throws Exception {
        // Arrange
        Note note = new Note(TEST_NOTE_1);
        // Act
        noteViewModel.setNote(note);
        // Assert
        verify(noteRepository, never()).insertNote(any(Note.class));
    }

    @Test
    public void setNoteNullTitle_throwException() throws Exception {
        // Arrange
        final Note note = new Note(TEST_NOTE_1);
        note.setTitle(null);
        // Act
        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                noteViewModel.setNote(note);
            }
        });

        // Assert
    }

}
