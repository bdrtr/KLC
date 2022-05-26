package com.example.sorular;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.example.sorular.Adaptors.InvateAdaptor;
import com.example.sorular.Adaptors.ResultAdaptor;
import com.example.sorular.Adaptors.UserAdaptor;
import com.example.sorular.Fragments.PageDashboardDirections;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private String name;
    private InvateAdaptor invate;
    private ResultAdaptor ra;
    private UserAdaptor user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        definetion();
        take();
    }

    private void take() {
        ra = (ResultAdaptor) getIntent().getSerializableExtra("nesne");
    }

    public void definetion() {
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView,
                navHostFragment.getNavController());

        invate = (InvateAdaptor) getIntent().getSerializableExtra("nesne");
    }
}