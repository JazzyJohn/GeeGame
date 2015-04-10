package nstuff.geogame;

import android.app.Application;
import android.content.Context;

/**
 * Created by vania_000 on 25.03.2015.
 */
public class GeoApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
