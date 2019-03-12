package nguyenngoctan44.io.uranashel_multirangecalendarview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
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

public class UNMultiRangeCalendarView extends LinearLayout implements View.OnClickListener {
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
    private int textSize = 14;
    private String textColor = "#000000";
    private String strokeColorCircle = "#000000";

    public UNMultiRangeCalendarView(Context context) {
        super(context);
    }

    public UNMultiRangeCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public UNMultiRangeCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet) {
        inflate(context, R.layout.main, this);
        initComponent();
    }

    private void initComponent() {
        textTime = findViewById(R.id.textTime);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        gridDate = findViewById(R.id.gridDate);

        gridDate.setBackgroundColor(Color.parseColor(getColorBackgroundCalendar()));
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

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getStrokeColorCircle() {
        return strokeColorCircle;
    }

    public void setStrokeColorCircle(String strokeColorCircle) {
        this.strokeColorCircle = strokeColorCircle;
    }

    public void build() {
        gridDateAdapter = new GridDateAdapter(getContext(), calendars, stringListMap, getTextColor(), getTextSize(), getStrokeColorCircle());
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

    @SuppressLint("SetTextI18n")
    private void settingDate() {
        currentCalendar.set(getCurrentYear(),
                getCurrentMonth(),
                1);
        MAX_DATE = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        FIRST_DAY = DateUtils.getFirstDay(currentMonth, currentYear);
        textTime.setText(new DateFormatSymbols().getMonths()[currentMonth] + " " + currentYear);
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
        gridDate.setBackgroundColor(Color.parseColor(getColorBackgroundCalendar()));
    }

    @SuppressLint("SetTextI18n")
    public void goToNextMonth() {
        setCurrentMonth(currentMonth + 1);
        if (getCurrentMonth() > 11) {
            setCurrentMonth(0);
            setCurrentYear(currentYear++);
        }
        settingDate();
        setupMockCalendar(customObjectArrayList);

        textTime.setText(new DateFormatSymbols().getMonths()[currentMonth] + "  " + currentYear);
        build();
    }

    @SuppressLint("SetTextI18n")
    public void backToPreviousMonth() {
        setCurrentMonth(currentMonth - 1);
        if (getCurrentMonth() < 0) {
            setCurrentMonth(11);
            setCurrentYear(currentYear--);
        }
        settingDate();
        setupMockCalendar(customObjectArrayList);
        gridDateAdapter = new GridDateAdapter(getContext(), calendars, stringListMap);
        gridDate.setAdapter(gridDateAdapter);
        textTime.setText(new DateFormatSymbols().getMonths()[currentMonth] + "  " + currentYear);
        build();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnNext) {
            goToNextMonth();
        } else if (i == R.id.btnPrevious) {
            backToPreviousMonth();
        }
    }
}
