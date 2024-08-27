package com.epizy.muztahiddurjoy.alap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
EditText email, password;
Button signIn,signUp;
FirebaseAuth auth;
ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Logging in..");
        auth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.emailBox);
        password = (EditText) findViewById(R.id.passwordBox);
        signIn = (Button) findViewById(R.id.login);
        signUp = (Button) findViewById(R.id.signup);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String mail,pass;
                mail = email.getText().toString();
                pass = password.getText().toString().trim();
                auth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            dialog.dismiss();
                            startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                            Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUp.class));
            }
        });
    }
}