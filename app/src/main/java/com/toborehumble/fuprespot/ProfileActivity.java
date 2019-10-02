package com.toborehumble.fuprespot;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toborehumble.fuprespot.pojos.ProfileObject;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    private TextView profile_quote;
    private TextView profile_hobby;
    private ImageView profile_image;
    private TextView profile_username;
    private TextView profile_department;

    FirebaseUser firebaseUser;
    ProfileObject profileObject;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference profileReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolBar();

        profile_hobby = findViewById(R.id.profile_page_hobby);
        profile_image = findViewById(R.id.profile_page_image);
        profile_username = findViewById(R.id.profile_page_username);
        profile_quote = findViewById(R.id.profile_page_profile_quote);
        profile_department = findViewById(R.id.profile_page_department);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        String userId = firebaseUser.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        profileReference = firebaseDatabase.getReference().child("users").child(userId).child(
                "profile");

        profileReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profileObject = dataSnapshot.getValue(ProfileObject.class);

                assert profileObject != null;
                profile_hobby.setText(profileObject.getHobby());
                profile_username.setText(profileObject.getUserName());
                profile_department.setText(profileObject.getDepartment());
                profile_quote.setText(profileObject.getProfileQuote());

                profile_image.setImageDrawable(
                        getResources().getDrawable(R.mipmap.ic_launcher)
                );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(
                        ProfileActivity.this, "Error fetching your profile",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    private void toolBar() {
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(
                getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp)
        );
        actionBar.setDisplayHomeAsUpEnabled(true);
    }



    private void uploadImage() {
        AssetManager assetManager = getAssets();
        String[] paths = new String[0];
        try {
            paths = assetManager.list("assets");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String path : paths){
            Toast.makeText(ProfileActivity.this, path, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId){
            case R.id.action_edit_profile: {
                Toast.makeText(
                        ProfileActivity.this, "Edit profile", Toast.LENGTH_LONG
                ).show();
                uploadImage();
            }break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
