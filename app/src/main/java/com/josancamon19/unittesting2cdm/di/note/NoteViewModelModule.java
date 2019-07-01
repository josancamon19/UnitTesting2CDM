package com.josancamon19.unittesting2cdm.di.note;

import androidx.lifecycle.ViewModel;

import com.josancamon19.unittesting2cdm.di.viewmodel.ViewModelKey;
import com.josancamon19.unittesting2cdm.ui.note.NoteViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class NoteViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel.class)
    public abstract ViewModel bindAuthViewModel(NoteViewModel viewModel);
}
