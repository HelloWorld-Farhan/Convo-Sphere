package com.example.letss_talk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class splash extends AppCompatActivity {
    TextView logo;
    TextView name,one,two ,three ,five;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    Animation topAnim, bottomAnim;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.textView3);
        name = findViewById(R.id.NameOriginal);
        one  = findViewById(R.id.name1);
        two  = findViewById(R.id.name2);
        three = findViewById(R.id.name3);
        five = findViewById(R.id.name5);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo.setAnimation(topAnim);
        name.setAnimation(bottomAnim);
        one.setAnimation(bottomAnim);
        two.setAnimation(bottomAnim);
        three.setAnimation(bottomAnim);
        five.setAnimation(bottomAnim);

        // Hide action bar if present
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance();

        // Initialize AuthStateListener
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    // User is signed in, redirect to MainActivity
                    Intent intent = new Intent(splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // User is signed out, redirect to login activity
                    Intent intent = new Intent(splash.this, login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

//         Delay before adding the AuthStateListener
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Add the AuthStateListener to start listening for auth state changes
                auth.addAuthStateListener(authStateListener);
            }
        }, 4000);  // 4 seconds delay
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Remove the AuthStateListener to prevent memory leaks
        if (auth != null && authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }
}
