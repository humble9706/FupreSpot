package com.toborehumble.fuprespot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            Intent toChooseLoginOrRegistration = new Intent(
                    SplashActivity.this, ChooseLoginOrRegistrationActivity.class
            );
            toChooseLoginOrRegistration.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(toChooseLoginOrRegistration);
            finish();
        }else{
            Intent toMainActivity = new Intent(
                    SplashActivity.this, MainActivity.class
            );
            toMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(toMainActivity);
            finish();
        }
    }
}
