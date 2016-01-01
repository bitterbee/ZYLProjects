package cn.bitterbee.zylprojects.application;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;

import cn.bitterbee.zylprojects.common.util.LogUtil;


/**
 * Created by zyl06 on 9/18/15.
 */
public abstract class AppProfile {
    /* package */ static Context sContext;

    public static final Context getContext() {
        return sContext;
    }

    public static final String getPackageName() {
        return sContext.getPackageName();
    }

    public static void gotoBackground(@NonNull Context context) {
        PackageManager pm = context.getPackageManager();
        ResolveInfo homeInfo = pm.resolveActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME), 0);

        ActivityInfo ai = homeInfo.activityInfo;
        Intent startIntent = new Intent(Intent.ACTION_MAIN);
        startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        startIntent.setComponent(new ComponentName(ai.packageName, ai.name));

        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(startIntent);
        } catch (ActivityNotFoundException e) {
            LogUtil.logE(e.getMessage());
        } catch (SecurityException e) {
            LogUtil.logE(e.getMessage());
        }
    }
}
