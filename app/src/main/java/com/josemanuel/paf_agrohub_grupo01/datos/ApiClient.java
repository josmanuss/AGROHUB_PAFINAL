package com.josemanuel.paf_agrohub_grupo01.datos;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://grupo1tdam.pythonanywhere.com/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        return (retrofit == null) ? new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                : retrofit;
    }
}
