package net.naylinaung.meal_ordering.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.naylinaung.meal_ordering.MealOrderingApp;
import net.naylinaung.meal_ordering.R;
import net.naylinaung.meal_ordering.adapters.MealAdapter;
import net.naylinaung.meal_ordering.data.models.MealModel;
import net.naylinaung.meal_ordering.data.vos.MealVO;
import net.naylinaung.meal_ordering.events.DataEvent;
import net.naylinaung.meal_ordering.views.holders.MealViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class MealListFragment extends Fragment {

    @BindView(R.id.rv_meals)
    RecyclerView rvMeals;

    public enum COLUMN_COUNT {
        ONE(1), TWO(2);

        private int numVal;

        COLUMN_COUNT(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
    }

    private static final String BARG_COLUMN_COUNT = "column_count";
    private COLUMN_COUNT columnCount = COLUMN_COUNT.ONE;

    private MealAdapter mMealAdapter;
    private MealViewHolder.ControllerMealItem controllerMealItem;

    public static MealListFragment newInstance(COLUMN_COUNT columnCount) {
        MealListFragment fragment = new MealListFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable(BARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controllerMealItem = (MealViewHolder.ControllerMealItem) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meal_list, container, false);
        ButterKnife.bind(this, v);

        setHasOptionsMenu(true);

        /*MealModel.getInstance().loadMeals();*/
        List<MealVO> mealList = MealModel.getInstance().getMealList();
        mMealAdapter = new MealAdapter(mealList, controllerMealItem);
        rvMeals.setAdapter(mMealAdapter);

        Bundle bundle = getArguments();
        COLUMN_COUNT columnCount = (bundle != null) ? (COLUMN_COUNT) bundle.getSerializable(BARG_COLUMN_COUNT) : COLUMN_COUNT.ONE;

        setupLayout(columnCount);
        return v;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_two_columns: {
                columnCount = (columnCount == COLUMN_COUNT.ONE) ? COLUMN_COUNT.TWO : COLUMN_COUNT.ONE;
                setupLayout(this.columnCount);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupLayout(COLUMN_COUNT gridColumnSpanCount) {
        rvMeals.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount.getNumVal()));
    }

    public void onEventMainThread(DataEvent.MealDataLoadedEvent event) {
        List<MealVO> newMealList = event.getMealList();
        this.mMealAdapter.setNewData(newMealList);
        this.mMealAdapter.notifyDataSetChanged();
    }
}
