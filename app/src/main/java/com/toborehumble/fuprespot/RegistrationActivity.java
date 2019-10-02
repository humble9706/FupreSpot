package com.toborehumble.fuprespot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.toborehumble.fuprespot.pojos.ProfileObject;

public class RegistrationActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Button register_btn;
    EditText email_edit;
    EditText username_edit;
    EditText password_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email_edit = findViewById(R.id.email_edit_register);
        register_btn = findViewById(R.id.btn_submit_register);
        username_edit = findViewById(R.id.username_edit_register);
        password_edit = findViewById(R.id.password_edit_register);

        firebaseAuth = FirebaseAuth.getInstance();

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });
    }

    private void signInUser() {
        final String username = username_edit.getText().toString();
        String userEmail = email_edit.getText().toString();
        String userPassword = password_edit.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUser = firebaseAuth.getCurrentUser();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

                    ProfileObject profileObject = new ProfileObject();
                    profileObject.setUserName(username);
                    profileObject.setDepartment("department");
                    profileObject.setHobby("reading");
                    profileObject.setBirthDay("june 12, 1672");
                    profileObject.setTribe("urhobo");
                    profileObject.setUid(firebaseUser.getUid());
                    profileObject.setFollowersCount(0);
                    profileObject.setFollowingCount(0);

                    reference.child("users").child(firebaseUser.getUid()).child("profile").setValue(profileObject, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError,
                                               @NonNull DatabaseReference databaseReference) {
                            Toast.makeText(RegistrationActivity.this, "profile created",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    Intent toSplashActivity = new Intent(
                            RegistrationActivity.this, SplashActivity.class
                    );
                    toSplashActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(toSplashActivity);
                    finish();
                }else {
                    Toast.makeText(
                            RegistrationActivity.this, "Sign up failed", Toast.LENGTH_LONG
                    ).show();
                }
            }
        });
    }
}
