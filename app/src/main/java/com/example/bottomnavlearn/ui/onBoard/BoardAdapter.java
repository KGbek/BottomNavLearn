package com.example.bottomnavlearn.ui.onBoard;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavlearn.MainActivity;
import com.example.bottomnavlearn.R;
import com.example.bottomnavlearn.databinding.PagerBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder>{

    private PagerBoardBinding binding;
    //Create String array for title text
    private String[] list = {"Code everywhere", "Eat on time", "Sleep well", "Reapet",};

    //Create Integer array for Images
    //TODO: Download and import images into drawable, initialize in array
    private Integer[] imageList = {R.drawable.code, R.drawable.eat, R.drawable.sleep, R.drawable.reapet};


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(int position) {
            binding.boardTextViewTitle.setText(list[position]);
            binding.boardImageView.setImageResource(imageList[position]);
        }
    }
}
