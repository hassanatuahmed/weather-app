package com.example.myapp.retrofit;



import com.example.myapp.model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyAPI {
    @GET("posts")
    Observable<List<Post>> getPost();
}
