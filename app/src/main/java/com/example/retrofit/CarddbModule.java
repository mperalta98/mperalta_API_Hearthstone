package com.example.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarddbModule {
    static CarddbAPI carddbAPI;

    public static CarddbAPI getAPI(){
        if(carddbAPI == null){
            final OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .addInterceptor(new ApiKeyInterceptor())
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15,TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://omgvamp-hearthstone-v1.p.mashape.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            carddbAPI = retrofit.create(CarddbAPI.class);
        }
        return carddbAPI;
    }
}

class ApiKeyInterceptor implements Interceptor {
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
//        HttpUrl originalHttpUrl = original.url();
//        HttpUrl url = originalHttpUrl.newBuilder()
//                .addQueryParameter("api_key", "c0dd73dba1a24e188c39899b3d7e05da")
//                .build();


//      API REQUEST HS Mashape key

        Request.Builder requestBuilder = original.newBuilder()
                .header("X-Mashape-Key", "GL5PGdPffCmshcUuGxnZFqmKXE3Ap1qZDWjjsnG9lvtJc3ufru");

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

class LoggingInterceptor implements Interceptor {
    @Override public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.e("INTERCEPTOR", String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        okhttp3.Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.e("INTERCEPTOR---", String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}