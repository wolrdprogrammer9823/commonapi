package tool.common_useage_apis;
import android.view.View;

public class ViewMeasureUtils {

    public static void measureWidthAndHeight(View view) {

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }
}
