package com.josancamon19.unittesting2cdm;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.josancamon19.unittesting2cdm.data.NoteDao;
import com.josancamon19.unittesting2cdm.data.NoteDatabase;

import org.junit.After;
import org.junit.Before;

public abstract class NoteDatabaseTest {

    private NoteDatabase noteDatabase;

    @Before
    public void init() {
        noteDatabase = Room.inMemoryDatabaseBuilder
                (ApplicationProvider.getApplicationContext(),
                        NoteDatabase.class)
                .build();
    }

    public NoteDao getNoteDao() {
        return noteDatabase.noteDao();
    }


    @After
    public void closeDb() {
        noteDatabase.close();
    }
}
