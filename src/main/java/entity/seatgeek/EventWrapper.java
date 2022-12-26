package entity.seatgeek;

import java.util.List;

public class EventWrapper {
    private List<Events> events;
    private Meta meta;

    public List<Events> getEvents() {
        return events;
    }

    public void addEvent(Events event){
        events.add(event);
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
