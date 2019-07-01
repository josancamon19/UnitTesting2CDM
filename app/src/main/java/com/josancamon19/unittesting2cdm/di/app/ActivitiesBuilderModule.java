package com.josancamon19.unittesting2cdm.di.app;

import com.josancamon19.unittesting2cdm.di.note.NoteViewModelModule;
import com.josancamon19.unittesting2cdm.ui.note.NoteActivity;
import com.josancamon19.unittesting2cdm.ui.noteslist.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesBuilderModule {

    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity();

    @ContributesAndroidInjector(
            modules = {
                    NoteViewModelModule.class
            }
    )
    abstract NoteActivity contributeNoteActivity();

}
