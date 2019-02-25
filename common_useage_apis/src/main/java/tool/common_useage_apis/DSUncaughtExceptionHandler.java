package tool.common_useage_apis;

import android.content.Context;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/*用于apk上线后捕捉bug信息*/
public class DSUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Context context;
    private String fileDir;
    private String pkgName;
    private String activityName;

    private DSUncaughtExceptionHandler() {}

    public static DSUncaughtExceptionHandler getInstance() {

        return SingletonHolder.DSUNCAUGHTEXCEPTIONHANDLER;
    }

    private static class SingletonHolder {

        private static final DSUncaughtExceptionHandler DSUNCAUGHTEXCEPTIONHANDLER = new DSUncaughtExceptionHandler();
    }

    public void init(Context mContext, String fileDir, String pkgName, String activityName) {

        context = mContext.getApplicationContext();
        this.fileDir = fileDir;
        this.pkgName = pkgName;
        this.activityName = activityName;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable throwable) {

        if (throwable != null) {

            final Writer writer = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(writer);
            throwable.printStackTrace(printWriter);
            Throwable myThrowable = throwable.getCause();
            while (myThrowable != null) {

                myThrowable.printStackTrace(printWriter);
                myThrowable = myThrowable.getCause();
            }

            final String errorStr = writer.toString();

            Log.e("more_log", errorStr);

            FileUtils.closeIO(printWriter);
            FileUtils.closeIO(writer);

            if (errorStr != null) {

                byte[] bytes = null;
                try {

                    bytes = errorStr.getBytes("GBK");
                } catch (UnsupportedEncodingException e) {

                    e.printStackTrace();
                }

                if (bytes != null) {

                    FileUtils.writeFileToSdCard(fileDir, bytes);
                }
            }

            ActivityManager.exitAllActivities();
            /*重启app*/
            ActivityManager.startActivityInNewTask(context, pkgName, activityName);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
