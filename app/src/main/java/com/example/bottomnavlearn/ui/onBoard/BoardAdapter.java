package com.example.bottomnavlearn.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavlearn.R;
import com.example.bottomnavlearn.databinding.PagerBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder>{

    private PagerBoardBinding binding;
    //Create String array for title text
    private String[] list = {"Code everywhere", "Eat on time", "Sleep well", "Reapet",};

    //Create Integer array for Images
    private Integer[] imageList = {R.raw.coding, R.raw.eat, R.raw.sleep, R.raw.reapet};


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

    public String[] getList() {
        return list;
    }

    public Integer[] getImageList() {
        return imageList;
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
//            binding.boardImageView.setImageResource(imageList[position]);
            binding.boardImageView.setAnimation(imageList[position]);
        }
    }
}
