package com.toborehumble.fuprespot;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.toborehumble.fuprespot.pojos.FollowObject;
import com.toborehumble.fuprespot.pojos.FollowingObject;
import com.toborehumble.fuprespot.pojos.FriendRequestObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserDetailActivity extends AppCompatActivity {
    String username;
    String userUid;
    FirebaseUser authUser;
    Button follow;

    DatabaseReference followersReference;
    DatabaseReference followingReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        follow = findViewById(R.id.follow);
        authUser = FirebaseAuth.getInstance().getCurrentUser();

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        userUid = bundle.getString("userUid");

        toolbar();

        followersReference = FirebaseDatabase.getInstance().getReference().child("users")
                .child(userUid).child("followers");

        followingReference = FirebaseDatabase.getInstance().getReference().child("users")
                .child(authUser.getUid()).child("following");

        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFriendRequest();
            }
        });
    }

    private void toolbar() {
        Toolbar toolbar = findViewById(R.id.user_detail_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(
                getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp)
        );
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_follow_user: {
                sendFriendRequest();
            }
            break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void sendFriendRequest() {
        FriendRequestObject friendRequestObject = new FriendRequestObject(
                authUser.getUid(), userUid, new Date().getTime(), false
        );
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        String key =
                dbRef.child("users").child(authUser.getUid()).child("friend_requests_made").push().getKey();

        Map<String, Object> requestUpdates = new HashMap<>();
        requestUpdates.put("/users/" + authUser.getUid() + "/friend_requests_made/" + key,
                friendRequestObject);

        requestUpdates.put("/users/" + userUid + "/friend_requests_received/" + key,
                friendRequestObject);

        dbRef.updateChildren(requestUpdates).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(UserDetailActivity.this, "Friend request sent", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserDetailActivity.this, "Friend request not sent",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
