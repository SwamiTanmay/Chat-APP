package com.example.chetnatanmay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowMessages extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Users> user_list;
    FirebaseAuth fAuth;
    DatabaseReference fRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_messages);

        fAuth = FirebaseAuth.getInstance();
        fRef = FirebaseDatabase.getInstance().getReference().child("users");

        recyclerView = (RecyclerView) findViewById(R.id.userRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        user_list = new ArrayList<>();

        fRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        for (int i = 0; i < 10; i++) {
            Users curr_user_list = new Users(
                    "Heading"+(i+1),
                    "your email",
                    "random pass",
                    "12:3"+(i),
                    "300"+(i)
            );
            user_list.add(curr_user_list);
        }

        adapter = new UserAdapter(user_list,this);
        recyclerView.setAdapter(adapter);



    }
}