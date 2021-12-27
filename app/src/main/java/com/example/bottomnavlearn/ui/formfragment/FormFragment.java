package com.example.bottomnavlearn.ui.formfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bottomnavlearn.App;
import com.example.bottomnavlearn.R;
import com.example.bottomnavlearn.databinding.FragmentFormBinding;
import com.example.bottomnavlearn.databinding.FragmentHomeBinding;
import com.example.bottomnavlearn.models.User;

import java.io.Serializable;


public class FormFragment extends Fragment {

    private FragmentFormBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
    }

    private void initListeners() {
        binding.saveBtn.setOnClickListener(v -> {
            save();
            close();
        });
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    private void save() {
        String name = binding.usernameEt.getText().toString();
        String surename = binding.surenameEt.getText().toString();
        User user = new User(name, surename);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        getParentFragmentManager().setFragmentResult("key", bundle);
        Log.e("TAG", "savedNote: ");
    }
}