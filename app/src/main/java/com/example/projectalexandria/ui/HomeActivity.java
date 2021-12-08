package com.example.projectalexandria.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projectalexandria.R;
import com.example.projectalexandria.data.database.ShelfEntry;
import com.example.projectalexandria.ui.fragments.Fragment1;
import com.example.projectalexandria.ui.fragments.Fragment2;
import com.example.projectalexandria.ui.fragments.Fragment3;
import com.example.projectalexandria.ui.viemodel.ShelfViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import LibraryServices.FileUtil;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ShelfViewModel shelfViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        shelfViewModel = new ViewModelProvider(this).get(ShelfViewModel.class);
        shelfViewModel.getAllEntries().observe(this, new Observer<List<ShelfEntry>>() {
            @Override
            public void onChanged(List<ShelfEntry> shelfEntries) {
                // update recycleview
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Fragment1()).commit();

        bottomNavigationView =findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.item1:
                        fragment= new Fragment1();
                        break;
                    case R.id.item2:
                        fragment= new Fragment2();
                        break;
                    case R.id.item3:
                        fragment= new Fragment3();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                return true;
            }

        });
        //PERMISSION REQUEST
            ActivityResultLauncher<String> requestPermissionReadExt =
                    registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                        if (!isGranted) {
                            System.out.println("Ma che stai a fa'");
                            Toast.makeText(this, R.string.ToastMsgStoragePermissionDenied, Toast.LENGTH_LONG).show();
                        }
                    });
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionReadExt.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        ActivityResultLauncher<String> requestPermissionWriteExt =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (!isGranted) {
                        System.out.println("Ma che stai a fa'");
                        Toast.makeText(this, R.string.ToastMsgStoragePermissionDenied, Toast.LENGTH_LONG).show();
                    }
                });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionWriteExt.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        //END PERMISSION REQUEST
    }

    public void onActivityResult(int requestcode, int resulCode, Intent data) {
        super.onActivityResult(requestcode,resulCode,data);
        if (requestcode == requestcode && resulCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            } Uri uri = data.getData();
            System.out.println(uri.toString());
            try {
                File inputFile = FileUtil.from(HomeActivity.this, uri);
                Log.d("file", "File...:::: uti - "+inputFile .getPath()+" file -" + inputFile + " : " + inputFile .exists());
                FileUtil.writeToSDFile( HomeActivity.this, inputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
    }
    }
