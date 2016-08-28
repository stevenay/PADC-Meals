package net.naylinaung.meal_ordering.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import net.naylinaung.meal_ordering.MealOrderingApp;
import net.naylinaung.meal_ordering.data.persistence.MealContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 8/18/2016.
 */
public class MealVO {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("img_url")
    private String image_url;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private int price;

    @SerializedName("ingredients")
    private String[] ingredients;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return this.image_url;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public String[] getIngredients() {
        return this.ingredients;
    }

//    public static void saveMeals(List<MealVO> mealList) {
//        Context context = MealOrderingApp.getContext();
//        ContentValues[] mealCVs = new ContentValues[mealList.size()];
//        for (int index = 0; index < mealList.size(); index++) {
//            MealVO meal = mealList.get(index);
//            mealCVs[index] = meal.parseToContentValues();
//
//            //Bulk insert into attraction_images.
//            MealVO.saveMealIngredients(meal.getName(), meal.getIngredients());
//        }
//
//        //Bulk insert into attractions.
//        int insertedCount = context.getContentResolver().bulkInsert(MealContract.MealEntry.CONTENT_URI, mealCVs);
//
//        Log.d(MealOrderingApp.TAG, "Bulk inserted into attraction table : " + insertedCount);
//    }

//    private static void saveMealIngredients(String name, String[] ingredients) {
//        ContentValues[] mealIngredientsCVs = new ContentValues[ingredients.length];
//        for (int index = 0; index < ingredients.length; index++) {
//            String ingredient = ingredients[index];
//
//            ContentValues cv = new ContentValues();
//            cv.put(MealContract.MealIngredientEntry.COLUMN_MEAL_NAME, name);
//            cv.put(MealContract.MealIngredientEntry.COLUMN_INGREDIENT, ingredient);
//
//            mealIngredientsCVs[index] = cv;
//        }
//
//        Context context = MealOrderingApp.getContext();
//        int insertCount = context.getContentResolver().bulkInsert(MealContract.MealIngredientEntry.CONTENT_URI, mealIngredientsCVs);
//
//        //Log.d(MyanmarAttractionsApp.TAG, "Bulk inserted into attraction_images table : " + insertCount);
//    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(MealContract.MealEntry.COLUMN_ID, id);
        cv.put(MealContract.MealEntry.COLUMN_NAME, name);
        cv.put(MealContract.MealEntry.COLUMN_DESCRIPTION, description);
        cv.put(MealContract.MealEntry.COLUMN_IMAGE_URL, image_url);
        cv.put(MealContract.MealEntry.COLUMN_PRICE, price);
        return cv;
    }

    public static MealVO parseFromCursor(Cursor data) {
        MealVO meal = new MealVO();
        meal.id = data.getString(data.getColumnIndex(MealContract.MealEntry.COLUMN_ID));
        meal.name = data.getString(data.getColumnIndex(MealContract.MealEntry.COLUMN_NAME));
        meal.description = data.getString(data.getColumnIndex(MealContract.MealEntry.COLUMN_DESCRIPTION));
        meal.image_url = data.getString(data.getColumnIndex(MealContract.MealEntry.COLUMN_IMAGE_URL));
        meal.price = data.getInt(data.getColumnIndex(MealContract.MealEntry.COLUMN_PRICE));
        return meal;
    }

    public static String[] loadMealIngredientsByName(String mealName) {
        Context context = MealOrderingApp.getContext();
        ArrayList<String> ingredients = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(MealContract.MealIngredientEntry.buildMealIngredientUriWithName(mealName),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ingredients.add(cursor.getString(cursor.getColumnIndex(MealContract.MealIngredientEntry.COLUMN_INGREDIENT)));
            } while (cursor.moveToNext());
        }

        String[] ingredientArray = new String[ingredients.size()];
        ingredients.toArray(ingredientArray);
        return ingredientArray;
    }
}
