package com.josancamon19.unittesting2cdm.ui.note;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.josancamon19.unittesting2cdm.data.NoteRepository;
import com.josancamon19.unittesting2cdm.models.Note;
import com.josancamon19.unittesting2cdm.ui.Resource;
import com.josancamon19.unittesting2cdm.util.DateUtil;

import java.util.Objects;

import javax.inject.Inject;

import static com.josancamon19.unittesting2cdm.data.NoteRepository.NOTE_TITLE_NULL;

public class NoteViewModel extends ViewModel {


    public enum ViewState {VIEW, EDIT}

    // inject
    private final NoteRepository noteRepository;

    // vars
    private MutableLiveData<Note> note  = new MutableLiveData<>();
    private MutableLiveData<ViewState> viewState = new MutableLiveData<>();
    private boolean isNewNote;


    @Inject
    public NoteViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public LiveData<Resource<Integer>> insertNote() throws Exception{
        return LiveDataReactiveStreams.fromPublisher(
                noteRepository.insertNote(note.getValue())
        );
    }

    public LiveData<Resource<Integer>> updateNote() throws Exception{
        return LiveDataReactiveStreams.fromPublisher(
                noteRepository.updateNote(note.getValue())
        );
    }

    public LiveData<Note> observeNote(){
        return note;
    }

    public LiveData<ViewState> observeViewState(){
        return viewState;
    }

    public void setViewState(ViewState viewState){
        this.viewState.setValue(viewState);
    }

    public void setIsNewNote(boolean isNewNote){
        this.isNewNote = isNewNote;
    }

    public LiveData<Resource<Integer>> saveNote(){
        return null;
    }

    public void updateNote(String title, String content) throws Exception{
        if(title == null || title.equals("")){
            throw new NullPointerException("Title can't be null");
        }
        String temp = removeWhiteSpace(content);
        if(temp.length() > 0){
            Note updatedNote = new Note(Objects.requireNonNull(note.getValue()));
            updatedNote.setTitle(title);
            updatedNote.setContent(content);
            updatedNote.setTimestamp(DateUtil.getCurrentTimeStamp());

            note.setValue(updatedNote);
        }
    }

    private String removeWhiteSpace(String string){
        string = string.replace("\n", "");
        string = string.replace(" ", "");
        return string;
    }

    public void setNote(Note note) throws Exception{
        if(note.getTitle() == null || note.getTitle().equals("")){
            throw new Exception(NOTE_TITLE_NULL);
        }
        this.note.setValue(note);
    }

    public boolean shouldNavigateBack(){
        return viewState.getValue() == ViewState.VIEW;
    }
}
