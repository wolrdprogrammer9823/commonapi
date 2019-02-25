package tool.common_useage_apis;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtils {

    /*获取应用版本号*/
    public static String obtainAppVersionName(final Context context) {

        String versionName = "";
        PackageManager packageManager = context.getPackageManager();
        try {

            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),PackageInfo.INSTALL_LOCATION_AUTO);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        return versionName;
    }
}
