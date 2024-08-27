package com.epizy.muztahiddurjoy.alap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {
private EditText name, mail, pass;
private Button signup, haveAcc;
private FirebaseAuth mAuth;
private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.nameBoxSignUp);
        mail = (EditText) findViewById(R.id.emailBoxSignUp);
        pass = (EditText) findViewById(R.id.passwordBoxSignUp);


        signup = (Button) findViewById(R.id.signUp);
        haveAcc = (Button) findViewById(R.id.haveAcc);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameT,mailT,passT;
                nameT = name.getText().toString();
                mailT = mail.getText().toString();
                passT = pass.getText().toString().trim();
                User user = new User();
                user.setEmail(mailT);
                user.setName(nameT);
                user.setPassword(passT);
                mAuth.createUserWithEmailAndPassword(mailT,passT).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUp.this, "Account is created Successfully!", Toast.LENGTH_SHORT).show();
                            firestore.collection("User")
                                    .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startActivity(new Intent(SignUp.this,LoginActivity.class));
                                }
                            });

                        }
                        else {
                            Toast.makeText(SignUp.this, task.getException().getLocalizedMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        haveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, LoginActivity.class));
            }
        });

    }
}