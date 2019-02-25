package tool.common_useage_apis;
import android.content.Context;
import android.support.annotation.StringRes;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

public class CUATextUtil {

    public static final <T extends TextView> String getText(T textView) {

        return textView.getText().toString().trim();
    }

    public static final <T extends EditText> String getText(T editText) {

        return editText.getText().toString().trim();
    }

    public static final  <T extends EditText> boolean isEmpty(T editText) {

        return TextUtils.isEmpty(getText(editText));
    }

    public static final boolean isEmpty(String content) {

        return TextUtils.isEmpty(content);
    }

    public static  final String  getStrContent(Context context,@StringRes int resId) {

        return context.getResources().getString(resId);
    }

    public static final <T extends TextView> int getTextWidth(T textView) {

        return (int) Layout.getDesiredWidth(textView.getText(), textView.getPaint());
    }

    public static final SpannableStringBuilder setDigitColor(Context context, String str, int color) {

        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(str);
        for (int i = 0; i < str.length(); i++) {

            char a = str.charAt(i);
            if (a >= '0' && a <= '9') {

                ssBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(color)), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //ssBuilder.setSpan(new RelativeSizeSpan(3.0f), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return ssBuilder;
    }
}
