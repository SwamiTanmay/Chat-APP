package com.example.chetnatanmay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button mLogout, mProfile;
    TextView mUserName;

    FirebaseAuth fAuth;
    DatabaseReference fDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid=current_user.getUid();
        fDbRef  = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

        mLogout = findViewById(R.id.logout);
        mProfile = findViewById(R.id.profile);
        mUserName = findViewById(R.id.username);

        getData();

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fAuth.signOut();
                Toast.makeText(getApplicationContext(), "Logout Successful ", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), User_profile.class));

            }
        });

    }

    public void getData(){

        fDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.child("name").getValue().toString();

                mUserName.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}