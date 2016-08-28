package net.naylinaung.meal_ordering.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.naylinaung.meal_ordering.MealOrderingApp;
import net.naylinaung.meal_ordering.R;
import net.naylinaung.meal_ordering.data.vos.MealVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dell on 8/19/2016.
 */
public class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_meal_name)
    TextView tvMealName;

    @BindView(R.id.iv_meal)
    ImageView ivMeal;

    private ControllerMealItem mController;
    private MealVO mMeal;

    public MealViewHolder(View itemView, ControllerMealItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(MealVO meal) {
        mMeal = meal;
        tvMealName.setText(meal.getName());

        String imageUrl = meal.getImageUrl();

        Glide.with(ivMeal.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivMeal);
    }

    @Override
    public void onClick(View view) {
        mController.onTapMeal(mMeal, ivMeal);
    }

    public interface ControllerMealItem {
        void onTapMeal(MealVO meal, ImageView ivMeal);
    }
}
