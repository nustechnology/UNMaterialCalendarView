package nguyenngoctan44.io.uranashel_multirangecalendarview;


public class CalendarCustomObject {
    private UNCalendar UNCalendar;
    private String colorBackground;
    private String colorStroke;
    private Integer widthStroke;
    private String type;

    public CalendarCustomObject(UNCalendar UNCalendar, String colorBackground, String colorStroke, Integer widthStroke, String type) {
        this.UNCalendar = UNCalendar;
        this.colorBackground = colorBackground;
        this.colorStroke = colorStroke;
        this.widthStroke = widthStroke;
        this.type = type;
    }

    public CalendarCustomObject() {
    }

    public UNCalendar getUNCalendar() {
        return UNCalendar;
    }

    public void setUNCalendar(UNCalendar UNCalendar) {
        this.UNCalendar = UNCalendar;
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
