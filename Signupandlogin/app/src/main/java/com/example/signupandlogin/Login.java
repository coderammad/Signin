package com.example.signupandlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email, password;
    String Email, Password;
    Button login;
    ProgressBar PB;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        PB=findViewById(R.id.pb);
        mauth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loginuser();
            }
        });
    }

    private void Loginuser() {
    PB.setVisibility(View.VISIBLE);
        Email = email.getText().toString();
        Password = password.getText().toString();
        if (TextUtils.isEmpty(Email) || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(this, "Please Check Email Address", Toast.LENGTH_SHORT).show();
            PB.setVisibility(View.GONE);
            return;

        } if (TextUtils.isEmpty(Password) || Password.length() < 7) {
            Toast.makeText(this, "Password length must be  7 or greater", Toast.LENGTH_SHORT).show();
            PB.setVisibility(View.GONE);
            return;
        }
    mauth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                Toast.makeText(Login.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
            PB.setVisibility(View.GONE);
            }
            else{
                Toast.makeText(Login.this, "Please Invalid Email and Password", Toast.LENGTH_SHORT).show();
            PB.setVisibility(View.GONE);
            }
        }
    });
    }
}