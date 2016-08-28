package net.naylinaung.meal_ordering.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import net.naylinaung.meal_ordering.MealOrderingApp;

/**
 * Created by Dell on 8/18/2016.
 */
public class MealContract {
    public static final String CONTENT_AUTHORITY = MealOrderingApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MEALS = "meals";
    public static final String PATH_MEAL_INGREDIENTS = "meal_ingredients";
    public static final String PATH_LOGIN_USER = "login_user";

    public static final class MealEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MEALS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEALS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEALS;

        public static final String TABLE_NAME = "meals";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE_URL = "image_url";
        public static final String COLUMN_PRICE = "price";

        public static Uri buildMealUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildMealUriWithName(String mealName) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions?title="Yangon"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_NAME, mealName)
                    .build();
        }

        public static String getNameFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_NAME);
        }
    }

    public static final class MealIngredientEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MEAL_INGREDIENTS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEAL_INGREDIENTS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEAL_INGREDIENTS;

        public static final String TABLE_NAME = "meal_ingredients";

        public static final String COLUMN_MEAL_NAME = "meal_name";
        public static final String COLUMN_INGREDIENT = "ingredient";

        public static Uri buildMealIngredientUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildMealIngredientUriWithName(String mealName) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_MEAL_NAME, mealName)
                    .build();
        }

        public static String getMealNameFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_MEAL_NAME);
        }
    }


}
