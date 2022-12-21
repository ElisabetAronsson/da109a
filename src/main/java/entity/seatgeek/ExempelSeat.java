package entity.seatgeek;

import java.util.List;

public class ExempelSeat {
    private List<Events> events;
    private Meta meta;

    public List<Events> getEvent() {
        return events;
    }

    public void setEvent(List<Events> events) {
        this.events = events;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
