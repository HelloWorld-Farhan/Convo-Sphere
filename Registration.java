package com.example.letss_talk;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class Registration extends AppCompatActivity {

    TextView loginbutton;
    EditText username,email,phone,password,repassword;
    Button signup;
    CircleImageView profileImg;
    FirebaseAuth auth;
    Uri imageURI;
    String imageuri;
    String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    String phonePattern = "^\\+?[0-9]{1,3}?[-. ]?(\\(?\\d{1,4}\\)?)[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$";
    FirebaseDatabase database;
    FirebaseStorage storage;
    android.app.ProgressDialog progressDialog;
    ImageView Eyechanges,Eyechanges1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        progressDialog = new ProgressDialog((this));
        progressDialog.setMessage("Please Wait Account is Creating");
        progressDialog.setCancelable(false);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        loginbutton = findViewById(R.id.loginagain);
        username = findViewById(R.id.rgusername);
        phone = findViewById(R.id.rgphone);
        email = findViewById(R.id.rgemail);
        password = findViewById(R.id.rgpassword);
        repassword = findViewById(R.id.rgrepassword);
        profileImg = findViewById(R.id.profilerg0);
        signup = findViewById(R.id.buttonsign);
        Eyechanges = findViewById(R.id.ShowHideEye1);
        Eyechanges1 = findViewById(R.id.ShowHideEye2);

        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        repassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        Eyechanges.setImageResource(R.drawable.hide_eye);
        Eyechanges1.setImageResource(R.drawable.hide_eye);
        Eyechanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Eyechanges.setImageResource(R.drawable.hide_eye);
                }else{
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Eyechanges.setImageResource(R.drawable.show_eyes);
                }
            }
        });
        Eyechanges1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    repassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Eyechanges1.setImageResource(R.drawable.hide_eye);
                }else{
                    repassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Eyechanges1.setImageResource(R.drawable.show_eyes);
                }
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, login.class);
                startActivity(intent);
                finish();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namee = username.getText().toString();
                String Phone = phone.getText().toString();
                String emaill = email.getText().toString();
                String Password = password.getText().toString();
                String RPassword = repassword.getText().toString();
                String status = "Hey I'm Using this Application";

                if(TextUtils.isEmpty(namee) || TextUtils.isEmpty(Phone) || TextUtils.isEmpty(emaill)
                        || TextUtils.isEmpty(Password) || TextUtils.isEmpty(RPassword)){
                    progressDialog.dismiss();
                    Toast.makeText(Registration.this, "Please Enter the Valid Information", Toast.LENGTH_SHORT).show();
                }
                else if (!emaill.matches(emailPattern)) {
                    progressDialog.dismiss();
                    email.setError("Give Proper Email");
                }
                else if (!Phone.matches(phonePattern)) {
                    progressDialog.dismiss();
                    phone.setError("Give Valid Number");
                }
                else if (Password.length()<6) {
                    progressDialog.dismiss();
                    password.setError("More than Six Character is not allowed");
                    Toast.makeText(Registration.this, "Password should be longer than 6 character", Toast.LENGTH_SHORT).show();
                }
                else if (phone.length()<10) {
                    progressDialog.dismiss();
                    phone.setError("Enter Valid Number");
                }
                else if (!Password.equals(RPassword)) {
                    progressDialog.dismiss();
                    repassword.setError("The Password Does not Match!");
                }
                else{
                    auth.createUserWithEmailAndPassword(emaill,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              String id = task.getResult().getUser().getUid();
                              DatabaseReference databaseReference = database.getReference().child("user").child(id);
                              StorageReference storageReference = storage.getReference().child("Upload").child(id);

                              if(imageURI != null){
                                  storageReference.putFile(imageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                      @Override
                                      public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                         if(task.isSuccessful()){
                                             storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                 @Override
                                                 public void onSuccess(Uri uri) {
                                                      imageuri = uri.toString();
                                                      Users users = new Users(id,namee,Phone,emaill,Password,imageuri,status);
                                                      databaseReference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                          @Override
                                                          public void onComplete(@NonNull Task<Void> task) {
                                                              if (task.isSuccessful()){
                                                                  progressDialog.show();
                                                                  Intent intent = new Intent(Registration.this, login.class);
                                                                  startActivity(intent);
                                                                  finish();
                                                                  Toast.makeText(Registration.this, "Account Created Sucessfully", Toast.LENGTH_SHORT).show();
                                                              }else{
//                                                                  Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                  Toast.makeText(Registration.this, "Error in Creating the Account", Toast.LENGTH_SHORT).show();
                                                              }
                                                          }
                                                      });
                                                 }
                                             });
                                         }
                                      }
                                  });
                              }else {
                                  String status = "Hey I'm Using this Application";
                                  imageuri = "https://firebasestorage.googleapis.com/v0/b/lets-s-talk-ca758.appspot.com/o/man.png?alt=media&token=f2f69e7b-1711-428d-b5e6-2d4ab3ea98e4";
                                  Users users = new Users(id,namee,Phone,emaill,Password,imageuri,status);
                                  databaseReference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {

                                      @Override
                                      public void onComplete(@NonNull Task<Void> task) {
                                          if (task.isSuccessful()){
                                              progressDialog.show();
                                              Intent intent = new Intent(Registration.this, login.class);
                                              startActivity(intent);
                                              finish();
                                              Toast.makeText(Registration.this, "Account Created Sucessfully", Toast.LENGTH_SHORT).show();
                                          }else{
                                              Toast.makeText(Registration.this, "Error in Creating the Account", Toast.LENGTH_SHORT).show();
                                          }
                                      }
                                  });
                              }

                          }else {
                              Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                          }
                        }
                    });

                }
            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),10);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 ) {
            if (data != null){
                imageURI = data.getData();
                profileImg.setImageURI(imageURI);
            }
        }
    }
}