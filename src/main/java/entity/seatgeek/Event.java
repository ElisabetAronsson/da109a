package entity.seatgeek;

public class Event {
    String short_title;
    String datetine_utc;
    Venue venue;

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getDatetine_utc() {
        return datetine_utc;
    }

    public void setDatetine_utc(String datetine_utc) {
        this.datetine_utc = datetine_utc;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
