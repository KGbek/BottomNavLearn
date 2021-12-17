package com.example.bottomnavlearn.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavlearn.databinding.ItemRvBinding;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ArrayList<String> list = new ArrayList<>();;
    private ItemRvBinding binding;
    private onItemClick listener;


    public void setText(String text){
        list.add(text);
        notifyDataSetChanged();
    }

    public void setListener(onItemClick listener){
        this.listener = listener;
    }

    public void removeItem(int position){
        Log.e("TAG", "removeItem: " + list.get(position) );
        list.remove(position);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(v -> {
                listener.onLongClick(getAdapterPosition());
                return true;
            });

        }

        public void onBind(String text) {
            binding.titleRv.setText(text);
            itemView.setOnClickListener(v -> {
                listener.onClick(text);
            });
        }
    }

    interface onItemClick{
        void onClick(String txt);
        void onLongClick(int position);
    }
}
