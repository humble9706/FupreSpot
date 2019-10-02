package com.toborehumble.fuprespot;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    public static final int READ_EXTERNAL_STORAGE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar();
        tabLayout();
        
        requestStoragePermission();

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        //Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = firebaseAuth.getCurrentUser();
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(
                MainActivity.this,
                new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                READ_EXTERNAL_STORAGE
        );
    }

    private void tabLayout() {
        TabLayout tabLayout = findViewById(R.id.activity_main_tab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();
                switch (tabPosition){
                    case 1: {
                        Intent toFriendsActivity = new Intent(
                                MainActivity.this, FriendsActivity.class
                        );
                        startActivity(toFriendsActivity);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void toolBar() {
        Toolbar toolbar = findViewById(R.id.a_main_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_settings: {
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_LONG).show();
            }
            break;
            case R.id.action_search: {
                Toast.makeText(this, "Search clicked", Toast.LENGTH_LONG).show();
            }
            break;
            case R.id.action_logout: {
                logUserOut();
            }
            break;
            case R.id.action_profile: {
                gotoProfilePage();
            }
            break;
        }
        return true;
    }

    private void gotoProfilePage() {
        Intent toProfileActivity = new Intent(
                MainActivity.this, ProfileActivity.class
        );
        startActivity(toProfileActivity);
    }

    protected void logUserOut(){
        firebaseAuth.signOut();
        Intent toSplashActivity = new Intent(
                MainActivity.this, SplashActivity.class
        );
        toSplashActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(toSplashActivity);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
