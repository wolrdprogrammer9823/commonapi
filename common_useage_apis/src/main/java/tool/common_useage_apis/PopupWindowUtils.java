package tool.common_useage_apis;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

public class PopupWindowUtils {

    public static void initPopupWindow(PopupWindow popupWindow, View parent,
                                       View content, int width, int height, int x, int y) {

        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffff));
        popupWindow.setContentView(content);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(parent, Gravity.CENTER, x, y);
    }
}
