package com.example.gambittest.mvp.listdishmodel;

import com.example.gambittest.DishModel;
import com.example.gambittest.mvp.global.MvpView;

import java.util.List;

public interface ListView extends MvpView {

    void showProgress();

    void hideProgress();

    void setDishList(List<DishModel> listDishes);

    void showMessage(String message);
}
