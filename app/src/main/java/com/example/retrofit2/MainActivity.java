package com.example.retrofit2;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private WebView webview;
    private void setRetrofitInit() { // app 값호출 설정해주기(retrofit)
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")  //base주소 뒤에는 안해도됨 factory와 다르게 작성
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service1 = retrofit.create(RetrofitService.class);
        Call<PostResult> call = service1.getPosts("1");
        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                if(response.isSuccessful()) {
                    //정상적으로 성공 된 경우
                    PostResult result = response.body();
                    Log.d(TAG, "onResponse: 성공, 결과 \n"+result.toString());
                    tvResult.setText(result.toString());
                }else {
                    //통신이 실패한 경우 (응답 코드 3xx, 4xx 등)
                    Log.d(TAG, "onResponse: 실패");
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                // 통신 실패 (인터넷 끊김, 예외 발생 등 시스템적인 이유)
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {    //web 값 보여지기(webview)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);
        webview = findViewById(R.id.webview);
        setRetrofitInit();
        webview.loadUrl("https://jsonplaceholder.typicode.com/posts/1");
    }

}