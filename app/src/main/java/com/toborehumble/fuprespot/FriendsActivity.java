package com.toborehumble.fuprespot;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toborehumble.fuprespot.adapters.UsersAdapter;
import com.toborehumble.fuprespot.pojos.UserObject;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<UserObject> userObjects;
    UsersAdapter usersAdapter;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference followersReference;
    DatabaseReference usersReference;
    FirebaseUser firebaseUser;

    ValueEventListener userListener;
    ValueEventListener followersListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        toolBar();
        userObjects = new ArrayList<>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        determineWhatToLoad();
    }

    private void determineWhatToLoad() {
        followersReference = FirebaseDatabase.getInstance().
                getReference().child("users")
                .child(firebaseUser.getUid())
                .child("followers");
        followersListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> followers = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    followers.add(snapshot.getValue(String.class));
                    Log.i("snap", snapshot.getValue(String.class));
                }
                if(followers.size() == 0){
                    getSuggestedUsersFromFireBase();
                }else{
                    Toast.makeText(FriendsActivity.this,
                            "followers greater than 0 : " + followers.size(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FriendsActivity.this, "fail",
                        Toast.LENGTH_LONG).show();
            }
        };
        followersReference.addValueEventListener(followersListener);
    }

    private void getFollowersFromFireBase(){

    }

    private void getFollowingsFromFireBase(){

    }

    private void getSuggestedUsersFromFireBase() {
        usersReference = FirebaseDatabase.getInstance().getReference().child("users");
        userListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapShot : dataSnapshot.getChildren()) {
                    userObjects.add(userSnapShot.getValue(UserObject.class));
                }

                getSupportActionBar().setTitle("Suggested users");
                getSupportActionBar().setSubtitle("People you may know");
                usersAdapter = new UsersAdapter(FriendsActivity.this, userObjects);
                recyclerView = findViewById(R.id.friends_activity_recycler);
                layoutManager = new LinearLayoutManager(FriendsActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FriendsActivity.this, "fail 2",
                        Toast.LENGTH_LONG).show();
            }
        };

        usersReference.addValueEventListener(userListener);
    }

    private void toolBar() {
        Toolbar toolbar = findViewById(R.id.friends_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(
                getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp)
        );
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.friends_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_friends_search: {
                Toast.makeText(
                        FriendsActivity.this, "Friends search clicked", Toast.LENGTH_LONG
                ).show();
            }
            break;
            case R.id.action_friends_logout: {
                Toast.makeText(
                        FriendsActivity.this, "Logout clicked", Toast.LENGTH_LONG
                ).show();
            }
            break;
            case R.id.action_friends_settings: {
                Toast.makeText(
                        FriendsActivity.this, "Settings clicked", Toast.LENGTH_LONG
                ).show();
            }
            break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        for(int i = 0; i < 10; i++){
            followersReference.removeEventListener(followersListener);
            usersReference.removeEventListener(userListener);
        }
    }*/
}
