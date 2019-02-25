package tool.common_useage_apis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

public class DateUtils {

    private static Calendar calendar;
    private static HashMap<String, String> date2WeekMaps;

    static {

        calendar = Calendar.getInstance();

        date2WeekMaps = new HashMap<>(16);

        for (int i = 0; i < CommonConstant.DateFiled.CHINA_WEEK.length; i++) {

            date2WeekMaps.put(CommonConstant.DateFiled.ENGLISH_WEEK[i], CommonConstant.DateFiled.CHINA_WEEK[i]);
        }
    }

    /*年*/
    public static int getYear() {

        return calendar.get(Calendar.YEAR);
    }

    /*月*/
    public static int getMonth() {

        return calendar.get(Calendar.MONTH) + 1;
    }

    /*日*/
    public static int getDay() {

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /*小时*/
    public static int getHour() {

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /*分钟*/
    public static String getMin() {

        int minute = calendar.get(Calendar.MINUTE);
        String strMinute = String.valueOf(minute);
        if (minute < 10) {

            strMinute = "0" + minute;
        }
        return strMinute;
    }

    /*字符串转日期*/
    public static Date parseStr2Date(String dateStr) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Date date = null;
        try {

            date = simpleDateFormat.parse(dateStr);
            /*严格指定时间日志格式*/
            simpleDateFormat.setLenient(false);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return date;
    }

    public static Date parseStr2Date(String dateStr,String dateFormat) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());

        Date date = null;
        try {

            date = simpleDateFormat.parse(dateStr);
            /*严格指定时间日志格式*/
            simpleDateFormat.setLenient(false);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return date;
    }

    /*日期转字符串*/
    public static String parseDate2Str(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String parseDate2Str2(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String parseDate2Str(Date date,String dateFormat) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    /*秒转小时分钟*/
    public static String convertSecToHourMinStr(long seconds) {

        long nHour = seconds / 3600;
        long nMin = seconds % 3600;
        nMin = nMin / 60;
        return String.format(Locale.getDefault(), "%02d:%02d", nHour, nMin);
    }

    /*秒转小时分钟秒*/
    public static String convertSecToTimeStr(long seconds) {

        long nHour = seconds / 3600;
        long nMin = seconds % 3600;
        long nSec = nMin % 60;
        nMin = nMin / 60;
        return String.format(Locale.getDefault(),"%02d小时%02d分钟%02d秒", nHour, nMin, nSec);
    }

    /*获取星期*/
    public static String getWeekday(Date date) {

        String[] date2Str = String.valueOf(date).split(" ");
        return date2WeekMaps.get(date2Str[0]);
    }

    /*获取小时分钟*/
    public static String getHourMin(Date date) {

        String[] date2Str = String.valueOf(date).split(" ");
        String[] hourMinArr = date2Str[3].split(":");
        int hour = Integer.parseInt(hourMinArr[0]);
        int minute = Integer.parseInt(hourMinArr[1]);
        return String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
    }

    /*获取年月日时分秒*/
    public static int[] getYearMonthDayHourMinuteSecond() {

        int[] dataSet = new int[6];

        String dateStr = parseDate2Str(new Date());
        String[] dateArr = dateStr.split(" ");
        String[] ymdArr = dateArr[0].split("-");
        String[] hmsArr = dateArr[1].split(":");

        int year = Integer.parseInt(ymdArr[0]);
        int month = Integer.parseInt(ymdArr[1]);
        int day = Integer.parseInt(ymdArr[2]);
        int hour = Integer.parseInt(hmsArr[0]);
        int minute = Integer.parseInt(hmsArr[1]);
        int second = Integer.parseInt(hmsArr[2]);

        dataSet[0] = year;
        dataSet[1] = month;
        dataSet[2] = day;
        dataSet[3] = hour;
        dataSet[4] = minute;
        dataSet[5] = second;

        return dataSet;
    }

    /*划分am pm的小时分钟*/
    public static String getHourMinDivideAmPm(Date date) {

        String[] date2Str = String.valueOf(date).split(" ");
        String[] hourMinArr = date2Str[3].split(":");
        int hour = Integer.parseInt(hourMinArr[0]);
        int minute = Integer.parseInt(hourMinArr[1]);
        if (hour > 12) {

            hour = hour % 12;
        }

        return String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
    }

    /*区分pm am*/
    public static String judgeAmPm() {

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int flag = gregorianCalendar.get(GregorianCalendar.AM_PM);
        if (flag == 0) {

            return CommonConstant.DateFiled.AM;
        } else {

            return CommonConstant.DateFiled.PM;
        }
    }
}
