package com.example.gambittest;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    private static final String BASE_URL = "https://api.gambit-app.ru/category/";
    private static ApiService apiService;


    public static ApiService getApiService() {
        return apiService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //В билдере мы указываем базовый URL и добавляем Gson конвертер,
        // чтобы Retrofit сам смог сконвертировать json данные в объекты Message
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //В итоге у нас есть объект Retrofit, который содержит базовый URL
        // и способность преобразовывать json данные с помощью Gson.
        // Мы передаем ему в метод create класс интерфейса, в котором описывали методы
        // и получаем реализацию ApiService от Retrofit
        apiService = retrofit.create(ApiService.class);
    }
}
