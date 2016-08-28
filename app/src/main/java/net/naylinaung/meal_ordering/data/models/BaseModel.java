package net.naylinaung.meal_ordering.data.models;

import net.naylinaung.meal_ordering.data.agents.MealDataAgent;
import net.naylinaung.meal_ordering.data.agents.retrofit.RetrofitDataAgent;

import de.greenrobot.event.EventBus;

/**
 * Created by Dell on 8/18/2016.
 */
public class BaseModel {
    private static final int INIT_DATA_AGENT_OFFLINE = 1;
    private static final int INIT_DATA_AGENT_HTTP_URL_CONNECTION = 2;
    private static final int INIT_DATA_AGENT_OK_HTTP = 3;
    private static final int INIT_DATA_AGENT_RETROFIT = 4;

    protected MealDataAgent dataAgent;

    public BaseModel() {
        initDataAgent(INIT_DATA_AGENT_RETROFIT);

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    private void initDataAgent(int initType) {
        switch (initType) {
            case INIT_DATA_AGENT_RETROFIT:
                dataAgent = RetrofitDataAgent.getInstance();
                break;
        }
    }

    public void onEventMainThread(Object obj) {

    }
}
