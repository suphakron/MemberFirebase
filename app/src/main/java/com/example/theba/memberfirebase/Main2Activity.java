package com.example.theba.memberfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        TextView name = (TextView) findViewById(R.id.nameFirebase);


        FirebaseUser user = mAuth.getCurrentUser();
        name.setText("Complete User is : \n" + user.getEmail());
        Toast.makeText(Main2Activity.this, "Hello Welcome :  " + user.getEmail() + "\n This show for check User",Toast.LENGTH_LONG).show();

        Button gone = (Button) findViewById(R.id.go);
        gone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, Main3Activity.class));
            }
        });

        Button sing_out = (Button) findViewById(R.id.logout_but);
        sing_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
            }
        });
    }
}
