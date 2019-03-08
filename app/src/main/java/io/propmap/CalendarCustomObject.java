package io.propmap;

import java.util.Calendar;

public class CalendarCustomObject {
    private Calendar calendar;
    private String colorBackground;
    private String colorStroke;
    private Integer widthStroke;
    private String type;

    public CalendarCustomObject(Calendar calendar, String colorBackground, String colorStroke, Integer widthStroke, String type) {
        this.calendar = calendar;
        this.colorBackground = colorBackground;
        this.colorStroke = colorStroke;
        this.widthStroke = widthStroke;
        this.type = type;
    }

    public CalendarCustomObject() {
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(String colorBackground) {
        this.colorBackground = colorBackground;
    }

    public String getColorStroke() {
        return colorStroke;
    }

    public void setColorStroke(String colorStroke) {
        this.colorStroke = colorStroke;
    }

    public Integer getWidthStroke() {
        return widthStroke;
    }

    public void setWidthStroke(Integer widthStroke) {
        this.widthStroke = widthStroke;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
