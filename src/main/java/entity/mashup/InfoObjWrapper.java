package entity.mashup;

import entity.seatgeek.Events;
import entity.wikipedia.Extract;

public class InfoObjWrapper {
    Extract extract;
    Events events;

    public Extract getExtract() {
        return extract;
    }

    public void setExtract(Extract extract) {
        this.extract = extract;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }
}
