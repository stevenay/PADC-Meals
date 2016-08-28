package net.naylinaung.meal_ordering.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.naylinaung.meal_ordering.MealOrderingApp;
import net.naylinaung.meal_ordering.R;
import net.naylinaung.meal_ordering.data.vos.MealVO;
import net.naylinaung.meal_ordering.views.holders.MealViewHolder;

import java.util.List;

/**
 * Created by Dell on 8/27/2016.
 */
public class MealAdapter extends RecyclerView.Adapter<MealViewHolder> {

    private LayoutInflater mInflater;
    private List<MealVO> mMealList;
    private MealViewHolder.ControllerMealItem mControllerMealItem;

    public MealAdapter(List<MealVO> mealList, MealViewHolder.ControllerMealItem controllerMealItem) {
        mInflater = LayoutInflater.from(MealOrderingApp.getContext());
        mMealList = mealList;
        mControllerMealItem = controllerMealItem;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_meal, parent, false);
        return new MealViewHolder(itemView, mControllerMealItem);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        holder.bindData(mMealList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMealList.size();
    }

    public void setNewData(List<MealVO> newMealList) {
        mMealList = newMealList;
        notifyDataSetChanged();
    }
}
