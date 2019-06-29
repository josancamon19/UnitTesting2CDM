package com.josancamon19.unittesting2cdm.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.josancamon19.unittesting2cdm.models.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase  extends RoomDatabase {

    public static final String DATABASE_NAME = "notes_db";
    public abstract NoteDao noteDao();
}
