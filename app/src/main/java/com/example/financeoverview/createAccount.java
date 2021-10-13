package com.example.financeoverview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class createAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button signUpBtn;
    private EditText emailText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();
        signUpBtn = findViewById(R.id.signup_btn);

        emailText = findViewById(R.id.signup_email);
        passwordText = findViewById(R.id.signup_password);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = stripEditTexts(emailText);
                String password = stripEditTexts(passwordText);
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(createAccount.this, "Must fill out all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(createAccount.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        User newUser = new User(email, password);

                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {
                                                    Toast.makeText(createAccount.this,
                                                            "User has been registered!",
                                                            Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(createAccount.this,
                                                            "User has not been registered.",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(createAccount.this,
                                                "User has not been created.",
                                                Toast.LENGTH_SHORT).show();
                                        Log.w("createAccount", "firstCreateUserFail", task.getException());
                                    }
                                }
                            });
                }

            }
        });

    }

    private String stripEditTexts(EditText editText) {
        return editText.getText().toString().trim();
    }
}