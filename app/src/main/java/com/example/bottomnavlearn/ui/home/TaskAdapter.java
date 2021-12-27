package com.example.bottomnavlearn.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavlearn.App;
import com.example.bottomnavlearn.databinding.ItemRvBinding;
import com.example.bottomnavlearn.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<User> list = new ArrayList<>();
    private ItemRvBinding binding;
    private onItemClick listener;

    private static final String SHARED_PREFS = "shared_prefs";
    private static final String TEXT = "text";
    private String text;


    public void setList(List<User> list){
        this.list.clear();
        this.list.addAll(list);
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

        public void onBind(User user) {
            binding.nameTV.setText(user.getName());
            binding.surenameTV.setText(user.getSurename());
            itemView.setOnClickListener(v -> {
                listener.onClick(user.getName());
            });
            binding.delete.setOnClickListener(v -> {
                App.database.userDao().deleteUser(user);
                notifyDataSetChanged();
            });
            binding.editRv.setOnClickListener(v -> {
                App.database.userDao().editUser(user);
            });
        }
    }

    interface onItemClick{
        void onClick(String txt);
        void onLongClick(int position);
    }
}
