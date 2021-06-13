package com.example.gambittest;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DishModelAdapter extends RecyclerSwipeAdapter<DishModelAdapter.ViewHolder> {
    private final List<DishModel> dishes;
    private Context context;
    private static SharedPreferences sPref;
    private static SharedPreferences sPref2;

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

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.mySwipe;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewDish, imageViewMinus, imageViewPlus, imageViewCart, imageViewFavOff, imageViewFavOn;
        private TextView dishName, dishPrice, dishCount;
        private SwipeLayout swipeLayout;

        ViewHolder(View view) {
            super(view);
            swipeLayout = view.findViewById(R.id.mySwipe);
            imageViewDish = view.findViewById(R.id.imageViewDish);
            imageViewCart = view.findViewById(R.id.imageViewCart);
            imageViewMinus = view.findViewById(R.id.imageViewMinus);
            imageViewPlus = view.findViewById(R.id.imageViewPlus);
            imageViewFavOff = view.findViewById(R.id.imageViewFavOff);
            imageViewFavOn = view.findViewById(R.id.imageViewFavOn);
            dishName = view.findViewById(R.id.textViewDishName);
            dishPrice = view.findViewById(R.id.textViewPrice);
            dishCount = view.findViewById(R.id.textViewCount);
        }


        public void bind(DishModel dishModel) {

            dishModel.setCount(getCountById(dishModel.getId()));

            dishModel.setFavorite(getFavById(dishModel.getId()));


            swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewById(R.id.bottom_wrapper));
            swipeLayout.setLeftSwipeEnabled(false);


            if (dishModel.getFavorite() != true) {
                imageViewFavOff.setVisibility(View.INVISIBLE);
                imageViewFavOn.setVisibility(View.VISIBLE);
            } else {
                imageViewFavOff.setVisibility(View.VISIBLE);
                imageViewFavOn.setVisibility(View.INVISIBLE);
            }

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

            //Добавляю слушатель для свайпа и реализовываю все его методы (В итоге ни один из методов на подошел)
            /* swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
                @Override
                public void onStartOpen(SwipeLayout layout) {

                }

                @Override
                public void onOpen(SwipeLayout layout) {

                }

                @Override
                public void onStartClose(SwipeLayout layout) {
                    //Toast.makeText(context, "Закрыл свайп", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onClose(SwipeLayout layout) {

                }

                @Override
                public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {


                }

                @Override
                public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                    //when user's hand released.

                }

            }); */


            swipeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if ((swipeLayout.getOpenStatus() == SwipeLayout.Status.Open)) {
                        //Start your activity
                        if (dishModel.getFavorite() == false) {
                            dishModel.setFavorite(true);
                            saveFavById(dishModel.getId(), dishModel.getFavorite());
                            Toast.makeText(context, "Добавлено в избранное", Toast.LENGTH_SHORT).show();
                        } else {
                            dishModel.setFavorite(false);
                            saveFavById(dishModel.getId(), dishModel.getFavorite());
                            Toast.makeText(context, "Удалено из избранного", Toast.LENGTH_SHORT).show();
                        }

                        notifyItemChanged(getAdapterPosition(), new Object());
                    }

                }
            });


            Glide.with(itemView.getContext())
                    .

                            load(dishModel.getImage())
                    .

                            into(imageViewDish);

            dishName.setText(dishModel.getName());
            dishPrice.setText(String.valueOf(dishModel.getPrice()));

            imageViewCart.setOnClickListener(v ->
            {
                dishModel.setCount(dishModel.getCount() + 1);
                saveCountById(dishModel.getId(), dishModel.getCount());
                notifyItemChanged(getAdapterPosition(), new Object());
            });

            imageViewPlus.setOnClickListener(v ->
            {
                dishModel.setCount(dishModel.getCount() + 1);
                saveCountById(dishModel.getId(), dishModel.getCount());
                notifyItemChanged(getAdapterPosition(), new Object());
            });

            imageViewMinus.setOnClickListener(v ->
            {
                if (dishModel.getCount() > 0) {
                    dishModel.setCount(dishModel.getCount() - 1);
                    saveCountById(dishModel.getId(), dishModel.getCount());
                    notifyItemChanged(getAdapterPosition(), new Object());
                }
            });
        }


        private boolean getFavById(String id) {
            sPref2 = context.getSharedPreferences("fav", MODE_PRIVATE);
            return sPref2.getBoolean(id, false);
        }

        void saveFavById(String id, boolean isFavorite) {
            sPref2 = context.getSharedPreferences("fav", MODE_PRIVATE);
            sPref2.edit()
                    .putBoolean(id, isFavorite)
                    .apply();
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



