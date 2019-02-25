package tool.common_useage_apis;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;

public class BroadcastManager {

    private Context context;
    private static volatile BroadcastManager instance;
    private Map<String, BroadcastReceiver> receiverMaps = new HashMap<>(16);

    private BroadcastManager(Context context) {

        this.context = context.getApplicationContext();
    }

    public static BroadcastManager getInstance(Context context) {

        if (instance == null) {

            synchronized (BroadcastManager.class) {

                instance = new BroadcastManager(context);
            }
        }

        return instance;
    }

    public void addAction(String action, BroadcastReceiver broadcastReceiver) {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        context.registerReceiver(broadcastReceiver, intentFilter);
        receiverMaps.put(action, broadcastReceiver);
    }

    public void addAction(String[] actions, BroadcastReceiver broadcastReceiver) {

        IntentFilter intentFilter = new IntentFilter();

        for (String action : actions) {

            intentFilter.addAction(action);
            receiverMaps.put(action, broadcastReceiver);
        }

        context.registerReceiver(broadcastReceiver, intentFilter);
    }
    
    public void sendBroadcast(String action, Bundle bundle) {

        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
    }

    public void sendBroadcast(String action, String key, String value) {

        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra(key, value);
        context.sendBroadcast(intent);
    }

    public void sendBroadcast(String action, String key, int value) {

        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra(key, value);
        context.sendBroadcast(intent);
    }

    public void sendBroadcast(String action, String key, int[] value) {

        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra(key, value);
        context.sendBroadcast(intent);
    }

    public void sendBroadcast(String action) {

        Intent intent = new Intent();
        intent.setAction(action);
        context.sendBroadcast(intent);
    }

    public void destroy(String action) {

        if (receiverMaps != null) {

            BroadcastReceiver broadcastReceiver = receiverMaps.get(action);
            context.unregisterReceiver(broadcastReceiver);
        }
    }

}
