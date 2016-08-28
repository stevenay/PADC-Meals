package net.naylinaung.meal_ordering.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.naylinaung.meal_ordering.data.persistence.MealContract;

/**
 * Created by Dell on 8/18/2016.
 */
public class MealDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "meals.db";

    private static final String SQL_CREATE_MEAL_TABLE = "CREATE TABLE " + MealContract.MealEntry.TABLE_NAME + " (" +
            MealContract.MealEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MealContract.MealEntry.COLUMN_ID + " INTEGER NOT NULL, " +
            MealContract.MealEntry.COLUMN_NAME + " TEXT NOT NULL, " +
            MealContract.MealEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
            MealContract.MealEntry.COLUMN_IMAGE_URL + " TEXT NOT NULL, " +
            MealContract.MealEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +

            " UNIQUE (" + MealContract.MealEntry.COLUMN_NAME + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_MEAL_INGREDIENT_TABLE = "CREATE TABLE " + MealContract.MealIngredientEntry.TABLE_NAME + " (" +
            MealContract.MealIngredientEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MealContract.MealIngredientEntry.COLUMN_MEAL_NAME + " TEXT NOT NULL, " +
            MealContract.MealIngredientEntry.COLUMN_INGREDIENT + " TEXT NOT NULL, " +

            " UNIQUE (" + MealContract.MealIngredientEntry.COLUMN_MEAL_NAME + ", " +
            MealContract.MealIngredientEntry.COLUMN_INGREDIENT + ") ON CONFLICT IGNORE" +
            " );";

    public MealDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MEAL_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MEAL_INGREDIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MealContract.MealEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MealContract.MealIngredientEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
