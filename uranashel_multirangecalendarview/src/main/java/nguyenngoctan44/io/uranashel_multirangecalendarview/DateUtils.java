package nguyenngoctan44.io.uranashel_multirangecalendarview;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

class DateUtils {
    /**
     * Get current time
     *
     * @return
     */
    static Calendar getCurrentDate() {
        return Calendar.getInstance();
    }

    /**
     * Check if dates contains one date
     *
     * @param calendar
     * @param calendars
     * @return
     */
    static boolean checkIfDateInRange(List<CalendarCustomObject> calendars, CalendarCustomObject calendar) {
        for (CalendarCustomObject calendarCustomObject : calendars) {
            if (calendarCustomObject.getUNCalendar().getDate() == calendar.getUNCalendar().getDate() &&
                    calendarCustomObject.getUNCalendar().getMonth() - 1 == (calendar.getUNCalendar().getMonth()) &&
                    calendarCustomObject.getUNCalendar().getYear() == calendar.getUNCalendar().getYear()) {
                return true;
            }
        }
        return false;
    }

    private static List<CalendarCustomObject> getFirstAvailabilityData(List<CalendarCustomObject> calendarCustomObjects) {
        List<CalendarCustomObject> calendarCustomObjects1 = new ArrayList<>();
        for (CalendarCustomObject calendarCustomObject : calendarCustomObjects) {
            if (calendarCustomObject.getUNCalendar() != null) {
                calendarCustomObjects1.add(calendarCustomObject);
            }
        }
        return calendarCustomObjects1;
    }

    /**
     * Get first day of month is tuesday or...
     *
     * @return
     */
    static int getFirstDay(int month, int year) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        return c.get(Calendar.DAY_OF_WEEK);
    }

}

