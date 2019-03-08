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
    private Map<String, List<CalendarCustomObject>> stringListMap;
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
        currentCalendarForSettingUp.set(2019, currentCalendarForSettingUp.get(Calendar.MONTH), 1);
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
            this.calendars.add(calendarCustomObject);
        }

        for (int j = 1; j <= MAX_DATE; j++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();
            Calendar newCalendar = Calendar.getInstance();
            newCalendar.set(2019, newCalendar.get(Calendar.MONTH), j);
            calendarCustomObject.setCalendar(newCalendar);
            this.calendars.add(calendarCustomObject);
        }
    }

    private void setupMockMapDate() {
        stringListMap = new HashMap<>();
        List<CalendarCustomObject> calendars = new ArrayList<>();
        for (int i = 2; i < 14; i++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();

            Calendar newCalendar1 = Calendar.getInstance();
            newCalendar1.set(newCalendar1.get(Calendar.YEAR), newCalendar1.get(Calendar.MONTH), i);

            calendarCustomObject.setColorBackground("#e4fffd");
            calendarCustomObject.setColorStroke("#3ab9ad");
            calendarCustomObject.setWidthStroke(5);
            calendarCustomObject.setCalendar(newCalendar1);
            calendars.add(calendarCustomObject);
        }
        stringListMap.put("DEMO", calendars);
    }
}
