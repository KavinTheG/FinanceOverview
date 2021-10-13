package com.example.financeoverview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button loginBtn;
    private TextView createNewAccount;

    private EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        loginBtn = findViewById(R.id.loginBtn);
        createNewAccount = findViewById(R.id.create_account);

        emailText = findViewById(R.id.login_email);
        passwordText = findViewById(R.id.login_password);

        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(view -> {
            String email = stripEditTexts(emailText);
            String password = stripEditTexts(passwordText);

            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    emailText.setError("Must enter email.");
                } else {
                    passwordText.setError("Must enter password");
                }
            } else {

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(MainActivity.this, GeneralOverview.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this,
                                            "No such account exists.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        createNewAccount.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, createAccount.class);
            startActivity(intent);
        });


    }
    private String stripEditTexts(EditText editText) {
        return editText.getText().toString().trim();
    }
}