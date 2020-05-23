package com.sasteyguru.pslscheduleandlivescores;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){


    NotificationChannel channel=new NotificationChannel("Notifications","Notifications", NotificationManager.IMPORTANCE_DEFAULT);

    NotificationManager manager=getSystemService(NotificationManager.class);
    manager.createNotificationChannel(channel);
    FirebaseMessaging.getInstance().subscribeToTopic("general")
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    String msg = "successfull";
                    if (!task.isSuccessful()) {
                        msg = "not successfull";
                    }

                  //  Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
}
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new homeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new homeFragment();
                            break;

                    }
                    switch (item.getItemId()) {
                        case R.id.teams:
                            selectedFragment = new TeamsFragment();


                            break;

                    }
                    switch (item.getItemId()) {
                        case R.id.stadiums:
                            selectedFragment = new VenuesFragment();
                            break;


                    }
                    switch (item.getItemId()) {
                        case R.id.score:
                            selectedFragment = new LiveScoreFragment();
                            break;

                    }
                    switch (item.getItemId()) {
                        case R.id.more:
                            selectedFragment = new moreFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}
