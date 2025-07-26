package com.aakash.timemodule;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakash.timeutils_ak.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity11";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            // 1 minute ago
            Date oneMinuteAgo = new Date(System.currentTimeMillis() - 60000);
            String timeAgo = TimeUtils.timeAgo(oneMinuteAgo);
            Log.d(TAG, "Time ago for 1 minute ago: " + timeAgo);

            // 1 day ago
            Date oneDayAgo = new Date(System.currentTimeMillis() - 86400000);
            boolean isYesterday = TimeUtils.isYesterday(oneDayAgo);
            Log.d(TAG, "Is yesterday for 1 day ago: " + isYesterday);

            // Day name today
            Date today = new Date();
            String dayName = TimeUtils.getDayName(today);
            Log.d(TAG, "Day name for today: " + dayName);

            // Formatted date
            String formattedDate = TimeUtils.formatDate(today, "dd MMM yyyy hh:mm:ss");
            Log.d(TAG, "Formatted date: " + formattedDate);

            // Month name
            String monthName = TimeUtils.getMonthName(today);
            Log.d(TAG, "Month name: " + monthName);

            // Age calculation
            @SuppressLint("SimpleDateFormat") Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse("1999-01-01");
            int age = TimeUtils.getAge(birthDate);
            Log.d(TAG, "Age for birth date 1999-01-01: " + age);

            //Age Breakdown
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date dob = sdf.parse("1999-03-15");

            int[] AgeBreakdown = TimeUtils.getAgeBreakdown(dob);
            Log.d(TAG, "AgeBreakdown: " + AgeBreakdown[0] + " years, " + AgeBreakdown[1] + " months, " + AgeBreakdown[2] + " days");

            // Time difference between two dates
            Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse("2023-01-01 08:00:00");
            Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse("2023-01-03 10:30:00");
            String timeDiff = TimeUtils.getTimeDiff(start, end);
            Log.d(TAG, "Time diff: " + timeDiff); // Expected: "2d 2h 30m"

            // isToday
            boolean todayCheck = TimeUtils.isToday(new Date());
            Log.d(TAG, "Is today: " + todayCheck);

            // isTomorrow
            Calendar tomorrowCal = Calendar.getInstance();
            tomorrowCal.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = tomorrowCal.getTime();
            boolean isTomorrow = TimeUtils.isTomorrow(tomorrow);
            Log.d(TAG, "Is tomorrow: " + isTomorrow);

            // isSameDay
            boolean sameDayCheck = TimeUtils.isSameDay(new Date(), new Date());
            Log.d(TAG, "Is same day: " + sameDayCheck); // Expected: true

            // isThisWeek
            Calendar thisWeekDate = Calendar.getInstance();
            thisWeekDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Some date in this week
            boolean thisWeek = TimeUtils.isThisWeek(thisWeekDate.getTime());
            Log.d(TAG, "Is this week: " + thisWeek); // Expected: true

            // Week of year
            int weekOfYear = TimeUtils.getWeekOfYear(today);
            Log.d(TAG, "Week of year: " + weekOfYear);

            // Add days
            Date threeDaysLater = TimeUtils.addDays(today, 3);
            Log.d(TAG, "3 days later: " + threeDaysLater.toString());

            // Start of day
            Date startOfDay = TimeUtils.getStartOfDay(today);
            Log.d(TAG, "Start of today: " + startOfDay.toString());

            // End of day
            Date endOfDay = TimeUtils.getEndOfDay(today);
            Log.d(TAG, "End of today: " + endOfDay.toString());

        } catch (Exception e) {
            Log.e(TAG, "Error during TimeUtils testing", e);
        }
    }
}