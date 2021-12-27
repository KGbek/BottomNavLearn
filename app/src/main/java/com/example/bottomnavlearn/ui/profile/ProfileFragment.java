package com.example.bottomnavlearn.ui.profile;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavlearn.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ActivityResultLauncher<String> mGetContent;
    private String uriPath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
//                        binding.imgView.setImageURI(uri);
                        binding.circleImage.setImageURI(uri);
                        Uri photoUri = Uri.parse(uri.getPath().toString());
//                        binding.uriPhoto.setText(photoUri.toString());

                    }
                });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setImageClick();
        clearInputs();
    }

    private void clearInputs() {
        binding.clearBtn.setOnClickListener(v -> {
            binding.usernameTxt.getEditText().setText("");
            binding.emailTxt.getEditText().setText("");
            binding.phoneTxt.getEditText().setText("");
            binding.DOBTxt.getEditText().setText("");
            binding.AddressTxt.getEditText().setText("");
        });
    }


    public void setImageClick(){
        binding.imgBtn.setOnClickListener(v -> {
            mGetContent.launch("image/*");
        });
    }
}