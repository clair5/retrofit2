package com.example.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("posts/{post}")  //전체 url 에서 url을  제외한 end point를 적어준다.
    Call<PostResult> getPosts(@Path("post") String post);


}
