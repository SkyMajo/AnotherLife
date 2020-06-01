package com.nixo.afterworklife.Utils;

import android.content.Context;
import android.util.Log;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Sustain on 2018/4/9.
 */
public class Commons {
    private static String[] months_31 = {"01", "03", "05", "07", "08", "10", "12"};
    private static String[] months_31_another = {"1", "3", "5", "7", "8", "10", "12"};
    private static String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    /**
     * 获取年份集合
     *
     * @return
     */
    public static List<String> yearList(int count) {
        List<String> mList = new ArrayList<>();
        mList.clear();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        for (int i = year; i < year + count; i++) {
            mList.add(i + "年");
        }
        return mList;
    }

    /**
     * 获取年份集合
     *
     * @return
     */
    public static List<String> yearList(int year, int count) {
        List<String> mList = new ArrayList<>();
        mList.clear();
        for (int i = year; i < year + count; i++) {
            mList.add(i + "年");
        }
        return mList;
    }

    /**
     * 获取年份集合
     *
     * @return
     */
    public static List<String> yearNearbyList(int year, int count) {
        List<String> mList = new ArrayList<>();
        mList.clear();
        for (int i = year - count; i <= year; i++) {
            mList.add(i + "年");
        }
        return mList;
    }

    /**
     * 获取月份
     *
     * @return
     */
    public static List<String> monthList() {
        List<String> mList = new ArrayList<>();
        mList.clear();
        for (int i = 0; i < months.length; i++) {
            mList.add(months[i] + "月");
        }
        return mList;
    }

    /**
     * 显示年
     *
     * @return
     */
    public static int getYear(int count, int year) {
        for (int i = 0; i < yearList(count).size(); i++) {
            if (yearList(count).get(i).equals(year + "年")) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 显示月
     *
     * @return
     */
    public static int getMonth(String month) {
        for (int i = 0; i < monthList().size(); i++) {
            if (monthList().get(i).equals(month)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * @param year  显示的年
     * @param month 显示月
     * @return
     */
    public static List<String> dayList(String year, String month) {
        List<String> mList = new ArrayList<>();
        mList.clear();
        int day;
        if (Integer.parseInt(year) % 4 == 0 || Integer.parseInt(year) % 400 == 0) {
            if (month.equals("02")) {
                day = 29;
            } else {
                if (Arrays.asList(months_31).contains(month)) {
                    day = 31;
                } else {
                    day = 30;
                }
            }
        } else {
            if (month.equals("02")) {
                day = 28;
            } else if (Arrays.asList(months_31).contains(month)) {
                day = 31;
            } else {
                day = 30;
            }
        }
        for (int i = 1; i < day + 1; i++) {
            if (i < 10) {
                mList.add("0" + i + "日");
            } else {
                mList.add(i + "日");
            }

        }
        return mList;
    }

    public static final Date dateFromCommonStr(String stringDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static final String timeToStr(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(time);
    }

    public static final Date timeFromCNStr(String stringTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("H点m分");
        Date time = null;
        try {
            time = sdf.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }


    public static final String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


    public static final Date dateTimeFromStr(String stringDateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = sdf.parse(stringDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }

    public static final String dateTimeToStr(Date dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dateTime);
    }

    public static final Date dateTimeFromCustomStr(String date, String time) {
        Date dateTime = new Date();
        Calendar calendar = Calendar.getInstance();
        //设置day
        if (date.equals("今天")) {

        } else if (date.equals("明天")) {
            calendar.add(Calendar.DATE, 1);
        } else if (date.equals("后天")) {
            calendar.add(Calendar.DATE, 2);
        } else {
            dateTime = dateFromCommonStr(date);
            calendar.setTime(dateTime);
        }

        //设置小时分钟
        Date timeFormat = timeFromCNStr(time);
        try {
            calendar.set(Calendar.HOUR_OF_DAY, timeFormat.getHours());
            calendar.set(Calendar.MINUTE, timeFormat.getMinutes());
            dateTime = calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    public static String showScore(float score) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(score);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //判断两个日期是否在同一天
    public static boolean isInSameDay(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2
                .get(Calendar.DAY_OF_MONTH);
    }

    //判断两个日期是否位于同一小时
    public static boolean isInSameHour(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
                && calendar1.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY);

    }

    /**
     * 显示未来年
     *
     * @return
     */
    public static int getfutureYear(int count, int year) {
        for (int i = 0; i < futureYearList(count).size(); i++) {
            if (futureYearList(count).get(i).equals(year + "")) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 获取未来count年集合
     *
     * @return
     */
    public static List<String> futureYearList(int count) {
        List<String> mList = new ArrayList<>();
        mList.clear();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        for (int i = year; i < year + count; i++) {
            mList.add(i + "年");
        }
        return mList;
    }
    /**
     * 自定义获取从XX年到后面XX年
     * 获取未来count年集合
     *
     * @return
     */
    public static List<String> futureYearList(int year , int count) {
        List<String> mList = new ArrayList<>();
        mList.clear();
        Calendar cal = Calendar.getInstance();
        for (int i = year; i < year + count; i++) {
            mList.add(i + "年");
        }
        return mList;
    }

    /**
     * 获取当前月之后月份
     *
     * @return
     */
    public static List<String> futureMonthList(String year , int month) {
        List<String> mList = new ArrayList<>();
        mList.clear();
        if((year).equals(TimeUtils.INSTANCE.getYEAR()+"年")){
            for (int i = month; i < 13; i++) {
                mList.add(i + "月");
            }
        }else{
            for (int i = 1; i < 13; i++) {
                mList.add(i + "月");
            }
        }

        return mList;
    }

    /**
     * 获取当前天之后天
     */
    public static List<String> futuredayList(String year, String month, int date) {
        List<String> mList = new ArrayList<>();
        mList.clear();
        int day = 30;
        year = year.substring(0, year.length() - 1);
        month = month.substring(0, month.length() - 1);
        Log.e("Nixo",month);
        if (Integer.parseInt(year) % 4 == 0 || Integer.parseInt(year) % 400 == 0) {

            if (month.equals("2")) {
                day = 29;
            } else {
                if (Arrays.asList(months_31_another).contains(month)) {
                    day = 31;
                } else {
                    day = 30;
                }
            }
        } else {
            if (month.equals("2")) {
                day = 28;
            } else if (Arrays.asList(months_31_another).contains(month)) {
                day = 31;
            } else {
                day = 30;
            }
        }
        int i = 0;
        Log.e("Nixo-----",""+ Integer.parseInt(year) +"---"+ Integer.parseInt(TimeUtils.INSTANCE.getYEAR())+"......" +
                "......."+ Integer.parseInt(month)+"----"+ Integer.parseInt(TimeUtils.INSTANCE.getMOUTH()));
        if (Integer.parseInt(year) == Integer.parseInt(TimeUtils.INSTANCE.getYEAR())&&
                Integer.parseInt(month) == Integer.parseInt(TimeUtils.INSTANCE.getMOUTH())
                ) {
            Log.e("Nixo","走了if");
            i = date;
        }else{
            Log.e("Nixo","走了if");
            i = 1;
        }

        for (; i < day + 1; i++) {
            mList.add(i + "日");
        }
        return mList;
    }

}
