package cn.bitterbee.zylprojects.common.util;

import android.support.annotation.StringRes;

import cn.bitterbee.zylprojects.application.AppProfile;

/**
 * Created by zyl06 on 1/1/16.
 */
public class ResUtil {
    public static String getString(@StringRes int id) {
        return AppProfile.getContext().getString(id);
    }
}
