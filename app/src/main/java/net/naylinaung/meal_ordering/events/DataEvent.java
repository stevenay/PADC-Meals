package net.naylinaung.meal_ordering.events;

import net.naylinaung.meal_ordering.data.vos.MealVO;

import java.util.List;

public class DataEvent {
    public static class MealDataLoadedEvent {

        private String extraMessage;
        private List<MealVO> mealList;

        public MealDataLoadedEvent(String extraMessage, List<MealVO> mealList) {
            this.extraMessage = extraMessage;
            this.mealList = mealList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<MealVO> getMealList() {
            return mealList;
        }
    }
}
