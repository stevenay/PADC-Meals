package net.naylinaung.meal_ordering.activities;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.naylinaung.meal_ordering.MealOrderingApp;
import net.naylinaung.meal_ordering.R;
import net.naylinaung.meal_ordering.data.models.MealModel;
import net.naylinaung.meal_ordering.data.vos.MealVO;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.tv_ingredients)
    TextView tvIngredients;

    @BindView(R.id.tv_meal_description)
    TextView tvMealDescription;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.iv_meal)
    ImageView ivMeal;

    private static final String IE_MEAL_ID = "meal_id";
    MealVO meal;

    public static Intent newIntent(String id) {
        Intent intent = new Intent(MealOrderingApp.getContext(), MealDetailActivity.class);
        intent.putExtra(IE_MEAL_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this, this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String mealId = getIntent().getStringExtra(IE_MEAL_ID);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            String tsName = getResources().getString(R.string.share_element_transistion_attractioin);
            ivMeal.setTransitionName(tsName);
        }

        meal = MealModel.getInstance().getMealById(mealId);

        if (meal == null) {
            throw new RuntimeException("Can't find Meal obj with the id : " + mealId);
        } else {
            collapsingToolbar.setTitle(meal.getName());
            tvPrice.setText(String.valueOf(meal.getPrice()));

            String ingredients = "";
            for (String ingredientName : meal.getIngredients()) {
                ingredients += ingredientName + "\n";
            }

            tvIngredients.setText(ingredients);
            tvMealDescription.setText(meal.getDescription());

            Glide.with(ivMeal.getContext())
                    .load(meal.getImageUrl().toString())
                    .centerCrop()
                    .placeholder(R.drawable.stock_photo_placeholder)
                    .into(ivMeal);
        }

    }
}
