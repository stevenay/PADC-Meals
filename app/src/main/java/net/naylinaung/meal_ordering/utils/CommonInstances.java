package net.naylinaung.meal_ordering.utils;

import com.google.gson.Gson;

/**
 * Created by Dell on 8/27/2016.
 */
public class CommonInstances {
    private static Gson gson = new Gson();

    public static Gson getGsonInstance() {
        return gson;
    }
}
