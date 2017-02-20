package com.example.wangyeming.androidviewdemo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by wangyeming on 2017/2/14.
 */

public class DisplayUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(final Context context, final float dpValue) {
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        final float scale = metrics.density;
        return (int) ((dpValue * scale) + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(final Context context, final float pxValue) {
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        final float scale = metrics.density;
        return (int) ((pxValue / scale) + 0.5f);
    }

    public static int sp2dip(Context context, float sp) {
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics);
        return px;
    }


}
