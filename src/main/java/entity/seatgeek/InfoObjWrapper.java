package entity.seatgeek;

import entity.wikipedia.ExtractWrapper;

public class InfoObjWrapper {
    EventWrapper eventWrapper;
    ExtractWrapper extractWrapper;

    public EventWrapper getEventWrapper() {
        return eventWrapper;
    }

    public void setEventWrapper(EventWrapper eventWrapper) {
        this.eventWrapper = eventWrapper;
    }

    public ExtractWrapper getExtractWrapper() {
        return extractWrapper;
    }

    public void setExtractWrapper(ExtractWrapper extractWrapper) {
        this.extractWrapper = extractWrapper;
    }
}
