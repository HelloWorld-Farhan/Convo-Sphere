package com.example.letss_talk;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    TextView Signupbutton,ResetP;
    Button button;
    EditText email,password;
    FirebaseAuth auth;
    String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    android.app.ProgressDialog progressDialog;
    ImageView Eyechanges;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        progressDialog = new ProgressDialog((this));
        progressDialog.setMessage("Please Wait.........");
        progressDialog.setCancelable(false);

        auth = FirebaseAuth.getInstance();
        Signupbutton = findViewById(R.id.Signbuton);
        button = findViewById(R.id.Logbutton);
        email = findViewById(R.id.editTextLogEmail);
        password = findViewById(R.id.editTextNumberLogPassword);
        Eyechanges = findViewById(R.id.ShowHideEye);
        ResetP = findViewById(R.id.resetButton);

        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        Eyechanges.setImageResource(R.drawable.hide_eye);
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
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, Registration.class);
                startActivity(intent);
                finish();
            }
        });

        ResetP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, ResetPassword.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if((TextUtils.isEmpty(Email))){
                    progressDialog.dismiss();
                    Toast.makeText(login.this, "Please enter the Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Password)) {
                    progressDialog.dismiss();
                    Toast.makeText(login.this, "Please enter the Email", Toast.LENGTH_SHORT).show();
                } else if (!Email.matches(emailPattern)) {
                    progressDialog.dismiss();
                    email.setError("Give Proper Email");
                } else if (Password.length()<6) {
                    progressDialog.dismiss();
                    password.setError("More thsn Six Character");
                    Toast.makeText(login.this, "Password should be longer than 6 character", Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.show();
                                try {
                                    Intent intent = new Intent(login.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(login.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                                }catch(Exception e){
                                    Toast.makeText(login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}