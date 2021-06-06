package com.example.gambittest;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DishModelAdapter extends RecyclerView.Adapter<DishModelAdapter.ViewHolder> {
    private final List<DishModel> dishes;
    private Context context;
    private static SharedPreferences sPref;

    public DishModelAdapter(List<DishModel> dishes, Context context) {
        this.dishes = dishes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dishes.get(position));
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewDish, imageViewMinus, imageViewPlus, imageViewCart;
        private TextView dishName, dishPrice, dishCount;

        ViewHolder(View view) {
            super(view);
            imageViewDish = view.findViewById(R.id.imageViewDish);
            imageViewCart = view.findViewById(R.id.imageViewCart);
            imageViewMinus = view.findViewById(R.id.imageViewMinus);
            imageViewPlus = view.findViewById(R.id.imageViewPlus);
            dishName = view.findViewById(R.id.textViewDishName);
            dishPrice = view.findViewById(R.id.textViewPrice);
            dishCount = view.findViewById(R.id.textViewCount);
        }

        public void bind(DishModel dishModel) {

            dishModel.setCount(getCountById(dishModel.getId()));

            if (dishModel.getCount() > 0) {
                dishCount.setVisibility(View.VISIBLE);
                imageViewMinus.setVisibility(View.VISIBLE);
                imageViewPlus.setVisibility(View.VISIBLE);
                imageViewCart.setVisibility(View.INVISIBLE);
                dishCount.setText(String.valueOf(dishModel.getCount()));
            } else {
                dishCount.setVisibility(View.INVISIBLE);
                imageViewMinus.setVisibility(View.INVISIBLE);
                imageViewPlus.setVisibility(View.INVISIBLE);
                imageViewCart.setVisibility(View.VISIBLE);
            }

            Glide.with(itemView.getContext())
                    .load(dishModel.getImage())
                    .into(imageViewDish);

            dishName.setText(dishModel.getName());
            dishPrice.setText(String.valueOf(dishModel.getPrice()));

            imageViewCart.setOnClickListener(v -> {
                dishModel.setCount(dishModel.getCount() + 1);
                saveCountById(dishModel.getId(), dishModel.getCount());
                notifyItemChanged(getAdapterPosition(), new Object());
            });

            imageViewPlus.setOnClickListener(v -> {
                dishModel.setCount(dishModel.getCount() + 1);
                saveCountById(dishModel.getId(), dishModel.getCount());
                notifyItemChanged(getAdapterPosition(), new Object());
            });

            imageViewMinus.setOnClickListener(v -> {
                if (dishModel.getCount() > 0) {
                    dishModel.setCount(dishModel.getCount() - 1);
                    saveCountById(dishModel.getId(), dishModel.getCount());
                    notifyItemChanged(getAdapterPosition(), new Object());
                }
            });
        }

        private int getCountById(String id) {
            sPref = context.getSharedPreferences("ids", MODE_PRIVATE);
            return sPref.getInt(id, 0);
        }

        void saveCountById(String id, int count) {
            sPref = context.getSharedPreferences("ids", MODE_PRIVATE);
            sPref.edit()
                    .putInt(id, count)
                    .apply();
        }

    }
}



