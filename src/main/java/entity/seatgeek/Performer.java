package entity.seatgeek;

public class Performer {
    private int id;
    private boolean has_upcoming_events;
    private String slug;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHas_upcoming_events() {
        return has_upcoming_events;
    }

    public void setHas_upcoming_events(boolean has_upcoming_events) {
        this.has_upcoming_events = has_upcoming_events;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
