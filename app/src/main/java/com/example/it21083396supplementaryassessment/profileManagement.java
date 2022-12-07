package com.example.it21083396supplementaryassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it21083396supplementaryassessment.Database.DBHandler;

public class profileManagement extends AppCompatActivity {
    EditText username, dob , password;
    Button add, updateProfile;
    RadioButton male,female;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.editTextTextPersonName2);
        dob = findViewById(R.id.editTextDate);
        password = findViewById(R.id.edittxtuserPassword);

        add = findViewById(R.id.btnadd);
        updateProfile = findViewById(R.id.btnupdateuser);

        male = findViewById(R.id.rbtnmale);
        female = findViewById(R.id.rbtnfemale);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(male.isChecked()){
                    gender = "Male";
                }
                else
                    gender = "Female";

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID = dbHandler.addInfo(username.getText().toString(), dob.getText().toString(),password.getText().toString(),gender);
                Toast.makeText(profileManagement.this, "user added successfully"+ newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(i);

                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(true);
                female.setChecked(false);
            }
        });
    }
}