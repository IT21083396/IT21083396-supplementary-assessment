package com.example.it21083396supplementaryassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it21083396supplementaryassessment.Database.DBHandler;

import java.util.List;

public class EditProfile extends AppCompatActivity {
    EditText username, dob , password;
    Button edit, delete,search;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.editusername);
        dob = findViewById(R.id.editTextDate2);
        password = findViewById(R.id.editTextTextPersonName5);

        edit = findViewById(R.id.btnuadd);
        delete = findViewById(R.id.btnuserupdate);
        search = findViewById(R.id.btnsearch);

        male = findViewById(R.id.rbtneditmale);
        female = findViewById(R.id.rbtneditfemale);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfo(username.getText().toString());

                if(user.isEmpty()){
                    Toast.makeText(EditProfile.this, "invalid user", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                }
                else{
                    Toast.makeText(EditProfile.this, "user found! user"+user.get(0).toString(), Toast.LENGTH_SHORT).show();
                    username.setText(user.get(0).toString());
                    dob.setText(user.get(1).toString());
                    password.setText(user.get(2).toString());
                    if(user.get(3).toString().equals("Male")){
                        male.setChecked(true);
                    }
                    else{
                        female.setChecked(true);
                    }
                }
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(male.isChecked()){
                    gender = "Male";
                }
                else
                    gender = "Female";
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                Boolean status = dbHandler.updateInfo(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);
                if(status){
                    Toast.makeText(EditProfile.this, "user updated successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfile.this, "update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(username.getText().toString());

                Toast.makeText(EditProfile.this, "user delete successfully", Toast.LENGTH_SHORT).show();

                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(false);
                female.setChecked(false);
            }
        });
    }
}