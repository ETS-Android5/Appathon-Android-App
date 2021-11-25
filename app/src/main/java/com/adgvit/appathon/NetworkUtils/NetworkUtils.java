package com.adgvit.appathon.NetworkUtils;

import com.adgvit.appathon.NetworkInterface.NetworkAPI;

import java.net.URL;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkUtils {
    public static String baseURL = "https://appathon-backend.herokuapp.com/";
    public static OkHttpClient getClientInstance() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        return client;
    }

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .client(getClientInstance())
            .addConverterFactory(GsonConverterFactory.create()).build();

    public static NetworkAPI networkAPI = retrofit.create(NetworkAPI.class);
}
