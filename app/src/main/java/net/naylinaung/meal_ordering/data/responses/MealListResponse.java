package net.naylinaung.meal_ordering.data.responses;

import com.google.gson.annotations.SerializedName;

import net.naylinaung.meal_ordering.data.vos.MealVO;

import java.util.ArrayList;

/**
 * Created by Dell on 8/18/2016.
 */
public class MealListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("meal_list")
    private ArrayList<MealVO> mealList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<MealVO> getMealList() {
        return mealList;
    }
}
