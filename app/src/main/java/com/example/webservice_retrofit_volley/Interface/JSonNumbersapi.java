package com.example.webservice_retrofit_volley.Interface;

import androidx.annotation.NonNull;

import com.example.webservice_retrofit_volley.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSonNumbersapi {
    @GET("{number}?json")
    Call<Posts> getPosts(@Path("number") int number);
}
