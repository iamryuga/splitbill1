package com.example.splitamount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Registration extends AppCompatActivity {

    EditText username,email,password,confpassword;
    Button register;
    LinearLayout signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confpassword = findViewById(R.id.confirm_password);
        register = findViewById(R.id.registerbutton);
        signin = findViewById(R.id.signin);



    }
}