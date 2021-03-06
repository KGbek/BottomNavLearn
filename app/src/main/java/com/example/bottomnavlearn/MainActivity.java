package com.example.bottomnavlearn;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bottomnavlearn.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BottomNavigationView navView;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.myToolbar);
        initViews();
        initAppBar();
        initNavController();
    }

    private void initNavController() {
        NavController navController;
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


//        if (!Prefs.getInstance().isBoardShown()){
//            navController.navigate(R.id.boardFragment);
//            Prefs.getInstance().saveBoardState();
//        }
        if (!App.prefs.isBoardShown()){
            navController.navigate(R.id.boardFragment);
            App.prefs.saveBoardState();
        }
        //Hide navigation when we tap to particular navigation.
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination,
                                             @Nullable Bundle arguments) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(R.id.navigation_home);
                list.add(R.id.navigation_dashboard);
                list.add(R.id.navigation_notifications);
                list.add(R.id.navigation_profile);

                if (list.contains(destination.getId())){
                    binding.navView.setVisibility(View.VISIBLE);
                    binding.myToolbar.setVisibility(View.VISIBLE);
                } else {
                    binding.navView.setVisibility(View.GONE);
                    binding.myToolbar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initAppBar() {
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
    }

    private void initViews() {
        navView = findViewById(R.id.nav_view);
    }
}