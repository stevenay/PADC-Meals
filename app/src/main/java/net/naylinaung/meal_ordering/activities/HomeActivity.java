package net.naylinaung.meal_ordering.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import net.naylinaung.meal_ordering.MealOrderingApp;
import net.naylinaung.meal_ordering.R;
import net.naylinaung.meal_ordering.data.vos.MealVO;
import net.naylinaung.meal_ordering.dialogs.ShowOptionsDialog;
import net.naylinaung.meal_ordering.fragments.MealListFragment;
import net.naylinaung.meal_ordering.views.holders.MealViewHolder;

public class HomeActivity extends AppCompatActivity
    implements MealViewHolder.ControllerMealItem {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowOptionsDialog dialog = new ShowOptionsDialog();
                dialog.show(getSupportFragmentManager(), "error_dialog");

            }
        });

        if (savedInstanceState == null) {
            navigateToMealList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_two_columns:
                MealListFragment.COLUMN_COUNT columnCount;
                if (MealListFragment.ColumnCount == MealListFragment.COLUMN_COUNT.ONE) {
                    columnCount = MealListFragment.COLUMN_COUNT.TWO;
                    item.setIcon(R.drawable.ic_fullscreen_white_24dp);
                } else{
                    columnCount = MealListFragment.COLUMN_COUNT.ONE;
                    item.setIcon(R.drawable.ic_fullscreen_exit_white_24dp);
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_container, MealListFragment.newInstance(columnCount))
                        .commit();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigateToMealList() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, MealListFragment.newInstance(MealListFragment.COLUMN_COUNT.ONE))
                .commit();
    }

    @Override
    public void onTapMeal(MealVO meal, ImageView ivMeal) {
        Intent intent = MealDetailActivity.newIntent(meal.getId());
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair(ivMeal, getString(R.string.share_element_transistion_attractioin)));
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }
}
