package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)

public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance = null;

    public static NoteDataBase getInstance(Application application) {
        if(instance == null) {
            instance = Room.databaseBuilder(application,
                    NoteDataBase.class,
                    "notes.db"
            ).build();
        } return instance;
    }

    public abstract NotesDao notesDao();
}
