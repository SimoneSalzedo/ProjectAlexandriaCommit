package com.example.projectalexandria.data.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ShelfRepository {
    private ShelfDAO shelfDAO;
    private LiveData<List<ShelfEntry>> allEntries;


    public ShelfRepository(Application application) {
        ShelfDatabase shelfDatabase = ShelfDatabase.getInstance(application);

        shelfDAO = shelfDatabase.shelfDao();
        allEntries = shelfDAO.getAll();
    }


    public void insert(ShelfEntry entry) {

    }

    public void update(ShelfEntry entry) {

    }

    public void delete(ShelfEntry entry) {

    }

    public LiveData<List<ShelfEntry>> getAll() {
        return allEntries;
    }

    public LiveData<List<ShelfEntry>> getByDataType() {
        return allEntries;
    }

    public LiveData<List<ShelfEntry>> getByTitle() {
        return allEntries;
    }

    public LiveData<List<ShelfEntry>> getByAuthor() {
        return allEntries;
    }

    public LiveData<List<ShelfEntry>> getByGenre() {
        return allEntries;
    }

    public LiveData<List<ShelfEntry>> searchByTitle() {
        return allEntries;
    }

    public LiveData<List<ShelfEntry>> searchByAuthor() {
        return allEntries;
    }

    public LiveData<List<ShelfEntry>> searchByGenre() {
        return allEntries;
    }



}
