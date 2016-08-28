package net.naylinaung.meal_ordering.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import net.naylinaung.meal_ordering.MealOrderingApp;

/**
 * Created by Dell on 8/27/2016.
 */
public class MMFontUtils {

    private static Typeface mmTypeFace;

    static {
        Context context = MealOrderingApp.getContext();
        mmTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Zawgyi.ttf");
    }

    public static void setMMFont(TextView view) {
        view.setTypeface(mmTypeFace);
    }


}