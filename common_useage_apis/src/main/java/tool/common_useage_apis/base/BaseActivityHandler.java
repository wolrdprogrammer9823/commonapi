package tool.common_useage_apis.base;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class BaseActivityHandler<T extends Activity> extends Handler {

    public final WeakReference<T> activityWeakReference;

    protected BaseActivityHandler(T activity) {

        activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {

        super.handleMessage(msg);
        Activity activity = activityWeakReference.get();
        if (activity != null) {

            toHandMessage(msg);
        }
    }

    public void sendMessage(int what, int param1, int param2) {

        Message message = new Message();
        message.what = what;
        message.arg1 = param1;
        message.arg2 = param2;
        this.sendMessageDelayed(message, 0);
    }

    public void sendMessage(int what, int[] params) {

        Message message = new Message();
        message.what = what;
        message.obj = params;
        this.sendMessageDelayed(message, 0);
    }

    public void sendMessage(int what, Object[] params) {

        Message message = new Message();
        message.what = what;
        message.obj = params;
        this.sendMessageDelayed(message, 0);
    }

    public void sendMessage(int what, int param1) {

        Message message = new Message();
        message.what = what;
        message.arg1 = param1;
        this.sendMessageDelayed(message, 0);
    }

    public void sendMessage(int what) {

        Message message = new Message();
        message.what = what;
        this.sendMessageDelayed(message, 0);
    }

    public void onDestroy() {

        activityWeakReference.clear();
    }

    protected abstract void toHandMessage(Message message);
}
