# CustomCalendarView
Hi everyone, this is the first library support multirange for UNCalendar view.

This will be updated more function in the nearest future. Dont be messed up with multirange, will be cool!

How to install:

***implementation 'com.github.ngoctan95:CustomCalendarView:{lastest-version}'***
    
How to use this lib:
    
    ***In xml file***:
        <nguyenngoctan44.io.uranashel_multirangecalendarview.UNMultiRangeCalendarView
            android:id="@+id/calenderView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
    ***In class***:
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);

        UNMultiRangeCalendarView unMultiRangeCalendarView;
        String[] types = new String[]{"Who", "Are", "You", "Guys"};
        String[] colors = new String[]{"#6abd45", "#e4fffd", "#e4fffd", "#6abd45"};

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

***Note: Do not use the color format with 3 characters. Let use with full 6 characters, like here: #xxxxxx, not #xxx
