package com.example.socialmediaapi;

import com.example.socialmediaapi.Model.AllUsers;
import com.example.socialmediaapi.Model.Comment.CommentResponse;
import com.example.socialmediaapi.Model.CreatePost.CreatePost;
import com.example.socialmediaapi.Model.CurrentUser;
import com.example.socialmediaapi.Model.FriendRequest.AcceptFriendRequestResponse;
import com.example.socialmediaapi.Model.FriendRequest.DeleteFriendRequest;
import com.example.socialmediaapi.Model.FriendRequest.FriendRequest;
import com.example.socialmediaapi.Model.FriendRequest.MyFriendResponse;
import com.example.socialmediaapi.Model.FriendRequest.RequestResponse;
import com.example.socialmediaapi.Model.Like.LikeResponse;
import com.example.socialmediaapi.Model.LoginModel.LoginResponse;
import com.example.socialmediaapi.Model.PostModel.Post;
import com.example.socialmediaapi.Model.PostModel.PostList;
import com.example.socialmediaapi.Model.PostModel.SinglePost;
import com.example.socialmediaapi.Model.PostModel.User;
import com.example.socialmediaapi.Model.RegistrationModel.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("user/register")
    Call<RegistrationResponse> register(
            @Field("email") String email,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("password") String password,
            @Field("birthDate") String dateOfBirth
    );


    @FormUrlEncoded
     @POST("user/login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("user/all")
    Call<AllUsers> fetchAllUser(@Header("Authorization") String token);


    @GET("user/all")
    Call<User> users(@Header("Authorization") String token);

    @GET("post/all")
    Call<PostList> fetchAllPost(@Header("Authorization") String token);


    @GET("user/")
    Call<CurrentUser> fetchCurrentUser(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("user/friend-request")
    Call<FriendRequest> sendFriendRequ(@Header("Authorization") String token,
                                       @Field("receiver") String receiver);

    @GET("user/friend-request")
    Call<RequestResponse> getFriendRequest(@Header("Authorization") String token);

    @GET("user/friend")
    Call<MyFriendResponse> getAllFriend(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("user/accept-friend-request")
    Call<AcceptFriendRequestResponse> AcceptFriendRequ(@Header("Authorization") String token,
                                                       @Field("receiver")String receiver);
    @PUT("user/friend-request")
    Call<DeleteFriendRequest> DeleteFriendRequest(@Header("Authorization") String token,
                                                  @Field("receiver")String receiver);

    @Headers({"Content-Type: multipart/form-data"})
    @FormUrlEncoded
    @POST("post")
    Call<CreatePost> CreatePost(@Header("Authorization")String token,
                                @Field("images")String images,
                                @Field("description") String description

    );

    @FormUrlEncoded
    @POST("post/comment")
    Call<CommentResponse> Comment(@Header("Authorization")String token,
                                  @Field("postId")String postId,
                                  @Field("comment")String comment);


    @GET("post/single/{id}")
    Call<SinglePost> getSinglePost(@Header("Authorization")String token,
                                   @Path("id") String id );

    @FormUrlEncoded
    @POST("post/like")
    Call<LikeResponse> Like(@Header("Authorization")String token,
                            @Field("postId")String postId,
                            @Field("type")String type);

}
