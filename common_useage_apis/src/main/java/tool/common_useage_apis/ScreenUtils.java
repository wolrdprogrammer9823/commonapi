package tool.common_useage_apis;
import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenUtils {

    private static DisplayMetrics displayMetrics;

    static {

        displayMetrics = new DisplayMetrics();
    }

    public static <T extends Activity> int[] getScreenWidthHeight(T t) {

        t.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int[] widthHeight = new int[2];
        widthHeight[0] = displayMetrics.widthPixels;
        widthHeight[1] = displayMetrics.heightPixels;
        return widthHeight;
    }

    public static <T extends Activity> float getDensity(T t) {

        t.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

}
