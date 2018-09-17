package com.example.theba.memberfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText idField;
    private EditText pwdField;

    private Button logbut;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        idField = (EditText) findViewById(R.id.iduser);
        pwdField = (EditText) findViewById(R.id.pwduser);

        logbut = (Button) findViewById(R.id.Log_button);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){

                    startActivity(new Intent(MainActivity.this, Main2Activity.class));

                }
            }
        };

        logbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthLogin();
            }
        });

    }

    private void AuthLogin() {
        String Email = idField.getText().toString();
        String Pwd = pwdField.getText().toString();

        if(TextUtils.isEmpty(Email) || TextUtils.isEmpty(Pwd) ) {

            Toast.makeText(MainActivity.this, "Field is Empty.", Toast.LENGTH_LONG).show();

        }else{
            mAuth.signInWithEmailAndPassword(Email, Pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toast.makeText(MainActivity.this, "Sing-in Problem", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }
}
