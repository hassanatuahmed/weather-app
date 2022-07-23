package com.example.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.databinding.LayoutPostBinding;
import com.example.myapp.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    Context context;
    List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutPostBinding binding=LayoutPostBinding.inflate(LayoutInflater.from(context),parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewContent.setText(String.valueOf(postList.get(position).body));
        holder.textViewTitle.setText(String.valueOf(postList.get(position).title));
        holder.textViewAuthor.setText(new StringBuilder(postList.get(position)
                .body.substring(0,20)).append("...").toString());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle,textViewAuthor,textViewContent;

        public ViewHolder(@NonNull LayoutPostBinding binding) {
            super(binding.getRoot());
            textViewTitle=binding.txtTitle;
            textViewAuthor=binding.txtAuthor;
            textViewContent = binding.txtContent;
        }
    }
}
