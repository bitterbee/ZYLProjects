package cn.bitterbee.zylprojects.common.util.media;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by zyl06 on 1/11/16.
 */
public class BitmapHelper {
    public static Bitmap drawViewToBitmap(View view, int width, int height, float translateX, float translateY, int downSampling) {
        float scale = 1f / downSampling;

        int bmpWidth = (int) ((width - translateX) * scale);
        int bmpHeight = (int) ((height - translateY) * scale);
        Bitmap dest = Bitmap.createBitmap(bmpWidth, bmpHeight, Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(dest);
        c.translate(-translateX * scale, -translateY * scale);

        if (downSampling > 1) {
            c.scale(scale, scale);
        }
        view.draw(c);

        return dest;
    }
}
