package io.propmap;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UraNashel_MultiRangeCalendar extends LinearLayout implements View.OnClickListener {
    private Button btnNext, btnPrevious;
    private int currentMonth, currentYear;
    private TextView textTime;
    private GridView gridDate;
    private GridDateAdapter gridDateAdapter;
    private List<CalendarCustomObject> calendars = new ArrayList<>();
    private List<CalendarCustomObject> customObjectArrayList = new ArrayList<>();
    private List<Map<String, List<CalendarCustomObject>>> stringListMap = new ArrayList<>();
    private Calendar currentCalendar = Calendar.getInstance();
    private int MAX_DATE;
    private int FIRST_DAY;
    private String colorBackgroundCalendar = "#FFFFFF";

    public UraNashel_MultiRangeCalendar(Context context) {
        super(context);
    }

    public UraNashel_MultiRangeCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public UraNashel_MultiRangeCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet) {
        inflate(context, R.layout.activity_main, this);
        initComponent();
    }

    private void initComponent() {
        textTime = findViewById(R.id.textTime);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        gridDate = findViewById(R.id.gridDate);

        initData();
        regEvents();
    }

    private void regEvents() {
        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
    }

    public void setCommonDatesDataInAMonth(List<CalendarCustomObject> calendarCustomObjectsHasBeenMarked) {
        customObjectArrayList.clear();
        customObjectArrayList.addAll(calendarCustomObjectsHasBeenMarked);
        //Set string map
        stringListMap.clear();
        for (CalendarCustomObject calendarCustomObject : calendarCustomObjectsHasBeenMarked) {
            Calendar calendar = calendarCustomObject.getCalendar();
            stringListMap.add(addNewCalendarCustomObject(calendarCustomObject.getCalendar().get(Calendar.DATE), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.YEAR), calendarCustomObject.getType(), calendarCustomObject.getColorBackground(), calendarCustomObject.getColorStroke()));
        }
        setupMockCalendar(calendarCustomObjectsHasBeenMarked);
    }

    private void setColorBackground(String color) {
        setColorBackgroundCalendar(color);
    }

    private void settingDeltaDates() {
        this.calendars.clear();
        for (int i = 0; i < FIRST_DAY - 1; i++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();
            calendarCustomObject.setColorBackground(getColorBackgroundCalendar());
            calendarCustomObject.setType("");
            this.calendars.add(calendarCustomObject);
        }
    }

    private void setupMockCalendar(List<CalendarCustomObject> calendarCustomObjectsHasBeenMarked) {
        settingDeltaDates();
        for (int j = 1; j <= MAX_DATE; j++) {
            CalendarCustomObject calendarCustomObject = new CalendarCustomObject();

            Calendar newCalendar = Calendar.getInstance();
            newCalendar.set(currentYear, currentMonth, j);
            calendarCustomObject.setCalendar(newCalendar);
            calendarCustomObject.setType("");
            if (calendarCustomObjectsHasBeenMarked != null) {
                for (CalendarCustomObject calendarCustomObject1 : calendarCustomObjectsHasBeenMarked) {
                    if (j == calendarCustomObject1.getCalendar().get(Calendar.DATE) &&
                            currentMonth == calendarCustomObject1.getCalendar().get(Calendar.MONTH) - 1 &&
                            currentYear == calendarCustomObject1.getCalendar().get(Calendar.YEAR)) {
                        calendarCustomObject.setColorBackground(calendarCustomObject1.getColorBackground());
                        calendarCustomObject.setColorStroke(calendarCustomObject1.getColorStroke());
                        calendarCustomObject.setType(calendarCustomObject1.getType());
                        calendarCustomObject.setWidthStroke(5);
                        break;
                    }
                }
            }

            this.calendars.add(calendarCustomObject);
        }
    }


    public void build() {
        gridDateAdapter = new GridDateAdapter(getContext(), calendars, stringListMap);
        gridDate.setAdapter(gridDateAdapter);
    }

    private void initData() {
        getFirstDateOfMonth();
        setUpDates();
    }

    private void setUpDates() {
        settingDate();
        setupMockCalendar(new ArrayList<CalendarCustomObject>());
    }

    private void getFirstDateOfMonth() {
        setCurrentMonth(currentCalendar.get(Calendar.MONTH));
        setCurrentYear(currentCalendar.get(Calendar.YEAR));
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    private void settingDate() {
        currentCalendar.set(getCurrentYear(),
                getCurrentMonth(),
                1);
        MAX_DATE = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        FIRST_DAY = DateUtils.getFirstDay(currentMonth, currentYear);
        textTime.setText(new DateFormatSymbols().getMonths()[currentMonth] + "-" + currentYear);
    }

    private Map<String, List<CalendarCustomObject>> addNewCalendarCustomObject(int date, int month, int year, String type,
                                                                               String colorBackground, String colorStroke) {
        Map<String, List<CalendarCustomObject>> map = new HashMap<>();
        List<CalendarCustomObject> calendarCustomObjects = new ArrayList<>();
        CalendarCustomObject calendarCustomObject = new CalendarCustomObject();

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(year, month - 1, date);

        calendarCustomObject.setColorBackground(colorBackground);
        calendarCustomObject.setColorStroke(colorStroke);
        calendarCustomObject.setWidthStroke(5);
        calendarCustomObject.setType(type);
        calendarCustomObject.setCalendar(calendar1);
        calendarCustomObjects.add(calendarCustomObject);

        map.put(type, calendarCustomObjects);
        return map;
    }

    public String getColorBackgroundCalendar() {
        return colorBackgroundCalendar;
    }

    public void setColorBackgroundCalendar(String colorBackgroundCalendar) {
        this.colorBackgroundCalendar = colorBackgroundCalendar;
    }

    private void goToNextMonth() {
        setCurrentMonth(currentMonth + 1);
        if (getCurrentMonth() > 11) {
            setCurrentMonth(0);
            setCurrentYear(currentYear++);
        }
        settingDate();
        setupMockCalendar(customObjectArrayList);

        textTime.setText(new DateFormatSymbols().getMonths()[currentMonth] + "-" + currentYear);
        build();
    }

    private void backToPreviousMonth() {
        setCurrentMonth(currentMonth - 1);
        if (getCurrentMonth() < 0) {
            setCurrentMonth(11);
            setCurrentYear(currentYear--);
        }
        settingDate();
        setupMockCalendar(customObjectArrayList);
        gridDateAdapter = new GridDateAdapter(getContext(), calendars, stringListMap);
        gridDate.setAdapter(gridDateAdapter);
        textTime.setText(new DateFormatSymbols().getMonths()[currentMonth] + "-" + currentYear);
        build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext: {
                goToNextMonth();
            }
            break;
            case R.id.btnPrevious: {
                backToPreviousMonth();
            }
            break;
        }
    }
}
