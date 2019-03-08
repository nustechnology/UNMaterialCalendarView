package io.propmap;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GridDateAdapter extends BaseAdapter {
    private List<CalendarCustomObject> dateNumber;
    private Context context;
    private List<Map<String, List<CalendarCustomObject>>> rangeDates;

    GridDateAdapter(Context context, List<CalendarCustomObject> dateNumber, List<Map<String, List<CalendarCustomObject>>> rangeDates) {
        this.dateNumber = dateNumber;
        this.context = context;
        this.rangeDates = rangeDates;
    }

    @Override
    public int getCount() {
        return dateNumber.size();
    }

    @Override
    public Object getItem(int i) {
        return dateNumber.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CalendarCustomObject calendarCustomObject = dateNumber.get(i);
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.item_date, null);
            viewHolder.date = view.findViewById(R.id.dateNumber);
            viewHolder.containerCircle = view.findViewById(R.id.containerCircle);
            viewHolder.containerDate = view.findViewById(R.id.containerDate);
            viewHolder.container = view.findViewById(R.id.container);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (calendarCustomObject != null && calendarCustomObject.getCalendar() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(calendarCustomObject.getCalendar().get(Calendar.YEAR), calendarCustomObject.getCalendar().get(Calendar.MONTH),
                    calendarCustomObject.getCalendar().get(Calendar.DATE));
            viewHolder.date.setText(String.valueOf(calendar.get(Calendar.DATE)));
            setCircle(viewHolder, calendarCustomObject.getCalendar().get(Calendar.DATE));
            Map<String, List<CalendarCustomObject>> map = getCurrentCalendarObjectBelongTo(rangeDates, calendarCustomObject);
            if (map != null) {
                setRoundedBackground(calendarCustomObject, viewHolder,
                        map.get(calendarCustomObject.getType()));
            }
        }
        return view;
    }

    private Map<String, List<CalendarCustomObject>> getCurrentCalendarObjectBelongTo(List<Map<String, List<CalendarCustomObject>>> mapList,
                                                                                     CalendarCustomObject calendarCustomObject) {
        if (calendarCustomObject == null || calendarCustomObject.getType() == null || calendarCustomObject.getType().isEmpty()) {
            return null;
        }
        for (Map<String, List<CalendarCustomObject>> map : mapList) {
            if (calendarCustomObject.getType().equals(Objects.requireNonNull(map.keySet().toArray())[0])) {
                return map;
            }
        }
        return null;
    }

    private void setRoundedBackground(CalendarCustomObject calendarCustomObject, ViewHolder viewHolder, List<CalendarCustomObject> calendarCORange) {
        if (calendarCORange == null || calendarCORange.size() == 0) {
            return;
        }
        if (calendarCORange.size() == 1) {
            viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_rounded));
            return;
        }
        if (DateUtils.checkIfTheFirstDate(calendarCORange, calendarCustomObject.getCalendar())) {
            viewHolder.container.setBackgroundResource(R.drawable.circle_left);
            setColorForBackgroundAndStroke(viewHolder.container, calendarCORange.get(0));
            return;
        }
        if (DateUtils.checkIfTheLastDate(calendarCORange, calendarCustomObject.getCalendar())) {
            viewHolder.container.setBackgroundResource(R.drawable.circle_right);
            setColorForBackgroundAndStroke(viewHolder.container, calendarCORange.get(0));
            return;
        }
        if (calendarCORange.size() >= 3) {
            if (DateUtils.checkIfDateInRange(calendarCORange, calendarCustomObject)) {
                viewHolder.container.setBackgroundResource(R.drawable.square);
                setColorForBackgroundAndStroke(viewHolder.container, calendarCORange.get(0));
            }
        }
    }

    private void setColorForBackgroundAndStroke(View view, CalendarCustomObject calendarCustomObject) {
        if (calendarCustomObject == null) {
            return;
        }
        if (calendarCustomObject.getColorBackground() != null &&
                calendarCustomObject.getColorStroke() != null &&
                calendarCustomObject.getWidthStroke() != null) {
            UIUtils.setColorBackground(view, calendarCustomObject.getColorBackground(), calendarCustomObject.getColorStroke());
        }
    }

    private void setCircle(ViewHolder viewHolder, int compareDate) {
        if (compareDate != DateUtils.getCurrentDate()) {
            return;
        }
        viewHolder.date.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
    }

    private static class ViewHolder {
        TextView date;
        LinearLayout containerCircle;
        FrameLayout containerDate;
        LinearLayout container;
    }
}
