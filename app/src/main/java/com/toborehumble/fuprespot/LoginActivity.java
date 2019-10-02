package com.toborehumble.fuprespot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Button btn_login;
    EditText password_edit;
    EditText email_edit;
    OnCompleteListener onCompleteListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_submit);
        email_edit = findViewById(R.id.email_edit);
        password_edit = findViewById(R.id.password_edit);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUserIn();
            }
        });
    }

    private void logUserIn() {
        String userEmail = email_edit.getText().toString();
        String userPassword = password_edit.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(
                onCompleteListener);

        onCompleteListener = new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Intent toMainActivity = new Intent(
                            LoginActivity.this, MainActivity.class
                    );
                    toMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(toMainActivity);
                    finish();
                }
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(int i = 0; i < 10; i++){

        }
    }
}

