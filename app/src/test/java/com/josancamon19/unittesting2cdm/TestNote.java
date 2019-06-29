package com.josancamon19.unittesting2cdm;

import com.josancamon19.unittesting2cdm.models.Note;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestNote {

    public static final String TIMESTAMP_1 = "05-2019";
    public static final String TIMESTAMP_2 = "04-2019";

    @Test
    public void isNotesEqual_identicalProperties_returnTrue() throws Exception {
        // Arrange
        Note note1 = new Note("Note #1", "This is a note #1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1", "This is a note #1", TIMESTAMP_1);
        note2.setId(1);
        // Act

        // Assert
        assertEquals(note1,note2);
    }

    @Test
    public void isNotesEqual_differentIds_returnFalse() throws Exception {
        // Arrange
        Note note1 = new Note("Note #1", "This is a note #1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1", "This is a note #1", TIMESTAMP_1);
        note2.setId(2);
        // Act

        // Assert
        assertNotEquals(note1,note2);
    }

    @Test
    public void isNotesEqual_differentTimestamps_returnTrue() throws Exception {
        // Arrange
        Note note1 = new Note("Note #1", "This is a note #1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1", "This is a note #1", TIMESTAMP_2);
        note2.setId(1);
        // Act

        // Assert
        assertEquals(note1,note2);
    }

    @Test
    public void isNotesEqual_differentTitles_returnFalse() throws Exception {
        // Arrange
        Note note1 = new Note("Note #1", "This is a note #1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #2", "This is a note #1", TIMESTAMP_1);
        note2.setId(1);
        // Act

        // Assert
        assertNotEquals(note1,note2);
    }

    @Test
    public void isNotesEqual_differentContents_returnFalse() throws Exception {
        // Arrange
        Note note1 = new Note("Note #1", "This is a note #1", TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1", "This is a note #2", TIMESTAMP_1);
        note2.setId(1);
        // Act

        // Assert
        assertNotEquals(note1,note2);
    }
}
