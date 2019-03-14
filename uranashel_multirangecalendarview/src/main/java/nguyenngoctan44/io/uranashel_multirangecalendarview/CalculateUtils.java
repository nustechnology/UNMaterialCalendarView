package nguyenngoctan44.io.uranashel_multirangecalendarview;

import android.content.Context;
import android.util.DisplayMetrics;

public class CalculateUtils {
    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
