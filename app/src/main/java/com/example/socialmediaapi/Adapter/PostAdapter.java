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
import com.example.socialmediaapi.Model.Like.LikeResponse;
import com.example.socialmediaapi.Model.PostModel.Likes;
import com.example.socialmediaapi.Model.PostModel.Post;
import com.example.socialmediaapi.Model.PostModel.SinglePost;
import com.example.socialmediaapi.R;
import com.example.socialmediaapi.Retrofit_Client;
import com.example.socialmediaapi.SharedPrefManager;
import com.example.socialmediaapi.databinding.SimplePostDesignBinding;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewHolder> {

    Context context;
    ArrayList<Post> list;
    SharedPrefManager sharedPrefManager;
    String imageUrl;

    public PostAdapter(Context context, ArrayList<Post> list) {
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


        //post.getUser().get_id().equals(sharedPrefManager.getUser().get_id());
       // post.getLikes().get(0).getUser().get_id().equals(sharedPrefManager.getUser().get_id());


        holder.binding.username.setText(post.getUser().getFirstName()+" "+post.getUser().getLastName());

        holder.binding.liketext.setText(post.getLikes().size()+"");
        holder.binding.commentText.setText(post.getComments().size()+"");

        if (post.getPostType().equals("text"))
        {
            holder.binding.postImg.setVisibility(View.GONE);
            holder.binding.postDescription.setText(""+post.getDescription());


        }else if (post.getPostType().equals("image")){

                 imageUrl=post.getPostImage().get(0).getImage().getUri()+post.getPostImage().get(0).getImage().getFileName();
                Picasso.get().load(imageUrl).placeholder(R.drawable.saju).into(holder.binding.postImg);
               holder.binding.postDescription.setText(post.getDescription());
        }



/*

        if (post.getLikes().get(position).getUser().get_id().equals(sharedPrefManager.getUser().get_id()))
        {
            Call<LikeResponse> call= Retrofit_Client
                    .getInstance()
                    .getApi()
                    .Like(sharedPrefManager.getToken(),post.get_id(),"like");

            call.enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {

                    if (response.isSuccessful())
                    {
                        holder.binding.like.setImageResource(R.drawable.redheard);
                        Toast.makeText(context, "auto like successfully", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LikeResponse> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
*/


        Call<SinglePost> call=Retrofit_Client
                .getInstance()
                .getApi()
                .getSinglePost(sharedPrefManager.getToken(),post.get_id());

        call.enqueue(new Callback<SinglePost>() {
            @Override
            public void onResponse(Call<SinglePost> call, Response<SinglePost> response) {


                if (response.isSuccessful())
                {
                    isLike(post.get_id());

                }else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SinglePost> call, Throwable t) {

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        holder.binding.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<LikeResponse> call= Retrofit_Client
                        .getInstance()
                        .getApi()
                        .Like(sharedPrefManager.getToken(),post.get_id(),"like");

                call.enqueue(new Callback<LikeResponse>() {
                    @Override
                    public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {

                        if (response.isSuccessful())
                        {
                            holder.binding.like.setImageResource(R.drawable.redheard);
                            Toast.makeText(context, "like sent successfully", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LikeResponse> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
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

    public void isLike(String postId)
    {

        for (int i=0 ;i<postId.length() ; i++)
        {

        }

    }
}
