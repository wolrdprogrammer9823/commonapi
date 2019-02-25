package tool.common_useage_apis.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import tool.common_useage_apis.ActivityManager;
import tool.common_useage_apis.R;

public abstract class BaseActivity extends AppCompatActivity
                                   implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onClick(View view) {}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == keyCode) {

            if (System.currentTimeMillis() - exitTime > 2000) {

                Toast.makeText(this, R.string.exit_app, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {

                ActivityManager.exitAllActivities();
                System.exit(0);
        }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private long exitTime = 0;
}
