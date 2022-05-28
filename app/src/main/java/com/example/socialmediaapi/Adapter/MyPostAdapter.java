package com.example.socialmediaapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapi.Activity.CommentActivity;
import com.example.socialmediaapi.Model.PostModel.Post;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.SharedPrefManager;
import com.example.socialmediaapi.databinding.SimplePostDesignBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.viewHolder> {

    Context context;
    ArrayList<Post> list;
    SharedPrefManager sharedPrefManager;
    String imageUrl;

    public MyPostAdapter(Context context, ArrayList<Post> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.simple_post_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        sharedPrefManager=new SharedPrefManager(context);
        Post post=list.get(position);

        String postId=post.getUser().get_id();

        holder.binding.username.setText(post.getUser().getFirstName()+" "+post.getUser().getLastName());

        holder.binding.liketext.setText(post.getLikes().size()+"");
        holder.binding.commentText.setText(post.getComments().size()+"");

        if (postId.equals(sharedPrefManager.getUser().get_id()))
        {

            if (post.getPostType().equals("text"))
            {
                holder.binding.postImg.setVisibility(View.GONE);
                holder.binding.postDescription.setText(""+post.getDescription());


            }else if (post.getPostType().equals("image")){

                imageUrl=post.getPostImage().get(0).getImage().getUri()+post.getPostImage().get(0).getImage().getFileName();
                Picasso.get().load(imageUrl).placeholder(R.drawable.saju).into(holder.binding.postImg);
                holder.binding.postDescription.setText(post.getDescription());
            }

        }




        holder.binding.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click like", Toast.LENGTH_SHORT).show();
            }
        });

        holder.binding.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context,CommentActivity.class);
                intent.putExtra("postId",post.get_id());
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        SimplePostDesignBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=SimplePostDesignBinding.bind(itemView);
        }
    }
}
