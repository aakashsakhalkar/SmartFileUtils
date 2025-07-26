package com.aakash.timeutils_ak;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static String timeAgo(Date date) {
        long diffInMillis = new Date().getTime() - date.getTime();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
        long hours = TimeUnit.MILLISECONDS.toHours(diffInMillis);
        long days = TimeUnit.MILLISECONDS.toDays(diffInMillis);

        if (seconds < 60) return "just now";
        if (minutes < 60) return minutes + " min ago";
        if (hours < 24) return hours + " hrs ago";
        if (days == 1) return "yesterday";
        return days + " days ago";
    }

    public static boolean isToday(Date date) {
        return isSameDay(date, new Date());
    }

    public static boolean isYesterday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return isSameDay(date, cal.getTime());
    }

    public static boolean isTomorrow(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        return isSameDay(date, cal.getTime());
    }

    public static boolean isSameDay(Date a, Date b) {
        Calendar calA = Calendar.getInstance();
        calA.setTime(a);
        Calendar calB = Calendar.getInstance();
        calB.setTime(b);
        return calA.get(Calendar.YEAR) == calB.get(Calendar.YEAR) &&
                calA.get(Calendar.DAY_OF_YEAR) == calB.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isThisWeek(Date date) {
        Calendar now = Calendar.getInstance();
        Calendar input = Calendar.getInstance();
        input.setTime(date);

        return now.get(Calendar.WEEK_OF_YEAR) == input.get(Calendar.WEEK_OF_YEAR) &&
                now.get(Calendar.YEAR) == input.get(Calendar.YEAR);
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }

    public static String getDayName(Date date) {
        return formatDate(date, "EEEE");
    }

    public static String getMonthName(Date date) {
        return formatDate(date, "MMMM");
    }

    public static String getTimeDiff(Date start, Date end) {
        long diff = Math.abs(end.getTime() - start.getTime());

        long days = TimeUnit.MILLISECONDS.toDays(diff);
        diff -= TimeUnit.DAYS.toMillis(days);

        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        diff -= TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

        return days + "d " + hours + "h " + minutes + "m";
    }

    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static Date getStartOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static int getAge(Date birthDate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }

    public static int[] getAgeBreakdown(Date birthDate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        Calendar today = Calendar.getInstance();

        int years = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        int months = today.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
        int days = today.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH);

        if (days < 0) {
            // Borrow days from previous month
            today.add(Calendar.MONTH, -1);
            days += today.getActualMaximum(Calendar.DAY_OF_MONTH);
            months--;
        }

        if (months < 0) {
            months += 12;
            years--;
        }

        return new int[]{years, months, days};
    }

}
