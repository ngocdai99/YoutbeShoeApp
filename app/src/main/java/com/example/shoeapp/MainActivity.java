package com.example.shoeapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.shoeapp.fragments.FavoriteFragment;
import com.example.shoeapp.fragments.HomeFragment;
import com.example.shoeapp.fragments.ProfileFragment;
import com.example.shoeapp.fragments.VoucherFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary));

        setupBottomNav();
    }

    private void setupBottomNav() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new HomeFragment())
                .commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);


        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new VoucherFragment());
        fragmentList.add(new FavoriteFragment());
        fragmentList.add(new ProfileFragment());


        bottomNavigation.setOnItemSelectedListener(item -> {
            int position = -1;

            if (item.getItemId() == R.id.bottomHome) {
                position = 0;
            } else if (item.getItemId() == R.id.bottomVoucher) {
                position = 1;
            } else if (item.getItemId() == R.id.bottomFavorite) {
                position = 2;
            } else if (item.getItemId() == R.id.bottomProfile) {
                position = 3;
            }


            Fragment fragment = fragmentList.get(position);
            if (position != -1){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .commit();
            }

            return true;
        });
    }


}