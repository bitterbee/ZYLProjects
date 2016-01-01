package cn.bitterbee.zylprojects.common.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zyl06 on 9/18/15.
 */
public class ToastUtil {
    // 显示短提示
    public static void makeShortToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT);
    }

    // 显示长提示
    public static void makeLongToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_LONG);
    }
}
