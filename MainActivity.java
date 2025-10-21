package com.example.letss_talk;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private RecyclerView mainUserRecyclerView;
    private UserAdpter adapter;
    private FirebaseDatabase database;
    private ArrayList<Users> arrayList;
      ImageView imglogout;
      ImageView imageView3,cumbut,setbut;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide ActionBar if present
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
//        cumbut = findViewById(R.id.camBut);
        setbut = findViewById(R.id.settingBut);
        imglogout = findViewById(R.id.logoutimg);

        imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this,R.style.dialoge);
                dialog.setContentView(R.layout.dialog);
                Button no,yes;
                yes = dialog.findViewById(R.id.yesbnt);
                no = dialog.findViewById(R.id.nobnt);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(MainActivity.this,login.class);
                        startActivity(intent);
                        finish();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

//        imageView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, chatwindo.class);
//                startActivity(intent);
//            }
//        });

        setbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

//        cumbut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,10);
//            }
//        });

        try {
            // Initialize Firebase and ArrayList
            database = FirebaseDatabase.getInstance();
            auth = FirebaseAuth.getInstance();
            arrayList = new ArrayList<>(); // Initialize the ArrayList

            // Setup RecyclerView
            mainUserRecyclerView = findViewById(R.id.mainUserRecyclerView);
            if (mainUserRecyclerView != null) {
                mainUserRecyclerView.setLayoutManager(new LinearLayoutManager(this));

                // Initialize the adapter with the empty ArrayList
                adapter = new UserAdpter(MainActivity.this, arrayList);
                mainUserRecyclerView.setAdapter(adapter); // Set adapter to RecyclerView
            } else {
                Log.e("MainActivity", "RecyclerView is null. Check XML layout for correct ID.");
            }

            // Get reference to Firebase database node "user"
            DatabaseReference reference = database.getReference().child("user");

            // Add ValueEventListener to fetch data from Firebase
            reference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        arrayList.clear(); // Clear previous data to avoid duplication
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Users users = dataSnapshot.getValue(Users.class);
                            if (users != null) {
                                arrayList.add(users); // Add users to the list
                            } else {
                                Log.e("MainActivity", "User data is null.");
                            }
                        }
                        adapter.notifyDataSetChanged(); // Notify the adapter about data changes
                    } else {
                        Log.e("MainActivity", "No data found at the specified Firebase reference.");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle error if needed
                    Log.e("MainActivity", "Firebase Database error: " + error.getMessage());
                }
            });

        } catch (Exception e) {
            Log.e("MainActivity", "Error initializing MainActivity: " + e.getMessage());
        }
//        mainUserRecyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, chatwindo.class);
//                startActivity(intent);
//            }
//        });
    }
}
