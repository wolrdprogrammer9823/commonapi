package tool.common_useage_apis;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Stack;

public class ActivityManager {

    private ActivityManager() {}

    public static <T extends Activity> void startActivity(T activity, Class<? extends Activity> target) {

        Intent intent = new Intent(activity, target);
        activity.startActivity(intent);
    }

    public static <T extends Activity> void startActivityThenFinish(T activity, Class<? extends Activity> target) {

        startActivity(activity, target);
        activity.finish();
    }

    public static void startActivityInNewTask(final Context context, final String pkgName, final String activityName) {


        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(pkgName, activityName);
        intent.setComponent(componentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static <T extends Activity> void startActivityAttachBundle(T activity, Class<? extends Activity> target, Bundle bundle) {

        Intent intent = new Intent(activity, target);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public static <T extends Activity> void addActivity(T t) {

        stack.push(t);
    }

    public static void exitAllActivities() {

        while (!stack.isEmpty()) {

            stack.pop().finish();
        }
    }

    private static Stack<Activity> stack = new Stack<>();
}
