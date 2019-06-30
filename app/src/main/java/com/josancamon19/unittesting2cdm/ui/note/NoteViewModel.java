package com.josancamon19.unittesting2cdm.ui.note;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.josancamon19.unittesting2cdm.data.NoteRepository;
import com.josancamon19.unittesting2cdm.models.Note;
import com.josancamon19.unittesting2cdm.ui.Resource;

import javax.inject.Inject;

import static com.josancamon19.unittesting2cdm.data.NoteRepository.NOTE_TITLE_NULL;

public class NoteViewModel extends ViewModel {

    private final NoteRepository repository;

    private MutableLiveData<Note> note = new MutableLiveData<>();

    @Inject
    public NoteViewModel(NoteRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<Integer>> insertNote() throws Exception {
        return LiveDataReactiveStreams.fromPublisher(repository.insertNote(note.getValue()));
    }

    public LiveData<Note> observeNote() {
        return note;
    }

    public void setNote(Note note) throws Exception {
        if (note.getTitle() == null || note.getTitle().equals("")) {
            throw new Exception(NOTE_TITLE_NULL);
        }
        this.note.setValue(note);
    }
}
