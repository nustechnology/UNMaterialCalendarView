# CustomCalendarView
Hi everyone, this is the first library support multirange for calendar view.
How to install: </br>
   <bold> implementation 'com.github.ngoctan95:CustomCalendarView:{version}'</bold>
    
How to use this lib:
    
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
        unMultiRangeCalendarView.build();

    }
