package cn.bitterbee.zylprojects.common.util.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;

/**
 * Created by zyl06 on 12/14/15.
 */
public class ImageGrayUtil {
    // 给出最终求和时的加权因子(为调整亮度)
    public static final float scaleFactor = 0.9f;

    public static Bitmap apply(Context context, Bitmap sentBitmap) {
        Bitmap bitmap = sentBitmap.copy(Bitmap.Config.ARGB_8888, true);

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];

        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        // 卷积内核中各个位置的值
        float kernalValue = 1.0f/9.0f;
        float k00 = kernalValue;
        float k10 = kernalValue;
        float k20 = kernalValue;
        float k01 = kernalValue;
        float k11 = kernalValue;
        float k21 = kernalValue;
        float k02 = kernalValue;
        float k12 = kernalValue;
        float k22 = kernalValue;
//        float k00 = 0.0f;
//        float k10 = 1.0f;
//        float k20 = 0.0f;
//        float k01 = 1.0f;
//        float k11 = -4.0f;
//        float k21 = 1.0f;
//        float k02 = 0.0f;
//        float k12 = 1.0f;
//        float k22 = 0.0f;

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                // 获取卷积内核中各个元素对应像素的颜色值
                int[] p00 = mutiply(getARGB(pix, w, h, i - 1, j - 1), k00);
                int[] p10 = mutiply(getARGB(pix, w, h, i, j - 1), k10);
                int[] p20 = mutiply(getARGB(pix, w, h, i + 1, j - 1), k20);
                int[] p01 = mutiply(getARGB(pix, w, h, i - 1, j), k01);
                int[] p11 = mutiply(getARGB(pix, w, h, i, j), k11);
                int[] p21 = mutiply(getARGB(pix, w, h, i + 1, j), k21);
                int[] p02 = mutiply(getARGB(pix, w, h, i - 1, j + 1), k02);
                int[] p12 = mutiply(getARGB(pix, w, h, i, j + 1), k12);
                int[] p22 = mutiply(getARGB(pix, w, h, i + 1, j + 1), k22);

                int[] pixARGB = add(p00, p10, p20, p01, p11, p21, p02, p12, p22);

                setColor(pix, w, h, i, j, uniform(toGray(pixARGB)));

//                bitmap.setPixel(i,j,Color.argb(pixARGB[0],pixARGB[1],pixARGB[2],pixARGB[3]));
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return bitmap;
    }

    private static int[] argbFromColor(@ColorInt int color) {
        int[] argb = new int[4];
        argb[0] = Color.alpha(color);
        argb[1] = Color.red(color);
        argb[2] = Color.green(color);
        argb[3] = Color.blue(color);

        return argb;
    }

    private static int getColor(int[] pix, int w, int h, int x, int y) {
        if (x < 0 || x > w - 1) return 0;
        if (y < 0 || y > h - 1) return 0;
        return pix[y * w + x];
    }

    private static int[] setColor(int[] pix, int w, int h, int x, int y, int[] argb) {
        if (x < 0 || x > w - 1) return pix;
        if (y < 0 || y > h - 1) return pix;
        pix[y * w + x] = Color.argb(argb[0], argb[1], argb[2], argb[3]);
        return pix;
    }

    private static int[] toGray(int[] argb) {
        int v = 0;
        for (int i=1; i<argb.length; i++) {
            v += argb[i];
        }
        v /= 3;
        return new int[]{v, v, v, v};
    }

    private static int[] getARGB(int[] pix, int w, int h, int x, int y) {
        int color = getColor(pix, w, h, x, y);
        return argbFromColor(color);
    }

    private static int[] mutiply(int[] argb, float factor) {
        for (int i = 0; i < argb.length; i++) {
            argb[i] = (int) (argb[i] * factor);
        }
        return argb;
    }

    private static int[] add(int[]... argbs) {
        int[] result = new int[4];
        for (int i = 0; i < argbs.length; i++) {
            for (int j = 0; j < 4; j++) {
                result[j] += argbs[i][j];
            }
        }

        return result;
    }

    private static int[] uniform(int[] argb) {
        argb[0] = 255;
        for (int i = 0; i < argb.length; i++) {
            if (argb[i] < 0) argb[i] = 0;
            if (argb[i] > 255) argb[i] = 255;

            argb[i] *= scaleFactor;
        }

        return argb;
    }
}
