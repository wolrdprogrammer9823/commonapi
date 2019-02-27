package tool.common_useage_apis;
import android.content.Context;
import android.content.pm.ApplicationInfo;

public class DebugUitls {

    public static Boolean inDebugState;

    public static Boolean getInDebugState() {

        return inDebugState == null ? false : inDebugState.booleanValue();
    }

    public static void syncInDebugState(Context context) {

        if (inDebugState == null) {

            inDebugState = context.getApplicationInfo() != null
                    && ((context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
        }
    }
}
