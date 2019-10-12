package com.example.it18175080;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it18175080.Database.DBHelper;

public class ProfileManagement extends AppCompatActivity {

    EditText username , dob , pass;
    String gender;
    RadioButton male , female;
    Button update ,add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.usernameP);
        dob = findViewById(R.id.dobP);
        pass = findViewById(R.id.passP);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        update = findViewById(R.id.updateP);
        add = findViewById(R.id.addbtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());

                String user = username.getText().toString();

                String password = pass.getText().toString();
                String dateofbirth = dob.getText().toString();

                if(male.isChecked()){
                    gender = "male";
                }
                else{
                    gender = "female";
                }

                long newRowId = dbHelper.AddInfo(user,dateofbirth,password,gender);
                if(newRowId > 0){
                    Toast.makeText(ProfileManagement.this, "User added", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),EditProfile.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(ProfileManagement.this, "user Not added", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
