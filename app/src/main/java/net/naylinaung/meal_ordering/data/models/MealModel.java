package net.naylinaung.meal_ordering.data.models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import net.naylinaung.meal_ordering.data.agents.MealDataAgent;
import net.naylinaung.meal_ordering.data.vos.MealVO;
import net.naylinaung.meal_ordering.events.DataEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.greenrobot.event.EventBus;

/**
 * Created by Dell on 8/18/2016.
 */
public class MealModel extends BaseModel {
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static MealModel objInstance;

    private List<MealVO> mMealList;

    private MealModel() {
        super();
        mMealList = new ArrayList<>();
        loadMeals();
    }

    public static MealModel getInstance() {
        if (objInstance == null) {
            objInstance = new MealModel();
        }
        return objInstance;
    }

    public void loadMeals() {
        dataAgent.loadMeals();
    }

    public List<MealVO> getMealList() {
        return mMealList;
    }

    public MealVO getMealByName(String mealName) {
        for (MealVO meal : mMealList) {
            if (meal.getName().equals(mealName))
                return meal;
        }

        return null;
    }

    public MealVO getMealById(String mealId) {
        for (MealVO meal : mMealList) {
            if (meal.getId().equals(mealId))
                return meal;
        }

        return null;
    }

    public void notifyMealsLoaded(List<MealVO> mealList) {
        //Notify that the data is ready - using LocalBroadcast
        mMealList = mealList;

        //keep the data in persistent layer.
        //MealVO.saveMeals(mMealList);

        broadcastMealLoadedWithEventBus();
        //broadcastAttractionLoadedWithLocalBroadcastManager();
    }

    public void notifyErrorInLoadingMeals(String message) {

    }

    private void broadcastMealLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.MealDataLoadedEvent("extra-in-broadcast", mMealList));
    }

    public void setStoredData(List<MealVO> mealList) {
        this.mMealList = mealList;
    }
}
