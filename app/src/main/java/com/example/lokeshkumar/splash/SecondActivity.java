package com.example.lokeshkumar.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.core.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView = null;
    Toolbar toolbar = null;
    ImageView imgView;
    SharedPreferences sharedPreferences;
public static final int RESULT_LOAD_IMAGE=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        SettingsFragment fragment = new SettingsFragment();
        androidx.core.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        imgView = (ImageView) hView.findViewById(R.id.imageUser);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uploadProfileImage=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(uploadProfileImage,RESULT_LOAD_IMAGE);
            }
        });
            }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();

imgView.setImageURI(selectedImage);

        } else {

            Toast.makeText(this, "You haven't picked Image",

                    Toast.LENGTH_LONG).show();

        }
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second, menu);
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.chat) {

            startActivity(new Intent(SecondActivity.this, ChatFragment.class));
            return (true);
        } else if (item.getItemId() == R.id.upload) {
            startActivity(new Intent(SecondActivity.this, UploadFlash.class));
            return (true);
        } else if (item.getItemId() == R.id.download) {
            startActivity(new Intent(SecondActivity.this, DownloadFragment.class));
            return (true);
        } else if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(SecondActivity.this, SettingsFragment.class));
            return (true);
        } else if (item.getItemId() == R.id.offline) {
            startActivity(new Intent(SecondActivity.this, OfflineNotesFragment.class));
            return (true);
        } else if (item.getItemId() == R.id.drawges) {
            startActivity(new Intent(SecondActivity.this, OfflineNotesFragment.class));
            return (true);
        }
        else if (item.getItemId() == R.id.audio) {
            startActivity(new Intent(SecondActivity.this, MusicFragment.class));
            return (true);
        }
        else if (item.getItemId() == R.id.videoplay) {
            startActivity(new Intent(SecondActivity.this, VideoFragment.class));
            return (true);
        }
        return (super.onOptionsItemSelected(item));
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.chat) {
            // Handle the camera action
            ChatFragment fragment = new ChatFragment();
            androidx.core.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.upload) {
            UploadFlash fragment = new UploadFlash();
            androidx.core.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.download) {
            DownloadFragment fragment = new DownloadFragment();
            androidx.core.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.action_settings) {
            SettingsFragment fragment = new SettingsFragment();
            androidx.core.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.offline) {
            OfflineNotesFragment fragment = new OfflineNotesFragment();
            androidx.core.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.drawges) {
            DrawingFragment fragment = new DrawingFragment();
            androidx.core.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
        else if (id == R.id.audio) {
            MusicFragment fragment = new MusicFragment();
            androidx.core.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }else if (id == R.id.videoplay) {
            VideoFragment fragment = new VideoFragment();
            androidx.core.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
