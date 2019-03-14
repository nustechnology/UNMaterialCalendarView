package nguyenngoctan44.io.uranashel_multirangecalendarview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GridDateAdapter extends BaseAdapter {
    private List<CalendarCustomObject> dateNumber;
    private Context context;
    private List<CalendarCustomObject> rangeDates;
    private String dateColor = "#000000";
    private int dateSize = 14;
    private String strokeCircleColor = "#000000";

    GridDateAdapter(Context context, List<CalendarCustomObject> dateNumber, List<CalendarCustomObject> rangeDates,
                    String dateColor, Integer dateSize, String strokeCircleColor) {

        this.dateNumber = dateNumber;
        this.context = context;
        this.rangeDates = rangeDates;
        this.dateColor = dateColor;
        this.dateSize = dateSize;
        this.strokeCircleColor = strokeCircleColor;
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
            view = layoutInflater.inflate(R.layout.item_layout, null);
            viewHolder.date = view.findViewById(R.id.dateNumber);
            viewHolder.containerCircle = view.findViewById(R.id.containerCircle);
            viewHolder.container = view.findViewById(R.id.container);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (calendarCustomObject != null && calendarCustomObject.getUNCalendar() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(calendarCustomObject.getUNCalendar().getYear(), calendarCustomObject.getUNCalendar().getMonth(),
                    calendarCustomObject.getUNCalendar().getDate());
            viewHolder.date.setText(String.valueOf(calendar.get(Calendar.DATE)));
            viewHolder.date.setTextSize(dateSize);
            viewHolder.date.setTextColor(Color.parseColor(dateColor));
            setCircle(viewHolder, calendarCustomObject.getUNCalendar());
            setColorStroke(viewHolder.date, strokeCircleColor);
            List<CalendarCustomObject> calendarCustomObjectList = getCurrentCalendarObjectBelongTo(rangeDates, calendarCustomObject);
            if (calendarCustomObjectList != null) {
                try {
                    if (DateUtils.checkIfDateInRange(calendarCustomObjectList, calendarCustomObject)) {
                        CalendarCustomObject tempCal = calendarCustomObjectList.get(0);
                        viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.square));
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewHolder.container.getLayoutParams();
                        layoutParams.height = (int) UIUtils.convertDpToPixel(25, context);
                        layoutParams.gravity = Gravity.CENTER;
                        viewHolder.container.setLayoutParams(layoutParams);
                        setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                        CalendarCustomObject customObjectNext = (i + 1 >= dateNumber.size() ? null : dateNumber.get(i + 1));
                        CalendarCustomObject customObjectPre = (i - 1 < 0 ? null : dateNumber.get(i - 1));

                        if (customObjectNext == null) {
                            viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_right));
                            setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            setMarginForView(viewHolder.container, 0, 0, 5, 0);
                        }
                        if (customObjectPre == null) {
                            viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_left));
                            setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            setMarginForView(viewHolder.container, 5, 0, 0, 0);
                        }

                        if (customObjectNext != null && customObjectPre == null) {
                            if (customObjectNext.getType().equals(calendarCustomObject.getType())) {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.square));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            } else {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_right));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                                setMarginForView(viewHolder.container, 0, 0, 5, 0);
                            }
                        }
                        if (customObjectPre != null && customObjectNext == null) {
                            if (customObjectPre.getType().equals(calendarCustomObject.getType())) {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_right));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                                setMarginForView(viewHolder.container, 0, 0, 5, 0);
                            } else {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_rounded));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                                setMarginForView(viewHolder.container, 5, 0, 5, 0);
                            }
                        }
                        if (customObjectNext != null && customObjectPre != null) {
                            if (customObjectNext.getType().equals(calendarCustomObject.getType()) && customObjectPre.getType().equals(calendarCustomObject.getType())) {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.square));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            } else if (customObjectNext.getType().equals(calendarCustomObject.getType()) && !customObjectPre.getType().equals(calendarCustomObject.getType())) {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_left));
                                setMarginForView(viewHolder.container, 5, 0, 0, 0);
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                            } else if (!customObjectNext.getType().equals(calendarCustomObject.getType()) && customObjectPre.getType().equals(calendarCustomObject.getType())) {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_right));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                                setMarginForView(viewHolder.container, 0, 0, 5, 0);
                            } else {
                                viewHolder.container.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_rounded));
                                setColorForBackgroundAndStroke(viewHolder.container, tempCal);
                                setMarginForView(viewHolder.container, 5, 0, 5, 0);
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

    private void setMarginForView(View view, int left, int top, int right, int bottom) {
        if (view instanceof LinearLayout) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.setMargins(left, top, right, bottom);
            view.setLayoutParams(params);
        }
        if (view instanceof FrameLayout) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
            params.setMargins(left, top, right, bottom);
            view.setLayoutParams(params);
        }
    }

    private List<CalendarCustomObject> getCurrentCalendarObjectBelongTo(
            List<CalendarCustomObject> customObjects, CalendarCustomObject calendarCustomObject) {
        if (calendarCustomObject == null || calendarCustomObject.getType() == null || calendarCustomObject.getType().isEmpty()) {
            return null;
        }
        List<CalendarCustomObject> calendarCustomObjects = new ArrayList<>();
        for (CalendarCustomObject calendarCustomObjects1 : customObjects) {
            if (calendarCustomObjects1 != null && calendarCustomObjects1.getType().equals(calendarCustomObject.getType())) {
                calendarCustomObjects.add(calendarCustomObjects1);
            }
        }
        return calendarCustomObjects;
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
        UIUtils.setColorBackground(view, null, strokeColor);
    }

    private void setCircle(ViewHolder viewHolder, UNCalendar compareDate) {
        Calendar calendar = DateUtils.getCurrentDate();
        if (compareDate.getDate() == calendar.get(Calendar.DATE) &&
                compareDate.getYear() == calendar.get(Calendar.YEAR) &&
                compareDate.getMonth() == calendar.get(Calendar.MONTH)) {
            viewHolder.date.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
            viewHolder.date.setTypeface(null, Typeface.BOLD);
        }
    }

    private static class ViewHolder {
        TextView date;
        FrameLayout containerCircle;
        FrameLayout container;
    }
}
