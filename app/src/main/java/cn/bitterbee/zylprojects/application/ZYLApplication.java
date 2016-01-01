package cn.bitterbee.zylprojects.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import cn.bitterbee.zylprojects.config.ServerEnvs;

/**
 * Created by zyl06 on 9/18/15.
 */
public class ZYLApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
        AppProfile.sContext = context;

        if (!ServerEnvs.isDebug()) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(context);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
