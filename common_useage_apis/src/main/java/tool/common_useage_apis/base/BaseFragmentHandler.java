package tool.common_useage_apis.base;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

public abstract class BaseFragmentHandler<T extends Fragment> extends Handler {

    public final WeakReference<T> fragmentWeakReference;

    protected BaseFragmentHandler(T t) {

        fragmentWeakReference = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {

        super.handleMessage(msg);
        Fragment fragment = fragmentWeakReference.get();
        if (fragment != null) {

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

    public void sendMessage(int what, long delayTime) {

        Message message = new Message();
        message.what = what;
        this.sendMessageDelayed(message, delayTime);
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

    public void sendMessage(int what, boolean bool) {

        Message message = new Message();
        message.what = what;
        message.obj = bool;
        this.sendMessageDelayed(message, 0);
    }

    public void sendMessage(int what) {

        Message message = new Message();
        message.what = what;
        this.sendMessageDelayed(message, 0);
    }

    public void sendMessageDelayedAtTime(int what, long delayedTime) {

        Message message = new Message();
        message.what = what;
        this.sendMessageDelayed(message, delayedTime);
    }

    public void onDestroy() {

        fragmentWeakReference.clear();
    }

    protected abstract void toHandMessage(Message message);
}
