package com.example.meimall;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "MainActivity";

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_closed);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new MainFragment());
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.cart:
                Toast.makeText(this, "Cart Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.categories:
                Toast.makeText(this, "Categories Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.infog:
                Toast.makeText(this, "About Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.terms:
                Toast.makeText(this, "Terms Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.licenses:
                Toast.makeText(this, "Licenses Selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    private void initViews() {
        Log.d(TAG, "initViews: ");
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationdrawer);
        toolbar = findViewById(R.id.toolbar);
    }
}
