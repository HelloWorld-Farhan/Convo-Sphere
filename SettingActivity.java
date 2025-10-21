//package com.example.letss_talk;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.squareup.picasso.Picasso;
//
//public class setting extends AppCompatActivity {
//    ImageView setprofile;
//    EditText setname, setstatus;
//    Button donebut;
//    FirebaseAuth auth;
//    FirebaseDatabase database;
//    FirebaseStorage storage;
//    Uri setImageUri;
//    String email,password;
//    ProgressDialog progressDialog;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_setting);
//        auth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();
//        storage = FirebaseStorage.getInstance();
//        setprofile = findViewById(R.id.settingprofile);
//        setname = findViewById(R.id.settingname);
//        setstatus = findViewById(R.id.settingstatus);
//        donebut = findViewById(R.id.donebutt);
//
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Saing...");
//        progressDialog.setCancelable(false);
//
//        DatabaseReference reference = database.getReference().child("user").child(auth.getUid());
//        StorageReference storageReference = storage.getReference().child("upload").child(auth.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                email = snapshot.child("mail").getValue().toString();
//                password = snapshot.child("password").getValue().toString();
//                String name = snapshot.child("userName").getValue().toString();
//                String profile = snapshot.child("profilepic").getValue().toString();
//                String status = snapshot.child("status").getValue().toString();
//                setname.setText(name);
//                setstatus.setText(status);
//                Picasso.get().load(profile).into(setprofile);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        setprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
//            }
//        });
//
//        donebut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                progressDialog.show();
//
//                String name = setname.getText().toString();
//                String Status = setstatus.getText().toString();
//                if (setImageUri!=null){
//                    storageReference.putFile(setImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//                                    String finalImageUri = uri.toString();
//                                    Users users = new Users(auth.getUid(), name,email,password,finalImageUri,Status);
//                                    reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()){
//                                                progressDialog.dismiss();
//                                                Toast.makeText(setting.this, "Data Is save ", Toast.LENGTH_SHORT).show();
//                                                Intent intent = new Intent(setting.this,MainActivity.class);
//                                                startActivity(intent);
//                                                finish();
//                                            }else {
//                                                progressDialog.dismiss();
//                                                Toast.makeText(setting.this, "Some thing went romg", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    });
//                                }
//                            });
//                        }
//                    });
//                }else {
//                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            String finalImageUri = uri.toString();
//                            Users users = new Users(auth.getUid(), name,email,password,finalImageUri,Status);
//                            reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        progressDialog.dismiss();
//                                        Toast.makeText(setting.this, "Data Is save ", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(setting.this,MainActivity.class);
//                                        startActivity(intent);
//                                        finish();
//                                    }else {
//                                        progressDialog.dismiss();
//                                        Toast.makeText(setting.this, "Some thing went romg", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                        }
//                    });
//                }
//
//            }
//        });
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 10) {
//            if (data != null) {
//                setImageUri = data.getData();
//                setprofile.setImageURI(setImageUri);
//            }
//        }
//
//
//    }
//}

package com.example.letss_talk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class SettingActivity extends AppCompatActivity {
    private ImageView setprofile;
    private EditText setname, setstatus;
    private Button donebut;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private Uri setImageUri;
    private ProgressDialog progressDialog;
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        setprofile = findViewById(R.id.settingprofile);
        setname = findViewById(R.id.settingname);
        setstatus = findViewById(R.id.settingstatus);
        donebut = findViewById(R.id.donebutt);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.setCancelable(false);
        DatabaseReference reference = database.getReference().child("user").child(auth.getUid());
        StorageReference storageReference = storage.getReference().child("upload").child(auth.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    email = snapshot.child("mail").getValue(String.class);
                    password = snapshot.child("password").getValue(String.class);
                    String name = snapshot.child("userName").getValue(String.class);
                    String profile = snapshot.child("profilepic").getValue(String.class);
                    String status = snapshot.child("status").getValue(String.class);

                    setname.setText(name != null ? name : "");
                    setstatus.setText(status != null ? status : "");
                    if (profile != null && !profile.isEmpty()) {
                        Picasso.get().load(profile).into(setprofile);
                    }
                } else {
                    Toast.makeText(SettingActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SettingActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });

        setprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
            }
        });

        donebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                String name = setname.getText().toString();
                String status = setstatus.getText().toString();

                if (setImageUri != null) {
                    StorageReference fileReference = storage.getReference().child("upload").child(auth.getUid());
                    fileReference.putFile(setImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String finalImageUri = uri.toString();
                                        Users users = new Users(auth.getUid(), name, email, password, finalImageUri, status);
                                        reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                progressDialog.dismiss();
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(SettingActivity.this, "Data is saved", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(SettingActivity.this, MainActivity.class));
                                                    finish();
                                                } else {
                                                    Toast.makeText(SettingActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                });
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(SettingActivity.this, "Image upload failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    String finalImageUri = ""; // No image URI
                    Users users = new Users(auth.getUid(), name, email, password, finalImageUri, status);
                    reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                Toast.makeText(SettingActivity.this, "Data is saved", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SettingActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SettingActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK && data != null) {
            setImageUri = data.getData();
            setprofile.setImageURI(setImageUri);
        }
    }
}

