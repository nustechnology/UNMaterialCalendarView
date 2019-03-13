# CustomCalendarView
Hi everyone, this is the first library support multirange for calendar view.

This will be updated more function in the nearest future. Dont be messed up with multirange, will be cool!

How to install:

***implementation 'com.github.ngoctan95:CustomCalendarView:{version}'***
    
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

        unMultiRangeCalendarView = findViewById(R.id.multiRangeUraNashelCalendar);

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
        unMultiRangeCalendarView.setCommonDatesDataInAMonth(calendarCustomObjects);
        unMultiRangeCalendarView.setColorBackgroundCalendar("#ffffff");
        unMultiRangeCalendarView.setTextSize(13);
        unMultiRangeCalendarView.setTextColor("#000000");
        unMultiRangeCalendarView.setStrokeColorCircle("#363636");
        unMultiRangeCalendarView.build();

    }
