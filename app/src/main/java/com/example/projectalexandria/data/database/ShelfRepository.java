package com.example.projectalexandria.data.database;

// Async non è necessario
// LiveData è intrinsecamente
// multithreaddato. Ma sarà
// deprecato entro la prossima
// estate.
// Quindi per ora possiamo interfacciarci
// direttamente con ShelfDAO.



import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

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
        new InsertEntry(shelfDAO).execute(entry);
    }

    public void update(ShelfEntry entry) {
        new UpdateEntry(shelfDAO).execute(entry);
    }

    public void delete(ShelfEntry entry) {
        new DeleteEntry(shelfDAO).execute(entry);
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



    private static class InsertEntry extends AsyncTask<ShelfEntry, Void, Void>{
        private ShelfDAO shelfDAO;

        private InsertEntry(ShelfDAO shelfDAO) {
            this.shelfDAO = shelfDAO;
        }

        @Override
        protected Void doInBackground(ShelfEntry ... entries) {
            shelfDAO.insertEntry(entries[0]);
            return null;
        }
    }

    private static class UpdateEntry extends AsyncTask<ShelfEntry, Void, Void>{
        private ShelfDAO shelfDAO;

        private UpdateEntry(ShelfDAO shelfDAO) {
            this.shelfDAO = shelfDAO;
        }

        @Override
        protected Void doInBackground(ShelfEntry ... entries) {
            shelfDAO.updateEntry(entries[0]);
            return null;
        }
    }

    private static class DeleteEntry extends AsyncTask<ShelfEntry, Void, Void>{
        private ShelfDAO shelfDAO;

        private DeleteEntry(ShelfDAO shelfDAO) {
            this.shelfDAO = shelfDAO;
        }

        @Override
        protected Void doInBackground(ShelfEntry ... entries) {
            shelfDAO.deleteEntry(entries[0]);
            return null;
        }
    }

}
