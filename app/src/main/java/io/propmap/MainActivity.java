package io.propmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GridView gridDate;
    private GridDateAdapter dateAdapter;
    private List<CalendarCustomObject> calendars = new ArrayList<>();
    private List<Map<String, List<CalendarCustomObject>>> stringListMap = new ArrayList<>();
    private Calendar currentCalendarForSettingUp = Calendar.getInstance();
    private int MAX_DATE;
    private int FIRST_DAY;
    private Button btnNext, btnPrevious;
    private int currentMonth, currentYear;
    private TextView textTime;

    private UraNashel_MultiRangeCalendar uraNashelMultiRangeCalendar;
    private String[] types = new String[]{"SICK", "BANK", "HOLIDAY", "OTHERS"};
    private String[] colors = new String[]{"#6abd45", "#e4fffd", "#e4fffd", "#6abd45"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        uraNashelMultiRangeCalendar = findViewById(R.id.multiRangeUraNashelCalendar);

        List<CalendarCustomObject> calendarCustomObjects = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();


            calendarCustomObject.setType(Arrays.asList(types).get(i));
            calendarCustomObject.setColorBackground(Arrays.asList(colors).get(i));
            calendarCustomObject.setColorStroke("#26b3a6");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2019);
            calendar.set(Calendar.MONTH, i <= 1 ? 3 : 4);
            calendar.set(Calendar.DATE, i + 1);

            calendarCustomObject.setCalendar(calendar);

            calendarCustomObjects.add(calendarCustomObject);
        }
        uraNashelMultiRangeCalendar.setCommonDatesDataInAMonth(calendarCustomObjects);
        uraNashelMultiRangeCalendar.build();

    }

    private void initUi() {
        textTime = findViewById(R.id.textTime);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        gridDate = findViewById(R.id.gridDate);

        initData();

        dateAdapter = new GridDateAdapter(getApplicationContext(), calendars, stringListMap);
        gridDate.setAdapter(dateAdapter);
    }

    private void initData() {
        getFirstDateOfMonth();
        setUpDates();
    }

    private void setUpDates() {
        settingDate();
        setupMockMapDate();
        setupMockCalendar();
    }

    private void getFirstDateOfMonth() {
        currentYear = currentCalendarForSettingUp.get(Calendar.YEAR);
        currentMonth = currentCalendarForSettingUp.get(Calendar.MONTH);
    }

    private void settingDate() {
        currentCalendarForSettingUp.set(currentYear,
                currentMonth,
                1);
        MAX_DATE = currentCalendarForSettingUp.getActualMaximum(Calendar.DAY_OF_MONTH);
        FIRST_DAY = DateUtils.getFirstDay(currentMonth, currentYear);
//        Log.d("=====Max & first day & year & month", String.valueOf(MAX_DATE) + " _" + FIRST_DAY + "_" + currentMonth + "_" + currentYear);
        textTime.setText(new DateFormatSymbols().getMonths()[currentMonth] + " " + currentYear);
    }

    private void settingDeltaDates() {
        this.calendars.clear();
        for (int i = 0; i < FIRST_DAY - 1; i++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();
            calendarCustomObject.setColorBackground("#ffffff");
            calendarCustomObject.setType("");
            this.calendars.add(calendarCustomObject);
        }
    }

    private void setupMockCalendar() {
        settingDeltaDates();

        for (int j = 1; j <= MAX_DATE; j++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();
            Calendar newCalendar = Calendar.getInstance();
            newCalendar.set(currentYear, currentMonth, j);
            calendarCustomObject.setCalendar(newCalendar);
            if (j >= 1 && j <= 6) {
                calendarCustomObject.setType("HOLIDAY");
            } else if (j >= 19 && j <= 24) {
                calendarCustomObject.setType("SICK");
            } else if (j >= 12 && j <= 14) {
                calendarCustomObject.setType("OTHERS");
            } else if (j >= 15 && j < 16) {
                calendarCustomObject.setType("BANK");
            } else if (j >= 26 && j < 27) {
                calendarCustomObject.setType("SICK");
            } else if (j >= 29 && j <= 31) {
                calendarCustomObject.setType("SICK");
            } else {
                calendarCustomObject.setType("");
            }
            this.calendars.add(calendarCustomObject);
        }
    }

    private void setupMockMapDate() {
        stringListMap.clear();
        stringListMap.add(addNewCalendarCustomObject(1, 6, 3, 2019, "HOLIDAY", "#e4fffd", "#3ab9ad"));
        stringListMap.add(addNewCalendarCustomObject(19, 24, 3, 2019, "SICK", "#fff5e6", "#ff5a3c"));
        stringListMap.add(addNewCalendarCustomObject(29, 31, 3, 2019, "SICK", "#fff5e6", "#ff5a3c"));
        stringListMap.add(addNewCalendarCustomObject(1, 3, 4, 2019, "SICK", "#fff5e6", "#ff5a3c"));
        stringListMap.add(addNewCalendarCustomObject(26, 26, 3, 2019, "SICK", "#fff5e6", "#ff5a3c"));
        stringListMap.add(addNewCalendarCustomObject(12, 14, 3, 2019, "OTHERS", "#ebebeb", "#838383"));
        stringListMap.add(addNewCalendarCustomObject(15, 15, 3, 2019, "BANK", "#e67b4e", "#d64d55"));
    }

    private Map<String, List<CalendarCustomObject>> addNewCalendarCustomObject(int begin, int end, int month, int year, String type,
                                                                               String colorBackground, String colorStroke) {
        Map<String, List<CalendarCustomObject>> map = new HashMap<>();
        List<CalendarCustomObject> calendarCustomObjects = new ArrayList<>();
        for (int i = begin; i <= end; i++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();

            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(year, month - 1, i);

            calendarCustomObject.setColorBackground(colorBackground);
            calendarCustomObject.setColorStroke(colorStroke);
            calendarCustomObject.setWidthStroke(5);
            calendarCustomObject.setType(type);
            calendarCustomObject.setCalendar(calendar1);
            calendarCustomObjects.add(calendarCustomObject);
        }
        map.put(type, calendarCustomObjects);
        return map;
    }
}
