package com.example.bottomnavlearn.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.bottomnavlearn.App;
import com.example.bottomnavlearn.R;
import com.example.bottomnavlearn.databinding.FragmentHomeBinding;
import com.example.bottomnavlearn.models.User;

import java.util.List;

public class HomeFragment extends Fragment implements TaskAdapter.onItemClick {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private TaskAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TaskAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRv();
        initListiners();
        setFragmentListener();
        App.database.userDao().getAllUsers().observe(getViewLifecycleOwner(),
                new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        adapter.setList(users);
                    }
                });

    }

    private void initRv() {
        adapter.setListener(this);
        binding.taskRv.setAdapter(adapter);
    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), (requestKey, result) -> {
            User user = (User) result.getSerializable("user");
            App.database.userDao().addUser(user);
        });
    }

    private void initListiners() {
        binding.actionBtn.setOnClickListener(v -> {
            openFragment();
        });
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.formFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(String txt) {
        Toast.makeText(requireContext(), txt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int position) {
        Log.e("TAG", "onLongClick: "+ position );
        new AlertDialog.Builder(requireContext())
                .setMessage("Вы действительно хотите удалить?")
                .setIcon(R.drawable.ic_delete)
                .setTitle("Внимание")
                .setNegativeButton("Нет", null)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.removeItem(position);
                        binding.taskRv.setAdapter(adapter);
                    }
                }).show();
    }
}