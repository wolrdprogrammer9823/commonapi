package tool.common_useage_apis;

import java.util.regex.Pattern;

public class RegularExpressionUtils {

    private RegularExpressionUtils() {}

    /*匹配手机号*/
    public static boolean matchPhoneNumber(String phoneNumber) {

        return phoneNumber.matches(Param.PHONE_NUMBER_RE);
    }

    /*匹配正整数*/
    public static boolean matchPositiveDigit(String matchStr) {

        return matchStr.matches(Param.POSITIVE_DIGIT_RE);
    }

    public static boolean matchZeroPositiveDigit(String matchStr) {

        return matchStr.matches(Param.ZERO_DIGIT_STRING_RE);
    }

    public static boolean matchDigitString(String matchStr) {

        return matchStr.matches(Param.DIGIT_STRING_RE);
    }

    public static boolean matchZeroOne(String matchStr) {

        return matchStr.matches(Param.ZERO_ONE_RE);
    }

    public static boolean matchOne2Five(String matchStr) {

        return matchStr.matches(Param.ONE_TO_FIVE_RE);
    }

    public static boolean matchDateStr(String matchStr) {

        return matchStr.matches(Param.DATE_FORMAT_STR_RE);
    }

    public static boolean matchName(String matchStr) {

        return matchStr.matches(Param.NAME_RE);
    }

    public static boolean matchSomeFormatFlag(String matchStr) {

        return matchStr.matches(Param.SOME_FORMAT_FLAG_RE);
    }

    public static boolean matchIP(String matchStr) {

        return matchStr.matches(Param.IP_RE);
    }

    public static boolean matchDigitChineseEnglish(String matchStr) {

        return matchStr.matches(Param.DIGIT_CHINESE_ENGLISH_RE);
    }

    public static boolean matchNormalCharacter(String matchStr) {

        return matchStr.matches(Param.NORMAL_CHARACTER);
    }

    private interface Param {

        String PHONE_NUMBER_RE = "1[3-9]\\d{9}";
        String POSITIVE_DIGIT_RE = "^[1-9]\\d*$";
        String DIGIT_STRING_RE = "\\d+$";
        String ZERO_DIGIT_STRING_RE = "^[1-9]\\d*|0";
        String ZERO_ONE_RE = "^[0-1]";
        String ONE_TO_FIVE_RE = "^[1-5]";
        String SOME_FORMAT_FLAG_RE = "^[A-Z]\\d{3}(-[A-Za-z]\\d{3}){3}$";
        String NAME_RE = "^[\u4e00-\u9fa5]{2,4}|[A-Za-z]{3,}$";
        String DIGIT_CHINESE_ENGLISH_RE = "^[0-9\u4e00-\u9fa5A-Za-z]+$";
        String NORMAL_CHARACTER = "\\w+";
        String DATE_FORMAT_STR_RE = "^[1-2]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])\\s"
                                    + "([0-1]\\d|2[0-3])" + "(:([0-5]\\d)){2}$";

        String IP_RE = "^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]"
                + "|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$";
    }
}
