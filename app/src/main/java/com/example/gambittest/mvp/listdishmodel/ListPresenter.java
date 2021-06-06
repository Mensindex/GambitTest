package com.example.gambittest.mvp.listdishmodel;

import android.util.Log;

import com.example.gambittest.DishModel;
import com.example.gambittest.MyApplication;
import com.example.gambittest.mvp.global.MvpPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPresenter extends MvpPresenter<ListView> {

    public void getListDishes() {
        getView().showProgress();
        MyApplication.getApiService()
                .getProducts()
                .enqueue(new Callback<List<DishModel>>() {
                    @Override
                    public void onResponse(Call<List<DishModel>> call, Response<List<DishModel>> response) {

                        getView().hideProgress();
                        getView().setDishList(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<DishModel>> call, Throwable t) {

                        getView().hideProgress();
                        Log.d("ОШИБКА", t.getMessage());
                    }
                });
    }

}
