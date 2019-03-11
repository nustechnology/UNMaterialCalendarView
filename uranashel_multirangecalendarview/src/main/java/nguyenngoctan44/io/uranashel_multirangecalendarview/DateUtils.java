package nguyenngoctan44.io.uranashel_multirangecalendarview;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
     * Get start date of the list not sort yet
     *
     * @param dates
     * @return
     */
    static int getStartDay(List<Date> dates) {
        Collections.sort(dates);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dates.get(0).getTime());
        return calendar.get(Calendar.DATE);
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
            if (calendarCustomObject.getCalendar().get(Calendar.DATE) == calendar.getCalendar().get(Calendar.DATE) &&
                    calendarCustomObject.getCalendar().get(Calendar.MONTH) == calendar.getCalendar().get(Calendar.MONTH) &&
                    calendarCustomObject.getCalendar().get(Calendar.YEAR) == calendar.getCalendar().get(Calendar.YEAR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if equal date
     *
     * @param customObjects
     * @param calendar
     * @return
     */
    static boolean checkIfTheFirstDate(List<CalendarCustomObject> customObjects, Calendar calendar) {
        if (calendar == null || customObjects.get(0).getCalendar() == null) {
            return false;
        }
        List<CalendarCustomObject> calendarCustomObjects = getFirstAvailabilityData(customObjects);

        return calendarCustomObjects.get(0).getCalendar().get(Calendar.DATE) == (calendar.get(Calendar.DATE)) &&
                calendarCustomObjects.get(0).getCalendar().get(Calendar.MONTH) == (calendar.get(Calendar.MONTH)) &&
                calendarCustomObjects.get(0).getCalendar().get(Calendar.YEAR) == (calendar.get(Calendar.YEAR));
    }

    private static List<CalendarCustomObject> getFirstAvailabilityData(List<CalendarCustomObject> calendarCustomObjects) {
        List<CalendarCustomObject> calendarCustomObjects1 = new ArrayList<>();
        for (CalendarCustomObject calendarCustomObject : calendarCustomObjects) {
            if (calendarCustomObject.getCalendar() != null) {
                calendarCustomObjects1.add(calendarCustomObject);
            }
        }
        return calendarCustomObjects1;
    }

    /**
     * Check if equal date
     *
     * @param calendars
     * @param calendar
     * @return
     */
    static boolean checkIfTheLastDate(List<CalendarCustomObject> calendars, Calendar calendar) {
        if (calendar == null || calendars.get(0) == null) {
            return false;
        }
        List<CalendarCustomObject> calendarCustomObjects = getFirstAvailabilityData(calendars);
        if (calendarCustomObjects.size() > 0) {
            return calendarCustomObjects.get(calendarCustomObjects.size() - 1).getCalendar().get(Calendar.DATE) == (calendar.get(Calendar.DATE)) &&
                    calendarCustomObjects.get(calendarCustomObjects.size() - 1).getCalendar().get(Calendar.MONTH) == (calendar.get(Calendar.MONTH)) &&
                    calendarCustomObjects.get(calendarCustomObjects.size() - 1).getCalendar().get(Calendar.YEAR) == (calendar.get(Calendar.YEAR));
        }
        return false;
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

