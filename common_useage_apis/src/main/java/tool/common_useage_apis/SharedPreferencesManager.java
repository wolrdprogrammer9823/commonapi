package tool.common_useage_apis;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharedPreferencesManager() {}

    public static void init(Context context) {

        sharedPreferences = context.getSharedPreferences(CommonConstant.SharedPreferenceField.SHARED_PREFERENCES_DATA, Context.MODE_PRIVATE);
    }

    public static void putBoolean(String key, boolean value) {

        editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean getBoolean(String key) {

        return sharedPreferences.getBoolean(key, true);
    }

    public static Boolean getBoolean(String key, boolean defValue) {

        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void putInteger(String key, int value) {

        editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInteger(String key) {

        return sharedPreferences.getInt(key, 1);
    }

    public static int getInteger(String key,int defValue) {

        return sharedPreferences.getInt(key, defValue);
    }

    public static void putString(String key, String value) {

        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key) {

        return sharedPreferences.getString(key, "");
    }

    public static String getInteger(String key,String defValue) {

        return sharedPreferences.getString(key, defValue);
    }

}
