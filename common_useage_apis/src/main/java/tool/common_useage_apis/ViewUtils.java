package tool.common_useage_apis;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ViewUtils {

    public static final View findView(Context context, @LayoutRes int layoutId) {

        LinearLayout linearLayout = new LinearLayout(context);
        return LayoutInflater.from(context).inflate(layoutId, linearLayout, false);
    }

    public static final <T extends View> T findView(View view, @IdRes int resId) {

        return view.findViewById(resId);
    }

    public static final <T extends ViewGroup> View findView(Context context, T t, @LayoutRes int layoutId) {

        return LayoutInflater.from(context).inflate(layoutId, t, false);
    }

    public static final void addView(ConstraintLayout constraintLayout, View view) {

        constraintLayout.removeAllViews();
        int width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        int height = ConstraintLayout.LayoutParams.MATCH_PARENT;
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(width, height);
        constraintLayout.addView(view, layoutParams);
    }

    public static void addViewWMHW(ConstraintLayout constraintLayout, View view,int topMargin) {

        constraintLayout.removeAllViews();
        int width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(width, height);
        layoutParams.topMargin = topMargin;
        constraintLayout.addView(view, layoutParams);
    }

    public static void addViewWMHW(ConstraintLayout constraintLayout, View view) {

        constraintLayout.removeAllViews();
        int width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(width, height);
        constraintLayout.addView(view, layoutParams);
    }

    public static final void addViewWrapContent(ConstraintLayout constraintLayout, View view) {

        constraintLayout.removeAllViews();
        int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(width, height);
        layoutParams.topToTop = 0;
        layoutParams.bottomToBottom = 0;
        constraintLayout.addView(view, layoutParams);
    }

    public static final void addView(ConstraintLayout constraintLayout, View view, int width) {

        constraintLayout.removeAllViews();
        width = DpSpPxUtils.px2dp(constraintLayout.getContext(), width);
        int height = ConstraintLayout.LayoutParams.MATCH_PARENT;
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(width, height);
        constraintLayout.addView(view, layoutParams);
    }

    public static <T extends ViewGroup> void addView(T t, View view) {

        t.removeAllViews();
        int width = T.LayoutParams.MATCH_PARENT;
        int height = T.LayoutParams.MATCH_PARENT;
        T.LayoutParams layoutParams = new T.LayoutParams(width, height);
        t.addView(view, layoutParams);
    }
}
