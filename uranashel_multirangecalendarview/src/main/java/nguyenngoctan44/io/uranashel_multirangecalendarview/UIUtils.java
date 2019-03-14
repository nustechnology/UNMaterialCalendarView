package nguyenngoctan44.io.uranashel_multirangecalendarview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

class UIUtils {
    static void setColorBackground(View view, String colorBackground, String colorStroke) {
        try {
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
        } catch (Exception e) {
            Log.e("UraNashel Error: ", e.getMessage());
        }
    }
    public static void setMarginForView(View view, int marginSide) {
        if (view instanceof FrameLayout) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
            params.setMargins(marginSide, 0, marginSide, 0);
            view.setLayoutParams(params);
        }
    }
    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}

