package com.josancamon19.unittesting2cdm.data;

import androidx.annotation.NonNull;

import javax.inject.Inject;

public class NoteRepository {

    @NonNull
    private final NoteDao noteDao;

    @Inject
    public NoteRepository(@NonNull NoteDao noteDao) {
        this.noteDao = noteDao;
    }


}
