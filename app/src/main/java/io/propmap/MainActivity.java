package io.propmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GridView gridDate;
    private GridDateAdapter dateAdapter;
    private List<CalendarCustomObject> calendars = new ArrayList<>();
    private List<Map<String, List<CalendarCustomObject>>> stringListMap;
    private Calendar currentCalendarForSettingUp = Calendar.getInstance();
    private int MAX_DATE;
    private int FIRST_DAY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        currentCalendarForSettingUp.set(currentCalendarForSettingUp.get(Calendar.YEAR), currentCalendarForSettingUp.get(Calendar.MONTH)-1, 1);
        MAX_DATE = currentCalendarForSettingUp.getActualMaximum(Calendar.DAY_OF_MONTH);
        FIRST_DAY = DateUtils.getFirstDay();

        setupMockMapDate();
        setupMockCalendar();
        gridDate = findViewById(R.id.gridDate);
        dateAdapter = new GridDateAdapter(getApplicationContext(), calendars, stringListMap);
        gridDate.setAdapter(dateAdapter);
    }

    private void setupMockCalendar() {
        for (int i = 0; i < FIRST_DAY - 1; i++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();
            calendarCustomObject.setColorBackground("#ffffff");
            calendarCustomObject.setType("");
            this.calendars.add(calendarCustomObject);
        }

        for (int j = 1; j <= MAX_DATE; j++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();
            Calendar newCalendar = Calendar.getInstance();
            newCalendar.set(2019, newCalendar.get(Calendar.MONTH), j);
            calendarCustomObject.setCalendar(newCalendar);
            if (j >= 2 && j < 6) {
                calendarCustomObject.setType("HOLIDAY");
            } else if (j >= 19 && j <= 24) {
                calendarCustomObject.setType("SICK");
            }
            this.calendars.add(calendarCustomObject);
        }
    }

    private void setupMockMapDate() {
        stringListMap = new ArrayList<>();

        Map<String, List<CalendarCustomObject>> listMap = new HashMap<>();
        List<CalendarCustomObject> calendars = new ArrayList<>();
        for (int i = 2; i < 6; i++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();

            Calendar newCalendar1 = Calendar.getInstance();
            newCalendar1.set(newCalendar1.get(Calendar.YEAR), newCalendar1.get(Calendar.MONTH), i);

            calendarCustomObject.setColorBackground("#e4fffd");
            calendarCustomObject.setColorStroke("#3ab9ad");
            calendarCustomObject.setWidthStroke(5);
            calendarCustomObject.setCalendar(newCalendar1);
            calendarCustomObject.setType("HOLIDAY");
            calendars.add(calendarCustomObject);
        }
        listMap.put("HOLIDAY", calendars);
        stringListMap.add(listMap);

        Map<String, List<CalendarCustomObject>> listMap1 = new HashMap<>();
        List<CalendarCustomObject> calendars1 = new ArrayList<>();
        for (int i = 19; i < 24; i++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();

            Calendar newCalendar1 = Calendar.getInstance();
            newCalendar1.set(newCalendar1.get(Calendar.YEAR), newCalendar1.get(Calendar.MONTH), i);

            calendarCustomObject.setColorBackground("#fff5e6");
            calendarCustomObject.setColorStroke("#ffa53c");
            calendarCustomObject.setWidthStroke(5);
            calendarCustomObject.setType("SICK");
            calendarCustomObject.setCalendar(newCalendar1);
            calendars1.add(calendarCustomObject);
        }
        listMap1.put("SICK", calendars1);
        stringListMap.add(listMap1);
    }
}
