package com.example.projectalexandria.app;

import android.app.Application;

import androidx.room.Room;

import com.example.projectalexandria.data.database.ShelfDatabase;

public class DatabaseInit extends Application {
    ShelfDatabase shelfDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        /*
         * Loading of table as the Application process is initialized
         */
        shelfDatabase = Room.databaseBuilder(this, ShelfDatabase.class, ShelfDatabase.NAME).fallbackToDestructiveMigration().build();
    }

    public ShelfDatabase getShelfDatabase() {
        return shelfDatabase;
    }

}
