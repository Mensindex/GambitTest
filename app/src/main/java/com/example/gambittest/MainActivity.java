package com.example.gambittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.gambittest.mvp.listdishmodel.ListPresenter;
import com.example.gambittest.mvp.listdishmodel.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListView {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);

        //Устанавливаю свой тулбар
        toolbar = findViewById(R.id.toolBarGambit);
        setSupportActionBar(toolbar);

        presenter = new ListPresenter();
        presenter.attachView(this);
        presenter.getListDishes();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDishList(List<DishModel> listDishes) {
        recyclerView.setAdapter(new DishModelAdapter(listDishes, MainActivity.this));

    }

    @Override
    public void showMessage(String message) {

    }
}