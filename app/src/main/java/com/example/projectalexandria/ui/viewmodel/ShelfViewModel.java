package com.example.projectalexandria.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.projectalexandria.data.database.ShelfEntry;
import com.example.projectalexandria.data.database.ShelfRepository;

import java.util.List;

public class ShelfViewModel extends AndroidViewModel {
    private ShelfRepository repository;
    private LiveData<List<ShelfEntry>> allEntries;

    public ShelfViewModel(Application application) {
        super(application);
        repository = new ShelfRepository(application);
        allEntries = repository.getAll();
    }

    public void insert(ShelfEntry entry) {
        repository.insert(entry);
    }

    public void update(ShelfEntry entry) {
        repository.update(entry);
    }

    public void delete(ShelfEntry entry) {
        repository.delete(entry);
    }

    public LiveData<List<ShelfEntry>> getAllEntries() {
        return allEntries;
    }


}
