package com.example.chetnatanmay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {

    Button mRegBtn;
    TextView mAlRegBtn;
    EditText mFullName, mEmail, mPassword, mPhone;

    FirebaseAuth fauth;
    DatabaseReference fRef;
    ProgressBar mProgressBar;
    Calendar mCal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mCal = Calendar.getInstance();
        fauth = FirebaseAuth.getInstance();
        fRef  = FirebaseDatabase.getInstance().getReference().child("users");

        mRegBtn = findViewById(R.id.reg_btn);
        mAlRegBtn = findViewById(R.id.alreg_btn);
        mFullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mProgressBar = findViewById(R.id.progressbar);

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mFullName.getText().toString().trim();
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String phone=mFullName.getText().toString().trim();

                //----CHECKING THE EMPTINESS OF THE EDITTEXT-----
                if(name.equals("")){
                    Toast.makeText(Register.this, "Please Fill the name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(email.equals("")){
                    Toast.makeText(Register.this, "Please Fill the email", Toast.LENGTH_SHORT).show();
                    return ;
                }

                if(password.length()<6){
                    Toast.makeText(Register.this, "Password is too short", Toast.LENGTH_SHORT).show();
                    return;
                }

                register_user(name,email,password,phone);
            }
        });

        mAlRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }

    private void register_user(final String name, String email, String password, String phone) {

        fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    final String uid=current_user.getUid();
                    String reg_time = mCal.getTime().toString();


                    Map userMap=new HashMap();

                    userMap.put("name",name);
                    userMap.put("email",email);
                    userMap.put("password",password);
                    userMap.put("phone",phone);
                    userMap.put("registered_on",reg_time);
                    userMap.put("messages","");

                    fRef.child(uid).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task1) {
                            if(task1.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "New User is created", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Register.this,MainActivity.class);

                                //----REMOVING THE LOGIN ACTIVITY FROM THE QUEUE----
                                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();



                            }
                            else{

                                Toast.makeText(Register.this, "YOUR NAME IS NOT REGISTERED... MAKE NEW ACCOUNT-- ", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });


                }
                //---ERROR IN ACCOUNT CREATING OF NEW USER---
                else{
                    Toast.makeText(getApplicationContext(), "ERROR REGISTERING USER....", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}