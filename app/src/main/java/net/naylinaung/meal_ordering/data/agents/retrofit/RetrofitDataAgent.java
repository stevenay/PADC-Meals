package net.naylinaung.meal_ordering.data.agents.retrofit;

import net.naylinaung.meal_ordering.data.agents.MealDataAgent;
import net.naylinaung.meal_ordering.data.models.MealModel;
import net.naylinaung.meal_ordering.data.responses.MealListResponse;
import net.naylinaung.meal_ordering.utils.CommonInstances;
import net.naylinaung.meal_ordering.utils.MealOrderingConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 8/19/2016.
 */
public class RetrofitDataAgent implements MealDataAgent {

    private static RetrofitDataAgent objInstance;

    private final MealApi theApi;

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    public RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MealOrderingConstants.ATTRACTION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        this.theApi = retrofit.create(MealApi.class);
    }

    @Override
    public void loadMeals() {
        Call<MealListResponse> loadMealCall = theApi.loadMeals(MealOrderingConstants.ACCESS_TOKEN);
        loadMealCall.enqueue(new Callback<MealListResponse>() {
            @Override
            public void onResponse(Call<MealListResponse> call, Response<MealListResponse> response) {
                MealListResponse mealListResponse = response.body();
                if (mealListResponse == null) {
                    MealModel.getInstance().notifyErrorInLoadingMeals(response.message());
                } else {
                    MealModel.getInstance().notifyMealsLoaded(mealListResponse.getMealList());
                }
            }

            @Override
            public void onFailure(Call<MealListResponse> call, Throwable throwable) {
                MealModel.getInstance().notifyErrorInLoadingMeals(throwable.getMessage());
            }
        });
    }
}
