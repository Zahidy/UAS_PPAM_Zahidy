package com.uasppam.pizzarest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://retoolapi.dev/StWODX/";
    private static Retrofit retrofit = null;

    public static InterfaceAPI getRetrofitClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(InterfaceAPI.class);
    }
}
