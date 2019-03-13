package io.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nguyenngoctan44.io.uranashel_multirangecalendarview.CalendarCustomObject;
import nguyenngoctan44.io.uranashel_multirangecalendarview.UNCalendar;
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

        CalendarCustomObject calendarCustomObject = new CalendarCustomObject();
        UNCalendar unCalendar;

        calendarCustomObject.setType(Arrays.asList(types).get(0));
        calendarCustomObject.setColorBackground("#e4fffd");
        calendarCustomObject.setColorStroke("#00aa9c");
        unCalendar = new UNCalendar(2018, 12, 12);
        calendarCustomObject.setUNCalendar(unCalendar);
        calendarCustomObjects.add(calendarCustomObject);


        calendarCustomObject = new CalendarCustomObject();
        calendarCustomObject.setType(Arrays.asList(types).get(0));
        calendarCustomObject.setColorBackground("#e4fffd");
        calendarCustomObject.setColorStroke("#00aa9c");
        unCalendar = new UNCalendar(2019, 1, 30);
        calendarCustomObject.setUNCalendar(unCalendar);
        calendarCustomObjects.add(calendarCustomObject);

        calendarCustomObject = new CalendarCustomObject();
        calendarCustomObject.setType(Arrays.asList(types).get(0));
        calendarCustomObject.setColorBackground("#e4fffd");
        calendarCustomObject.setColorStroke("#00aa9c");
        unCalendar = new UNCalendar(2019, 1, 31);
        calendarCustomObject.setUNCalendar(unCalendar);
        calendarCustomObjects.add(calendarCustomObject);


        calendarCustomObject = new CalendarCustomObject();
        calendarCustomObject.setType(Arrays.asList(types).get(0));
        calendarCustomObject.setColorBackground("#e4fffd");
        calendarCustomObject.setColorStroke("#00aa9c");
        unCalendar = new UNCalendar(2019, 3, 12);
        calendarCustomObject.setUNCalendar(unCalendar);
        calendarCustomObjects.add(calendarCustomObject);


        unMultiRangeCalendarView.setCommonDatesDataInAMonth(calendarCustomObjects);
        unMultiRangeCalendarView.setColorBackgroundCalendar("#ffffff");
        unMultiRangeCalendarView.setTextSize(13);
        unMultiRangeCalendarView.setTextColor("#000000");
        unMultiRangeCalendarView.setStrokeColorCircle("#363636");
        unMultiRangeCalendarView.setVerticalSpacing(-60); // Any value what you want to reduce the spacing between row in gridview
        unMultiRangeCalendarView.build();
    }
}
