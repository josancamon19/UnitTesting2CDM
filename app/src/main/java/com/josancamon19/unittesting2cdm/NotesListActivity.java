package com.josancamon19.unittesting2cdm;

import android.os.Bundle;

import com.josancamon19.unittesting2cdm.data.NoteRepository;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class NotesListActivity extends DaggerAppCompatActivity {

    @Inject
    NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        Timber.d("Note repository : %s", noteRepository.toString());
    }
}
