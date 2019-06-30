package com.josancamon19.unittesting2cdm.data;

import com.josancamon19.unittesting2cdm.models.Note;
import com.josancamon19.unittesting2cdm.ui.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import io.reactivex.Single;

import static com.josancamon19.unittesting2cdm.data.NoteRepository.INSERT_FAILURE;
import static com.josancamon19.unittesting2cdm.data.NoteRepository.INSERT_SUCCESS;
import static com.josancamon19.unittesting2cdm.data.NoteRepository.NOTE_TITLE_NULL;
import static com.josancamon19.unittesting2cdm.util.TestUtil.TEST_NOTE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class NoteRepositoryTest {

    private NoteRepository noteRepository;
    private NoteDao noteDao;

    @BeforeEach
    public void initEach() {
        noteDao = mock(NoteDao.class);
        noteRepository = new NoteRepository(noteDao);
    }

    @Test
    public void insertNote_returnRow() throws Exception {
        // Arrange
        final long insertedRow = 1L;
        final Single<Long> returnedData = Single.just(insertedRow);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        // Act
        final Resource<Integer> returnedValue = noteRepository.insertNote(TEST_NOTE_1).blockingFirst();
        // blocking first cause the return value is a Flowable, in others blockingSingle()

        // Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.success(1, INSERT_SUCCESS), returnedValue);

    }

    @Test
    public void insertNote_returnFailure() throws Exception {
        // Arrange
        final long insertedRow = -1L;
        final Single<Long> returnedData = Single.just(insertedRow);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        // Act
        final Resource<Integer> returnedValue = noteRepository.insertNote(TEST_NOTE_1).blockingFirst();

        // Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null, INSERT_FAILURE), returnedValue);
    }

    @Test
    public void insertNoteNullTitle_throwException() throws Exception {
        Exception exception =assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                 Note note = new Note(TEST_NOTE_1);
                 note.setTitle(null);
                 noteRepository.insertNote(note);
            }
        });

        assertEquals(NOTE_TITLE_NULL,exception.getMessage());
    }

}
