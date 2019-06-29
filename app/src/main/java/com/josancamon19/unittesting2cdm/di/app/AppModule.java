package com.josancamon19.unittesting2cdm.di.app;

import android.app.Application;

import androidx.room.Room;

import com.josancamon19.unittesting2cdm.data.NoteDao;
import com.josancamon19.unittesting2cdm.data.NoteDatabase;
import com.josancamon19.unittesting2cdm.data.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.josancamon19.unittesting2cdm.data.NoteDatabase.DATABASE_NAME;

@Module
public class AppModule {

    @Singleton
    @Provides
    public NoteDatabase provideNoteDatabase(Application application) {
        return Room.databaseBuilder(
                application.getApplicationContext(),
                NoteDatabase.class,
                DATABASE_NAME)
                .build();
    }

    @Singleton
    @Provides
    public NoteDao provideNoteDao(NoteDatabase database) {
        return database.noteDao();
    }

    @Singleton
    @Provides
    public NoteRepository provideNoteRepository(NoteDao noteDao) {
        return new NoteRepository(noteDao);
    }
}
