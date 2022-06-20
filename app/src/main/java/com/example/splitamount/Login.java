package com.example.splitamount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    public void gotoRegistration(View view){
        startActivity(new Intent(Login.this,Registration.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView username = findViewById(R.id.username);
        TextView me = findViewById(R.id.password);
        LinearLayout lay= findViewById(R.id.singup);
        Button but = findViewById(R.id.login);
        lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registration.class));
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("fjd",username.getText().toString());
            }
        });
    }
}