package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapp.adapter.PostAdapter;
import com.example.myapp.databinding.ActivityMainBinding;
import com.example.myapp.model.Post;
import com.example.myapp.retrofit.MyAPI;
import com.example.myapp.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyAPI myAPI;
    RecyclerView recyclerView;
    //private final CompositeDisposable disposables = new CompositeDisposable();
   CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        Retrofit retrofit= RetrofitClient.getInstance();
        myAPI =retrofit.create(MyAPI.class);
        recyclerView=binding.recycleView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(binding.getRoot());
        fetchData();

    }

    private void fetchData() {
        compositeDisposable.add(myAPI.getPost()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Post>>() {
            @Override
            public void accept(List<Post> posts) throws Exception {
                displayData(posts);
            }
        }));

    }

    private void displayData(List<Post> posts) {
        PostAdapter adapter=new PostAdapter(this,posts);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}