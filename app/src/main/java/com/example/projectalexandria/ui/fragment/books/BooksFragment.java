package com.example.projectalexandria.ui.fragment.books;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectalexandria.R;
import com.example.projectalexandria.ui.viewmodel.ShelfViewModel;

public class BooksFragment extends Fragment {

    private ShelfViewModel shelfVM;

    public static BooksFragment newInstance() {
        return new BooksFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.books_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shelfVM = new ViewModelProvider(this).get(ShelfViewModel.class);
        // TODO: Use the ViewModel
    }

}