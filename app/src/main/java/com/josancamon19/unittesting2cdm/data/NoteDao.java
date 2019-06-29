package com.josancamon19.unittesting2cdm.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.josancamon19.unittesting2cdm.models.Note;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();

    @Insert
    Single<Long> insertNote(Note note) throws Exception;


    @Update(onConflict = OnConflictStrategy.REPLACE)
    Single<Integer> updateNote(Note note) throws Exception;


    @Delete
    Single<Integer> deletetNote(Note note) throws Exception;


}
