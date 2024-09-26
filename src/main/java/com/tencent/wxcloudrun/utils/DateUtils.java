package com.tencent.wxcloudrun.utils;

import com.tencent.wxcloudrun.exception.MyForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class DateUtils {
    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    /**
     * 获取当月第一天 00：00：00 结束
     *
     * @return
     */
    public static String getMonth0() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar.getTime());
    }
    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取当天最后一秒时间
     *
     * @return
     */
    public static String getSameDay() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        return df.format(new Date());
    }

    /**
     * 获取前一个月第一天
     */
    public static String getBeforeMonthFirstDay(Date date) {
        //获取时间工具对象
        Calendar calendar = Calendar.getInstance();
        //设置前一个月
        calendar.add(Calendar.MONTH, -1);
        //设置开始时间是00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        //获取这个月最小的一天
        int minimum = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        //放进对象
        calendar.set(Calendar.DAY_OF_MONTH, minimum);
        Date time = calendar.getTime();
        //指定格式输出
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    /**
     * 获取当前时间，yyyy-MM-dd HH:mm:ss
     */
    public static String getYmdHms() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
    /**
     * 获取当前时间，yyyy-MM-dd HH:mm:ss
     */
    public static String getRedis2YmdHms() {
        DateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        return df.format(new Date());
    }

    /**
     * 时间格式化，yyyy-MM-dd HH:mm:ss
     */
    public static String getYmdHms(Date date) {
        if(Objects.isNull(date)){
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 时间格式化，yyyy-MM-dd HH:mm:ss
     */
    public static String getYmdHmsCN(Date date) {
        if(Objects.isNull(date)){
            return null;
        }
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(date);
    }

    /**
     * 时间格式化，yyyy-MM-dd HH:mm:ss
     */
    public static Date getYmdHms(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * redis 临时用
     * 时间格式化，yyyy-MM-dd HH:mm:ss
     */
    public static Date getYmdHms2Reids(String date) {
        try {
            return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期，yyyyMMdd 不带格式的
     */
    public static String getYmd2NoFormat() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    /**
     * 获取当前日期，yyyy-MM-dd
     */
    public static String getYmd() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    /**
     * 日期格式化，yyyy-MM-dd
     */
    public static String getYmd(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 日期格式化，yyyy-MM-dd
     */
    public static Date getYmd(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取本月的天数
     */
    public static int getCurrentMonthDays() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        return aCalendar.getActualMaximum(Calendar.DATE);

    }
    /**
     * 获取环比时间  上月1号 上月底 本月1号 本月底
     * @param startDate
     * @return
     * @throws ParseException
     */
    public static List<String> getLinkRatioDate(String startDate) throws ParseException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        String currentMonthFirstDay = DateUtils.getCurrentMonthFirstDay();
        log.info("当月第一天： " + currentMonthFirstDay);

        Date date = sdf2.parse(startDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        // 当前日
        int currentDay = cal.get(Calendar.DATE);
        log.info("当前 日 ： " + currentDay);

        cal.add(Calendar.MONTH, -1);
//        上个月时间
        String before = sdf.format(cal.getTime());
        String[] split = before.split("-");
        int year = Integer.parseInt(split[0]);
        int month =  Integer.parseInt(split[1]);
        // 上月 总日
        int daysByYearMonth = DateUtils.getDaysByYearMonth(year, month);

        // 上月开始 结束集合
        List<String> monthEnd = new ArrayList<>();


        // 如果今天是 1 号 获取上个月的日期。 上月数据就是 上上月1号 -- 上上月月底 本月数据就是上月1号 到上月月底
        // 如果今天是 31号 上月是30 则获取1-30

        if(currentDay == 1){
            // 获取上上月 的月初和月末
            String format = sdf2.format(getLast2Date(date));
            monthEnd = getMonthEnd(format);

            // 上个月
            Date last1Date = getLast1Date(date);
            List<String> monthEnd1 = getMonthEnd(sdf2.format(last1Date));
            monthEnd.addAll(monthEnd1);

        } else if(currentDay > daysByYearMonth){
            // 当前时间 大于 上月月末
            //  获取上月一号 到月末
            Date last1Date = getLast1Date(date);
            monthEnd = getMonthEnd(sdf2.format(last1Date));

            List<String> monthEnd1 = getMonthEnd(startDate);
            monthEnd.addAll(monthEnd1);
        }else {
            // 获取上月时间
            // 例如 当前2022-05-13  获取 2022-04-01   2022-04-13
            String firstDayOfMonth = before + "-" + "01 00:00:00";
            String lastDayOfMonth = "";
            if(currentDay < 10){
                lastDayOfMonth = before + "-0" + currentDay + " 23:59:59";
            }else {
                lastDayOfMonth = before + "-" + currentDay + " 23:59:59";
            }
            // 上月
            monthEnd.add(firstDayOfMonth);
            monthEnd.add(lastDayOfMonth);

            // 本月
            String format = sdf.format(date);

            String firstDayOfMonth1 = format + "-" + "01 00:00:00";
            String lastDayOfMonth1 = "";
            if(currentDay < 10){
                lastDayOfMonth1 = format + "-0" + currentDay + " 23:59:59";
            }else {
                lastDayOfMonth1 = format + "-" + currentDay + " 23:59:59";
            }
            monthEnd.add(firstDayOfMonth1);
            monthEnd.add(lastDayOfMonth1);

        }
        return monthEnd;
    }

    /**
     * 获取 指定 月初到月末
     * @param dateStr
     * @throws ParseException
     */
    private static List<String> getMonthEnd(String dateStr) throws ParseException {
        List<String> list = new ArrayList<String>();
        // 获取 指定 月初到月末
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cale.setTime(formatter.parse(dateStr));
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        // 当月第一天 2019-02-01
        String firstDayOfMonth = formatter.format(cale.getTime());
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        // 当月最后一天 2019-02-28
        String lastDayOfMonth = formatter.format(cale.getTime());
        list.add(firstDayOfMonth + " 00:00:00");
        list.add(lastDayOfMonth  + " 23:59:59");
        return list;
    }

    /**
     * 获取上月时间
     * @param date
     * @return
     */
    public static Date getLast1Date(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取上上月时间
     * @param date
     * @return
     */
    private static Date getLast2Date(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -2);
        return cal.getTime();
    }

    /**
     * 获得指定日期的前一天
     *
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        return new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(c
                .getTime());
    }
    /**
     * 获得指定日期的前一天
     *
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBeforeYYYYMMDD(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        return new SimpleDateFormat("yyyy-MM-dd").format(c
                .getTime());
    }

    /**
     * 获取本月第一天，yyyy-MM-dd
     */
    public static String getCurrentMonthFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取本月最后一天，yyyy-MM-dd
     */
    public static String getCurrentMonthLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.set(Calendar.DAY_OF_MONTH, calendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar2.getTime());
    }

    /**
     * 获取某个月的第一天，yyyy-MM-dd
     */
    public static String getThisMonthFirstDay(String month) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf1.parse(month));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            return sdf2.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取某个月的最后一天，yyyy-MM-dd
     */
    public static String getThisMOnthLastDay(String month) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf1.parse(month));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            return sdf2.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取该日期往前推多少天的日期，yyyy-MM-dd
     */
    public static String getThisDayBeforeDate(Date date, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -days);
        return sdf.format(calendar.getTime());
    }
    /**
     * 获取该日期往前推多少天的日期，yyyy-MM-dd
     */
    public static Date getThisDayBefore(Date date, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }

    /**
     * 获取该日期往后推多少天的日期，yyyy-MM-dd
     */
    public static String getThisDayAfterDate(Date date, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取本周周一，yyyy-MM-dd
     */
    public static String getCurrentWeekMonday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        return imptimeBegin;
    }

    /**
     * 获取本周周日，yyyy-MM-dd
     */
    public static String getCurrentWeekSunday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.add(Calendar.DATE, 6);
        return sdf.format(cal.getTime());
    }

    /**
     * 补全时间，开始时间
     */
    public static String completionTimeStart(String date) {
        return date + " 00:00:00";
    }

    /**
     * 补全时间，结束时间
     */
    public static String completionTimeEnd(String date) {
        return date + " 23:59:59";
    }

    /**
     * 获取两个时间的时间差
     */
    public static String getDateTimeDiffer(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        return day + "天" + hour + "时" + min + "分";
    }

    /**
     * 获取两个时间的时间差 秒
     */
    public static Integer getDateTimeDiffers(String startDate, String endDate) {
        //long nd = 1000 * 24 * 60 * 60;
        //long nh = 1000 * 60 * 60;
        //long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        Date end = getYmdHms(endDate);
        Date nowDate = getYmdHms(startDate);
        long diff = end.getTime() - nowDate.getTime();
        //// 计算差多少天
        //long day = diff / nd;
        //// 计算差多少小时
        //long hour = diff % nd / nh;
        //// 计算差多少分钟
        //long min = diff % nd % nh / nm;

        int s = (int) (diff / ns);

        return s;
        // 计算差多少秒//输出结果
        //return day + "天" + hour + "时" + min + "分";
    }

    /**
     * 获取两个时间的时间差
     */
    public static long getDateTimeDiffer2Min(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少分钟
        return diff % nm;
    }
    /**
     *
     * @param nowTime   当前时间
     * @param startTime	开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 获取两个时间差的天数
     */
    public static long getDateDayDiffer(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        return diff / nd;
    }

    /**
     * 根据生日算年龄
     */
    private static int getAgeByBirthDay(Date birthday) {
        int age;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            //如果传入的时间，在当前时间的后面，返回0岁
            if (birth.after(now)) {
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取第N天日期
     *
     * @return
     */
    public static String getBeforeDayTime(int dayNum) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, dayNum);
        return sdf.format(cal.getTime());
    }

    /**
     * 前后推N月
     *
     * @return
     */
    public static String getChangeMoth(int monthNum) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthNum);
        return sdf.format(cal.getTime());
    }
    /**
     * 往前推N月
     *
     * @return
     */
    public static String getAgoMoth(Date monthDate, int monthNum) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate);
        cal.add(Calendar.MONTH, -monthNum);
        return sdf.format(cal.getTime());
    }

    /**
     * 前后推N月
     *
     * @return
     */
    public static String getChangeMoth(Date monthDate, int monthNum) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate);
        cal.add(Calendar.MONTH, monthNum);
        return sdf.format(cal.getTime());
    }

    /**
     * 日期格式化，yyyy-MM
     */
    public static Date getDateYm(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期，yyyyMMdd 不带格式的
     */
    public static String getYmdParam(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    /**
     * 获取当前日期，yyyyMM 不带格式的
     */
    public static String getYmParam(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMM");
        return df.format(date);
    }

    /**
     * 补全时间，开始时间
     */
    public static String completMonthFirstDate(String date) {
        return date + "-01 00:00:00";
    }

    /**
     * 前后推N月的最后一天
     *
     * @return
     */
    public static String getMothLastday(int monthNum) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthNum);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return sdf.format(cal.getTime());
    }
    /** 24小时制 yyyyMMddHHmmss */
    public static final String   DATETIMEPATTERN = "yyyyMMdd";

    /**
     * 获取当天开始时间
     * @return
     */
    public static Date getDayBegin(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);//0点
        cal.set(Calendar.MINUTE, 0);//0分
        cal.set(Calendar.SECOND, 0);//0秒
        cal.set(Calendar.MILLISECOND, 0);//0毫秒
        return cal.getTime();
    }


    /**
     * 获取当天结束时间
     * @return
     */
    public static Date getDayEnd(){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);//23点
        cal.set(Calendar.MINUTE, 59);//59分
        cal.set(Calendar.SECOND, 59);//59秒
        return cal.getTime();
    }


    /**
     * 获取昨天开始时间
     * @return
     */
    public static Date getBeginDayOfYesterday(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(getDayBegin());//当天开始时间
        cal.add(Calendar.DAY_OF_MONTH, -1);//当天月份天数减1
        return cal.getTime();
    }


    /**
     * 获取昨天结束时间
     * @return
     */
    public static Date getEndDayOfYesterday(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(getDayEnd());//当天结束时间
        cal.add(Calendar.DAY_OF_MONTH, -1);//当天月份天数减1
        return cal.getTime();
    }


    /**
     * 获取明天开始时间
     * @return
     */
    public static Date getBeginDayOfTomorrow(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(getDayBegin());//当天开始时间
        cal.add(Calendar.DAY_OF_MONTH, 1);//当天月份天数加1
        return cal.getTime();
    }


    /**
     * 获取明天结束时间
     * @return
     */
    public static Date getEndDayOfTomorrow(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(getDayEnd());//当天结束时间
        cal.add(Calendar.DAY_OF_MONTH, 1);//当天月份天数加1
        return cal.getTime();
    }


    /**
     * 获取某个日期的开始时间
     * @param d
     * @return
     */
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar=Calendar.getInstance();
        if(null!=d){
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的开始时间
     * @param d
     * @return
     */
    public static Date getDayStartTimeFormatData(Date d) {
        Calendar calendar=Calendar.getInstance();
        if(null!=d){
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return new Date(new Timestamp(calendar.getTimeInMillis()).getTime());
    }

    /**
     * 获取某个日期的结束时间
     * @param d
     * @return
     */
    public static Date getDayEndTimeFormatData(Date d) {
        Calendar calendar=Calendar.getInstance();
        if(null!=d){
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        return new Date(new Timestamp(calendar.getTimeInMillis()).getTime());
    }

    public static void main(String[] args) {
        //String a = "2022-08-07 10:10:10";
        //Date b = strToDate(a);
        //System.out.println(getFirstOfMonth(new Date()));
        //Date date1 = new Date(2022, 12, 5, 0, 0, 0);
        //Date date2 = new Date(2022, 12, 12, 1, 0, 0);
        //
        //final long dateDayDiffer = countTwoDayWeek(date2, date1);
        //Date date3= new Date(2022, 12, 12, 1, 0, 0);
        //final String week = getWeek("2022-12-12 10:10:10", "0");
        //
        //System.out.println(week);

        Date fa = strToDate("2024-01-20 00:00:00");
        Date h = strToDate("2024-01-19 00:00:00");
        long dateDayDiffer = getDateDayDiffer(fa, h);
        System.out.println(dateDayDiffer);


    }


    /**
     * 获取某个日期的结束时间
     * @param d
     * @return
     */
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar=Calendar.getInstance();
        if(null!=d){
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }


    /**
     * 获取本周的开始时间
     * @return
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfWeek(){
        Date date=new Date();
        if(date==null){
            return null;
        }
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek==1){
            dayOfWeek+=7;
        }
        cal.add(Calendar.DATE, 2-dayOfWeek);
        return getDayStartTime(cal.getTime());
    }


    /**
     * 获取本周的结束时间
     * @return
     */
    public static Date getEndDayOfWeek(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }


    /**
     * 获取上周开始时间
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfLastWeek() {
        Date date=new Date();
        if (date==null) {
            return null;
        }
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int dayofweek=cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek==1) {
            dayofweek+=7;
        }
        cal.add(Calendar.DATE, 2-dayofweek-7);
        return getDayStartTime(cal.getTime());
    }


    /**
     * 获取上周的结束时间
     * @return
     */
    public static Date getEndDayOfLastWeek(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }


    /**
     * 获取今年是哪一年
     * @return
     */
    public static Integer getNowYear(){
        Date date = new Date();
        GregorianCalendar gc=(GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    /**
     * 获取指定时间是哪一年
     * @param date
     * @return
     */
    public static Integer getYear(Date date){
        GregorianCalendar gc=(GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }


    /**
     * 获取本月是哪一月
     * @return
     */
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc=(GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 获取指定时间是哪一月
     * @return
     */
    public static int getMonth(Date date) {
        GregorianCalendar gc=(GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }


    /**
     * 获取本月的开始时间
     * @return
     */
    public static Date getBeginDayOfMonth() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth()-1, 1);
        return getDayStartTime(calendar.getTime());
    }


    /**
     * 获取本月的结束时间
     * @return
     */
    public static Date getEndDayOfMonth() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth()-1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth()-1, day);
        return getDayEndTime(calendar.getTime());
    }


    /**
     * 获取上月的开始时间
     * @return
     */
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth()-2, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取前几个月的开始时间
     * @return
     */
    public static Date getBeginDayOfBeforeMonth(int i) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth()-1-i, 1);
        return getDayStartTime(calendar.getTime());
    }


    /**
     * 获取上月的结束时间
     * @return
     */
    public static Date getEndDayOfLastMonth() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth()-2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth()-2, day);
        return getDayEndTime(calendar.getTime());
    }


    /**
     * 根据指定日期获取前后个月日期（i>0 后几个月  i<0 前几个月）
     * @param date
     * @return
     */
    public static Date getLastDate(Date date,int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }


    /**
     * 获取本年的开始时间
     * @return
     */
    public static Date getBeginDayOfYear() {
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }


    /**
     * 获取本年的结束时间
     * @return
     */
    public static Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }


    /**
     * 两个日期相减得到的天数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDiffDays(Date beginDate, Date endDate) {
        if(beginDate==null||endDate==null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff=(endDate.getTime()-beginDate.getTime())/(1000*60*60*24);
        int days = new Long(diff).intValue();
        return days;
    }


    /**
     * 两个日期相减得到的毫秒数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms=beginDate.getTime();
        long date2ms=endDate.getTime();
        return date2ms-date1ms;
    }


    /**
     * 获取两个日期中的最大日起
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date max(Date beginDate, Date endDate) {
        if(beginDate==null) {
            return endDate;
        }
        if(endDate==null) {
            return beginDate;
        }
        if(beginDate.after(endDate)) {//beginDate日期大于endDate
            return beginDate;
        }
        return endDate;
    }


    /**
     * 获取两个日期中的最小日期
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date min(Date beginDate, Date endDate) {
        if(beginDate==null) {
            return endDate;
        }
        if(endDate==null) {
            return beginDate;
        }
        if(beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }


    /**
     * 获取某月该季度的第一个月
     * @param date
     * @return
     */
    public static Date getFirstSeasonDate(Date date) {
        final int[] season={ 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int sean = season[cal.get(Calendar.MONTH)];
        cal.set(Calendar.MONTH, sean*3-3);
        return cal.getTime();
    }


    /**
     * 返回某个日期下几天的日期
     * @param date
     * @param i
     * @return
     */
    public static Date getNextDay(Date date, int i) {
        Calendar cal=new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE,cal.get(Calendar.DATE)+i);
        return cal.getTime();
    }


    /**
     * 返回某个日期前几天的日期
     * @param date
     * @param i
     * @return
     */
    public static Date getFrontDay(Date date, int i) {
        Calendar cal=new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE)-i);
        return cal.getTime();
    }



    /**
     * 获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
     * @param beginYear
     * @param beginMonth
     * @param k
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal=new GregorianCalendar(beginYear,beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }


    /**
     * 获取某年某月到某年某月按天的切片日期集合（间隔天数的集合）
     * @param beginYear
     * @param beginMonth
     * @param endYear
     * @param endMonth
     * @param k
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getTimeList(int beginYear,int beginMonth,int endYear,int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear==endYear){
            for(int j=beginMonth;j<=endMonth;j++){
                list.add(getTimeList(beginYear,j,k));
            }
        }else{
            {
                for(int j=beginMonth;j<12;j++){
                    list.add(getTimeList(beginYear,j,k));
                }
                for(int i=beginYear+1;i<endYear;i++) {
                    for (int j=0; j<12; j++) {
                        list.add(getTimeList(i,j,k));
                    }
                }
                for (int j=0;j <= endMonth; j++) {
                    list.add(getTimeList(endYear, j, k));
                }
            }
        }
        return list;
    }



    /**
     * 日期加 n 天  n如果为负数，则向前移动n天
     * @param date
     * @return
     */
    public static Date dateAddOne(Date date,int n) {
        Calendar   calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,n); //把日期往后增加一天,整数  往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }

    //=================================时间格式转换==========================

    /**
     * date类型进行格式化输出（返回类型：String）
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {
        if (date == null){
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * date类型进行格式化输出（返回类型：String）
     * @param date
     * @return
     */
    public static String dateFormatByFormat(Date date,String format) {
        if (date == null){
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }


    /**
     * 将"2015-08-31 21:08:06"型字符串转化为Date
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String str){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date) formatter.parse(str);
            return date;
        }catch (Exception e){
            log.error("时间格式转换异常",e);
            throw new MyForbiddenException("时间格式转换异常",500);
        }
    }


    /**
     * 字符串转化为Date,自定义格式
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date StringToDateByFormat(String str,String format){
        try {
            if (!StringUtils.hasText(str)){
                return null;
            }
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date date = (Date) formatter.parse(str);
            return date;
        }catch (Exception e){
            throw new MyForbiddenException("时间格式转换异常",500);
        }
    }


    /**
     * 将CST时间类型字符串进行格式化输出
     * @param str
     * @return
     * @throws ParseException
     */
    public static String CSTFormat(String str) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = (Date) formatter.parse(str);
        return dateFormat(date);
    }



    /**
     * 将long类型转化为Date
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date LongToDare(long str) throws ParseException{
        return new Date(str * 1000);
    }




    //====================================其他常见日期操作方法======================

    /**
     * 判断当前日期是否在[startDate, endDate]区间
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @author jqlin
     * @return
     */
    public static boolean isEffectiveDate(Date startDate, Date endDate){
        if(startDate == null || endDate == null){
            return false;
        }
        long currentTime = new Date().getTime();
        if(currentTime >= startDate.getTime()
                && currentTime <= endDate.getTime()){
            return true;
        }
        return false;
    }


    /**
     * 得到二个日期间的间隔天数
     * @param secondString：后一个日期
     * @param firstString：前一个日期
     * @return
     */
    public static String getTwoDay(String secondString, String firstString) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date secondTime = myFormatter.parse(secondString);
            Date firstTime = myFormatter.parse(firstString);
            day = (secondTime.getTime() - firstTime.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }


    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     * @param StringTime：时间
     * @param minute：分钟（有正负之分）
     * @return
     */
    public static String getPreTime(String StringTime, String minute) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(StringTime);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(minute) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
            return "";
        }
        return mydate1;
    }


    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }



    /**
     * 得到一个时间延后或前移几天的时间
     * @param nowdate：时间
     * @param delay：前移或后延的天数
     * @return
     */
    public static String getNextDay(String nowdate, String delay) {
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        }catch(Exception e){
            return "";
        }
    }


    /**
     * 判断是否闰年
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {
        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0){
            return true;
        }else if ((year % 4) == 0){
            if ((year % 100) == 0){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }


    /**
     * 返回美国时间格式
     * @param str
     * @return
     */
    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }


    /**
     * 判断二个时间是否在同一个周
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if(0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)){
                return true;
            }
        }else if(1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)){
                return true;
            }
        }else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)){
                return true;
            }
        }
        return false;
    }



    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     * @return
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1) {
            week = "0" + week;
        }
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year +"年第"+ week+"周";
    }


    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     * @param sdate：日期
     * @param num：星期几（星期天是一周的第一天）
     * @return
     */
    public static String getWeek(String sdate, String num) {
        // 再转换为时间
        Date dd = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(dd);
        if ("1".equals(num)) // 返回星期一所在的日期
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else if ("2".equals(num)) // 返回星期二所在的日期
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        } else if ("3".equals(num)) // 返回星期三所在的日期
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        } else if ("4".equals(num)) // 返回星期四所在的日期
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        } else if ("5".equals(num)) // 返回星期五所在的日期
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        } else if ("6".equals(num)) // 返回星期六所在的日期
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        } else if ("0".equals(num)) // 返回星期日所在的日期
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }
        return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(c.getTime());
    }


    /**
     * 根据一个日期，返回是星期几的字符串
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }


    /**
     * 根据一个日期，返回是星期几的字符串
     * @param sdate
     * @return
     */
    public static String getWeekStr(String sdate){
        String str = "";
        str = getWeek(sdate);
        if("1".equals(str)){
            str = "星期日";
        }else if("2".equals(str)){
            str = "星期一";
        }else if("3".equals(str)){
            str = "星期二";
        }else if("4".equals(str)){
            str = "星期三";
        }else if("5".equals(str)){
            str = "星期四";
        }else if("6".equals(str)){
            str = "星期五";
        }else if("7".equals(str)){
            str = "星期六";
        }
        return str;
    }


    /**
     * 两个时间之间的天数
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals("")) {
            return 0;
        }
        if (date2 == null || date2.equals("")) {
            return 0;
        }
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }


    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期
     * @param sdate
     * @return
     */
    public static String getNowMonth(String sdate) {
        // 取该时间所在月的一号
        sdate = sdate.substring(0, 8) + "01";

        // 得到这个月的1号是星期几
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(Calendar.DAY_OF_WEEK);
        String newday = getNextDay(sdate, (1 - u) + "");
        return newday;
    }


    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写
     * @param sformat
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 返回一个i位数的随机数
     * @param i
     * @return
     */
    public static String getRandom(int i) {
        Random jjj = new Random();
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0) {
            return "" +
                    "";
        }
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }


    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
     * @param k：表示是取几位随机数，可以自己定
     * @return
     */
    public static String getNo(int k) {
        return getUserDate("yyyyMMddhhmmss") + getRandom(k);
    }
    /**
     * 返回当前日期字符串
     *
     * @param pattern 日期字符串样式
     * @return
     */
    public static String getCurrentDateString(String pattern) {
        return dateToString(getCurrentDateTime(), pattern);
    }
    /**
     * 根据时间变量返回时间字符串
     *
     * @return 返回时间字符串
     * @param pattern 时间字符串样式
     * @param date 时间变量
     */
    public static String dateToString(Object date,
                                      String pattern) {
        if (date == null || "".equals(pattern)) {
            return null;
        }
        SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
        sfDate.setLenient(false);
        return sfDate.format(date);
    }
    /**
     * 返回当前时间
     *
     * @return 返回当前时间
     */
    public static Date getCurrentDateTime() {
        Calendar calNow = Calendar.getInstance();
        return calNow.getTime();

    }

    /**
     * 获取指定日期所在月的第一天
     * @param date 日期
     * @return 所在月的第一天
     */
    public static Date getFirstOfMonth(Date date){
        //获取当前月第一天：
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在月的第最后一天
     * @param date 日期
     * @return  最后一天
     */
    public static Date getLastOfMonth(Date date) {
        //获取当前月最后一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    public static int countTwoDayWeek(Date endDate, Date startDate) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(startDate);
        long time1=cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2=cal.getTimeInMillis();
        long betweenDays =(time2-time1)/(1000*3600*24);
        double days=Double.parseDouble(String.valueOf(betweenDays));
        if((days/7)>0 && (days/7)<=1){
            //不满一周的按一周算
            return 1;
        }else if(days/7>1){
            int day= (int) days;
            if(day%7>0){
                return day/7+1;
            }else{
                return day/7;
            }
        }else if((days/7)==0){
            return 0;
        }else{
            //负数返还0
            return 0;
        }
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取当前日期，MM-dd
     */
    public static String getMd() {
        DateFormat df = new SimpleDateFormat("MM-dd");
        return df.format(new Date());
    }


}
