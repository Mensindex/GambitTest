package com.example.gambittest.mvp.global;

public class MvpPresenter<Type extends MvpView> {
    private Type type;

    public Type getView() {
        return type;
    }

    public void attachView(Type type) {
        this.type = type;
    }
}
