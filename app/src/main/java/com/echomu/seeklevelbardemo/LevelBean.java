package com.echomu.seeklevelbardemo;

import android.graphics.drawable.GradientDrawable;

/**
 * author : echoMu
 * date   : 2019/6/10
 * desc   :
 */
public class LevelBean extends BaseLevel {

    private GradientDrawable drawable;

    public LevelBean(int color, String value,String name) {
        super(color, value,name);
        this.drawable=getDrawable(color,color,1,0);
    }

    /**
     * 产生shape类型的drawable
     *
     * @param solidColor
     * @param strokeColor
     * @param strokeWidth
     * @param radius
     * @return
     */
    public static GradientDrawable getDrawable(int solidColor, int strokeColor, int strokeWidth, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(solidColor);
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setCornerRadius(radius);
        return drawable;
    }

}
