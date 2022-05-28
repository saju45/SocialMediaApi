package com.example.socialmediaapi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapi.Model.PostModel.Comment;
import com.example.socialmediaapi.Model.PostModel.Post;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.databinding.SimpleCommentBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.viewHolder> {


    Context context;
    List<Comment> comments;

    public CommentAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.comments = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.simple_comment,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

         Comment comment=comments.get(position);

       holder.binding.commentBody.setText(comment.getComment()+"");
       holder.binding.username.setText(comment.getUser().getFirstName()+" "+comment.getUser().getLastName());
       Picasso.get().load(String.valueOf(comment.getUser().getProfileImage())).placeholder(R.drawable.saju).into(holder.binding.profileImage);



    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        SimpleCommentBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding=SimpleCommentBinding.bind(itemView);
        }
    }
}
