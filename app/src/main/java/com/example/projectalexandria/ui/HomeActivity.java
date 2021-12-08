package com.example.projectalexandria.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projectalexandria.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //PERMISSION REQUEST
        ActivityResultLauncher<String> requestPermissionReadExt =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (!isGranted) {
                        Toast.makeText(this, R.string.ToastMsgStoragePermissionDenied, Toast.LENGTH_LONG).show();
                    }
                });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionReadExt.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        ActivityResultLauncher<String> requestPermissionWriteExt =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (!isGranted) {
                        Toast.makeText(this, R.string.ToastMsgStoragePermissionDenied, Toast.LENGTH_LONG).show();
                    }
                });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionWriteExt.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        //END PERMISSION REQUEST

    }


}
