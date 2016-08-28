package net.naylinaung.meal_ordering;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 8/27/2016.
 */
public class MealOrderingApp extends Application {
    public static final String TAG = "MealOrderingApp";

    private static Context context;
    private static Bitmap appIcon;
    private static Bitmap attractionSight;

    public static MealOrderingApp INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        INSTANCE = this;

//        encodeAppIcon();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                encodeAttractionSight();
//            }
//        }, 500);
    }

    public static Context getContext() {
        return context;
    }

    public static Bitmap getAppIcon() {
        return appIcon;
    }

    public static Bitmap getAttractionSight() {
        return attractionSight;
    }

    private void encodeAppIcon() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                //Encode bitmap for large notification icon
                Context context = MealOrderingApp.getContext();
                int largeIconWidth = context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_width);
                int largeIconHeight = context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height);

//                try {
//                    appIcon = Glide.with(context)
//                            .load(R.drawable.)
//                            .asBitmap()
//                            .into(largeIconWidth, largeIconHeight)
//                            .get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
                return null;
            }
        }.execute();
    }
}
