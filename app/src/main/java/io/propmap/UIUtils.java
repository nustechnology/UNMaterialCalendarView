package io.propmap;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;

public class UIUtils {
    static void setColorBackground(View view, String colorBackground, String colorStroke) {
        Drawable background = view.getBackground();
        if (background instanceof ShapeDrawable) {
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(Color.parseColor(colorBackground));
        } else if (background instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setColor(Color.parseColor(colorBackground));
            gradientDrawable.setStroke(1, Color.parseColor(colorStroke));
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(Color.parseColor(colorBackground));
        }
    }
}
