package net.naylinaung.meal_ordering.data.agents.retrofit;

import net.naylinaung.meal_ordering.data.responses.MealListResponse;
import net.naylinaung.meal_ordering.utils.MealOrderingConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Dell on 8/19/2016.
 */
public interface MealApi {

    @FormUrlEncoded
    @POST(MealOrderingConstants.API_GET_MEAL_LIST)
    Call<MealListResponse> loadMeals(
            @Field(MealOrderingConstants.PARAM_ACCESS_TOKEN) String param);

}
