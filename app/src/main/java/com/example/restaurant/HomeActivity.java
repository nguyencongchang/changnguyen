package com.example.restaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurant.FragmentApp.DisplayStaffFragment;
import com.example.restaurant.FragmentApp.DisplayDeskFragment;
import com.example.restaurant.FragmentApp.DisplayMenuFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    TextView txtusername;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_home);
        View view = navigationView.inflateHeaderView(R.layout.layout_nav_header);
        txtusername = view.findViewById(R.id.txt_username);
        fragmentManager = getSupportFragmentManager();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerToggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        txtusername.setText(username);

        FragmentTransaction tranDisk = fragmentManager.beginTransaction();
        DisplayDeskFragment displayDeskFragment = new DisplayDeskFragment();
        tranDisk.replace(R.id.content, displayDeskFragment);
        tranDisk.commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_home:
                FragmentTransaction tranDisk = fragmentManager.beginTransaction();
                DisplayDeskFragment displayDeskFragment = new DisplayDeskFragment();
                tranDisk.replace(R.id.content, displayDeskFragment);
                tranDisk.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.item_menu:
                FragmentTransaction tranMenu = fragmentManager.beginTransaction();
                DisplayMenuFragment displayMenuFragment = new DisplayMenuFragment();
                tranMenu.replace(R.id.content, displayMenuFragment);
                tranMenu.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.item_staff:
                FragmentTransaction transtaff = fragmentManager.beginTransaction();
                DisplayStaffFragment displayStaffFragment = new DisplayStaffFragment();
                transtaff.replace(R.id.content, displayStaffFragment);
                transtaff.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.item_analystics:
                break;
            case R.id.item_logout:
                finish();
                break;
        }
        return false;
    }
}
