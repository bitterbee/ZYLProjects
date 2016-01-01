package cn.bitterbee.zylprojects.common.util;

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;

/**
 * Created by zyl06 on 1/1/16.
 */
public class ScreenUtil {
    private static int sStatusBarHeight = 0;

    public static int getStatusBarHeight(@NonNull Context context) {
        if (sStatusBarHeight > 0) return sStatusBarHeight;
        try {
            Class c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            sStatusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sStatusBarHeight;
    }
}
