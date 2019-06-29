package com.josancamon19.unittesting2cdm.di.app;

import com.josancamon19.unittesting2cdm.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesBuilderModule {

    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity();

}
