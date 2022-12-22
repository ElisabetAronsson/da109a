package entity.seatgeek;

import com.google.gson.JsonElement;

public class Events extends JsonElement {
    private String title;
    private String id;
    private String short_title;
    private Venue venue;
    private String datetime_utc;

    public String getDatetime_utc() {
        return datetime_utc;
    }

    public void setDatetime_utc(String datetime_utc) {
        this.datetime_utc = datetime_utc;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public JsonElement deepCopy() {
        return this;
    }
}
