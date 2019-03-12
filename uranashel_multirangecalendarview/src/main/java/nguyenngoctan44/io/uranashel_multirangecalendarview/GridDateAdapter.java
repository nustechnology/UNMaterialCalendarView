package nguyenngoctan44.io.uranashel_multirangecalendarview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GridDateAdapter extends BaseAdapter {
    private List<CalendarCustomObject> dateNumber;
    private Context context;
    private List<Map<String, List<CalendarCustomObject>>> rangeDates;
    private String dateColor = "#000000";
    private int dateSize = 14;
    private String strokeCircleColor = "#000000";

    GridDateAdapter(Context context, List<CalendarCustomObject> dateNumber, List<Map<String, List<CalendarCustomObject>>> rangeDates) {
        this.dateNumber = dateNumber;
        this.context = context;
        this.rangeDates = rangeDates;
    }

    GridDateAdapter(Context context, List<CalendarCustomObject> dateNumber, List<Map<String, List<CalendarCustomObject>>> rangeDates,
                    String dateColor, Integer dateSize, String strokeCircleColor) {
        this.dateNumber = dateNumber;
        this.context = context;
        this.rangeDates = rangeDates;
        this.dateColor = dateColor;
        this.dateSize = dateSize;
        this.strokeCircleColor = strokeCircleColor;
    }

    void onNewTextColorChange(String color) {
        this.dateColor = color;
        this.notifyDataSetChanged();
    }

    void onNewTextSizeChange(int size) {
        this.dateSize = size;
        this.notifyDataSetChanged();
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
            viewHolder.date.setTextSize(dateSize);
            viewHolder.date.setTextColor(Color.parseColor(dateColor));
            setCircle(viewHolder, calendarCustomObject.getCalendar());
            Map<String, List<CalendarCustomObject>> map = getCurrentCalendarObjectBelongTo(rangeDates, calendarCustomObject);
            if (map != null) {
                try {
                    if (DateUtils.checkIfDateInRange(Objects.requireNonNull(map.get(calendarCustomObject.getType())), calendarCustomObject)) {
                        CalendarCustomObject tempCal = Objects.requireNonNull(map.get(calendarCustomObject.getType())).get(0);
                        viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.square));
                        setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                        viewHolder.container.setPadding(0, -15, 0, -15);

                        CalendarCustomObject customObjectNext = (i + 1 >= dateNumber.size() ? null : dateNumber.get(i + 1));
                        CalendarCustomObject customObjectPre = (i - 1 < 0 ? null : dateNumber.get(i - 1));

                        if (customObjectNext == null) {
                            viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_right));
                            setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                        }
                        if (customObjectPre == null) {
                            viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_left));
                            setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                        }

                        if (customObjectNext != null && customObjectPre == null) {
                            if (customObjectNext.getType().equals(calendarCustomObject.getType())) {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.square));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            } else {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_right));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            }
                        }
                        if (customObjectPre != null && customObjectNext == null) {
                            if (customObjectPre.getType().equals(calendarCustomObject.getType())) {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_right));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            } else {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_rounded));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                                setMarginForView(viewHolder.container, 5);
                            }
                        }
                        if (customObjectNext != null && customObjectPre != null) {
                            if (customObjectNext.getType().equals(calendarCustomObject.getType()) && customObjectPre.getType().equals(calendarCustomObject.getType())) {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.square));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            } else if (customObjectNext.getType().equals(calendarCustomObject.getType()) && !customObjectPre.getType().equals(calendarCustomObject.getType())) {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_left));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            } else if (!customObjectNext.getType().equals(calendarCustomObject.getType()) && customObjectPre.getType().equals(calendarCustomObject.getType())) {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_right));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            } else {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_rounded));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                                setMarginForView(viewHolder.container, 5);
                            }
                        }
                    }
                } catch (Exception e) {
                    Log.e("UraNashel error: ", e.getMessage());
                }
            }
        }
        return view;
    }

    private void setMarginForView(View view, int marginSide) {
        if (view instanceof LinearLayout) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.setMargins(marginSide, 0, marginSide, 0);
        }
    }

    private Map<String, List<CalendarCustomObject>> getCurrentCalendarObjectBelongTo(List<Map<String, List<CalendarCustomObject>>> mapList,
                                                                                     CalendarCustomObject calendarCustomObject) {
        if (calendarCustomObject == null || calendarCustomObject.getType() == null || calendarCustomObject.getType().isEmpty()) {
            return null;
        }
        Map<String, List<CalendarCustomObject>> tempMap = new HashMap<>();
        List<CalendarCustomObject> calendarCustomObjects = new ArrayList<>();
        for (Map<String, List<CalendarCustomObject>> map : mapList) {
            List<CalendarCustomObject> calendarCustomObjects1 = map.get(calendarCustomObject.getType());
            if (calendarCustomObjects1 != null) {
                calendarCustomObjects.addAll(calendarCustomObjects1);
            }
        }
        tempMap.put(calendarCustomObject.getType(), calendarCustomObjects);
        return tempMap;
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

    private void setColorStroke(View view, String strokeColor) {
        if (strokeColor == null) {
            return;
        }
        UIUtils.setColorBackground(view, "#00FFFFFF", strokeColor);
    }

    private void setCircle(ViewHolder viewHolder, Calendar compareDate) {
        Calendar calendar = DateUtils.getCurrentDate();
        if (compareDate.get(Calendar.DATE) == calendar.get(Calendar.DATE) &&
                compareDate.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                compareDate.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
            viewHolder.date.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
            viewHolder.date.setTypeface(null, Typeface.BOLD);
            setColorStroke(viewHolder.date, strokeCircleColor);
        }
    }

    private static class ViewHolder {
        TextView date;
        LinearLayout containerCircle;
        FrameLayout containerDate;
        LinearLayout container;
    }
}

