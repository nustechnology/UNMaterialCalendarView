package io.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import nguyenngoctan44.io.uranashel_multirangecalendarview.CalendarCustomObject;
import nguyenngoctan44.io.uranashel_multirangecalendarview.UNMultiRangeCalendarView;


public class MainActivity extends AppCompatActivity {
    private UNMultiRangeCalendarView unMultiRangeCalendarView;
    private String[] types = new String[]{"SICK", "BANK", "HOLIDAY", "OTHERS"};
    private String[] colors = new String[]{"#6abd45", "#e4fffd", "#e4fffd", "#6abd45"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);

        unMultiRangeCalendarView = findViewById(R.id.multiRangeUraNashelCalendar);

        List<CalendarCustomObject> calendarCustomObjects = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();

            calendarCustomObject.setType(Arrays.asList(types).get(i));
            calendarCustomObject.setColorBackground("#e4fffd");
            calendarCustomObject.setColorStroke("#00aa9c");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2019);
            calendar.set(Calendar.MONTH, i <= 1 ? 3 : 4);
            calendar.set(Calendar.DATE, i + 1);

            calendarCustomObject.setCalendar(calendar);

            calendarCustomObjects.add(calendarCustomObject);
        }
        unMultiRangeCalendarView.setCommonDatesDataInAMonth(calendarCustomObjects);
        unMultiRangeCalendarView.setColorBackgroundCalendar("#ffffff");
        unMultiRangeCalendarView.setTextSize(13);
        unMultiRangeCalendarView.setTextColor("#000000");
        unMultiRangeCalendarView.setStrokeColorCircle("#363636");
        unMultiRangeCalendarView.build();
    }
}
