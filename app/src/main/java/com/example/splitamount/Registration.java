package com.example.splitamount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    EditText username,email,password,confpassword;
    Button register;
    LinearLayout signin;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    FirebaseUser user;
    ProgressBar bar;

    public void registeruser(View view){
        bar.setVisibility(View.VISIBLE);
        String ema= email.getText().toString();
        String passwor= password.getText().toString();
        if(!passwor.equals(confpassword.getText().toString())){
            Toast.makeText(Registration.this,"Password dose't match ",Toast.LENGTH_SHORT).show();
        }else{
            fauth.createUserWithEmailAndPassword(ema,passwor).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        bar.setVisibility(View.GONE);
                        user = fauth.getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username.getText().toString()).build();

                        user.updateProfile(profileUpdates);
                        Toast.makeText(Registration.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                        DocumentReference reference = fstore.collection("Username").document(username.getText().toString());
                        Map<String , Object> mp = new HashMap<>();
                        mp.put("Email",ema);
                        mp.put("Password",passwor);
                        reference.set(mp).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Registration.this,"Data added successfully",Toast.LENGTH_SHORT).show();
                            }
                        });




                        startActivity(new Intent(Registration.this,Login.class));
                    }else{
                        Toast.makeText(Registration.this,"Registration Unsuccessful",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confpassword = findViewById(R.id.confirm_password);
        register = findViewById(R.id.registerbutton);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        signin = findViewById(R.id.signin);
        bar = findViewById(R.id.progressbar);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser(v);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,Login.class));
            }
        });

    }
}