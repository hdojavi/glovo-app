package com.jhernando.glovo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jhernando.glovo.fragments.HomeFragment;
import com.jhernando.glovo.orders.ordersActivity;
import com.jhernando.glovo.userProfile.userProfileActivity;
import com.jhernando.glovo.fragments.SettingsFragment;
import com.jhernando.glovo.fragments.UserSettingsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.NavBot);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new HomeFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        SharedPreferences userDetails = getSharedPreferences("userdetails", MODE_PRIVATE);
        int userId = userDetails.getInt("userId", 0);

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_orders:
                if (userId > 0) {
                    fragment = new ordersActivity();
                } else {
                    Toast.makeText(this, "Cuenta necesaria para ir a Tus pedidos!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.navigation_profile:
                if (userId > 0) {
                    fragment = new userProfileActivity();
                } else {
                    Toast.makeText(this, "Cuenta necesaria para ir a Tu perfil!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.navigation_settings:
                if (userId > 0) {
                    fragment = new UserSettingsFragment();
                } else {
                    fragment = new SettingsFragment();
                }
                break;

        }
        return loadFragment(fragment);
    }
}