package com.example.it21083396supplementaryassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.it21083396supplementaryassessment.Database.DBHandler;

import java.util.List;

public class Payment extends AppCompatActivity {
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        username = findViewById(R.id.editTextTextPersonName3);

        DBHandler dbHandler = new DBHandler(getApplicationContext());
        List user = dbHandler.readAllInfo(username.getText().toString());
        username.setText(null);

    }
}