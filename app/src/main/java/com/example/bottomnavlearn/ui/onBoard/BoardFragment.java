package com.example.bottomnavlearn.ui.onBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bottomnavlearn.R;
import com.example.bottomnavlearn.databinding.FragmentBoardBinding;
import com.example.bottomnavlearn.databinding.PagerBoardBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Collections;
import java.util.List;

public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding;
    private BoardAdapter adapter;
    private PagerBoardBinding pagerBoardBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();
        new TabLayoutMediator(binding.TabDots, binding.BoardViewPager2, ((tab, position) -> {
            tab.setIcon(R.drawable.tab_selector);
        })).attach();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
        binding.btnSkip.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigateUp();
        });
    }

    private void initViewPager() {
        adapter = new BoardAdapter();
        binding.BoardViewPager2.setAdapter(adapter);
    }

}