package io.propmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
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
    private Button btnNext;
    private int currentMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                new SimpleDateFormat("MMMM").format(date));
//            }
//        });
    }

    private void initUi() {
        currentCalendarForSettingUp.set(currentCalendarForSettingUp.get(Calendar.YEAR),
                currentCalendarForSettingUp.get(Calendar.MONTH) - 1,
                1);
        currentMonth = currentCalendarForSettingUp.get(Calendar.MONTH) + 1;
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
            if (j >= 2 && j <= 6) {
                calendarCustomObject.setType("HOLIDAY");
            } else if (j >= 19 && j <= 24) {
                calendarCustomObject.setType("SICK");
            } else if (j >= 12 && j <= 14) {
                calendarCustomObject.setType("OTHERS");
            }
            this.calendars.add(calendarCustomObject);
        }
    }

    private void setupMockMapDate() {
        stringListMap = new ArrayList<>();
        Map<String, List<CalendarCustomObject>> listMap = new HashMap<>();
        stringListMap.add(addNewCalendarCustomObject(2, 6, "HOLIDAY", "#e4fffd", "#3ab9ad"));

        stringListMap.add(addNewCalendarCustomObject(19, 24, "SICK", "#fff5e6", "#ff5a3c"));

        stringListMap.add(addNewCalendarCustomObject(12, 14, "OTHERS", "#ebebeb", "#838383"));
    }

    private Map<String, List<CalendarCustomObject>> addNewCalendarCustomObject(int begin, int end, String type,
                                                                               String colorBackground, String colorStroke) {
        Map<String, List<CalendarCustomObject>> map = new HashMap<>();
        List<CalendarCustomObject> calendarCustomObjects = new ArrayList<>();
        for (int i = begin; i <= end; i++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();

            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), i);

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
