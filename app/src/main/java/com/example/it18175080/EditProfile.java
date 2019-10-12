package com.example.it18175080;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it18175080.Database.DBHelper;
import com.example.it18175080.Database.UserProfile;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText username , dob , pass;
    String gender;
    RadioButton male , female;
    Button edit , delete , search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.userM);
        dob = findViewById(R.id.dobM);
        pass = findViewById(R.id.passM);
        male = findViewById(R.id.maleM);
        female = findViewById(R.id.femaleM);
        edit = findViewById(R.id.editbtnM);
        delete = findViewById(R.id.deleteM);
        search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());

                List users = dbHelper.readAllInfor(username.getText().toString());

                if(users.isEmpty()){
                    Toast.makeText(EditProfile.this, "user not found", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditProfile.this, "User found , User Name :"+ users.get(0).toString(), Toast.LENGTH_SHORT).show();
                    username.setText(users.get(0).toString());
                    dob.setText(users.get(1).toString());
                    pass.setText(users.get(2).toString());
                    if(users.get(3).toString().equals("Male")){
                        male.setChecked(true);
                    }
                    else {
                        female.setChecked(true);
                    }
                }

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());

                String user = username.getText().toString();
                String password = pass.getText().toString();
                String date = dob.getText().toString();

                if(male.isChecked()){
                    gender = "male";
                }
                else {
                    gender = "female";
                }

                Boolean status = dbHelper.updateInfor(user,date,password,gender);

                if(status){
                    Toast.makeText(EditProfile.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditProfile.this, "Updated unsuccessfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.deleteInfo(username.getText().toString());
                Toast.makeText(EditProfile.this, "Delete successfullt !", Toast.LENGTH_SHORT).show();

                username.setText(null);
                pass.setText(null);
                dob.setText(null);
                female.setText(null);
                male.setText(null);
            }
        });


    }
}
