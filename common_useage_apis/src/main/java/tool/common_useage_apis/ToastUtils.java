package tool.common_useage_apis;
import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast;

    public static void show(Context context, String content) {

        if (toast == null) {

            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {

            toast.setText(content);
        }

        toast.show();
    }

    public static void show(Context context, @StringRes int resId) {

        show(context, context.getString(resId));
    }
}
