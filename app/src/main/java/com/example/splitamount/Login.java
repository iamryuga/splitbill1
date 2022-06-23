package com.example.splitamount;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userId;
    EditText email, password;
    Button login;
    DocumentReference dr;
    private void login(String e, String p ){

        if( e.isEmpty() ){
            Toast.makeText(Login.this,"email field is emptyy ", Toast.LENGTH_SHORT).show();
        }else if (p.isEmpty()){
            Toast.makeText(Login.this,"password field is emptyy ",Toast.LENGTH_SHORT).show();
        }
        {
            Log.i("dfsif", "fjdkslf");


            fauth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        startActivity(new Intent(Login.this, Profile.class));
                    } else {
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.username);
        password= findViewById(R.id.password);
        LinearLayout lay= findViewById(R.id.singup);

        login = findViewById(R.id.login);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registration.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                Log.i("value",e + " "+p);

                if(e.contains("@gmail.com")){
                    login(e,p);
                }else{

                    fstore.collection("cities")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            if(e.equals(document.getId())){
                                                Log.i("found","jdfklsjf");
                                            }
                                        }
                                    } else {
                                        Log.i("not found" , "fjkdslf");
                                    }
                                }
                            });
                }

            }
        });

    }
}