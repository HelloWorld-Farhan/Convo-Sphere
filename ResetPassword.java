package com.example.letss_talk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText emailEditText;
        Button resetPasswordButton,afterchange;
        FirebaseAuth auth;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password);
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        afterchange = findViewById(R.id.afterchange);
        // Find Views
        emailEditText = findViewById(R.id.emailEditText);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // Set Click Listener for Reset Button
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();

                // Check if email field is empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your registered email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Send reset password email
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ResetPassword.this, "Reset password email sent!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ResetPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        afterchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetPassword.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}