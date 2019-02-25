package tool.common_useage_apis;
import android.content.Context;
import android.util.TypedValue;

public class DpSpPxUtils {

    public static int dp2px(Context context, float dpValue) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float spValue) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    public static int px2dp(Context context, float pxValue) {

        float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue * density + 0.5f);
    }

}
