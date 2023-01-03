package entity.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.seatgeek.Events;
import entity.wikipedia.ExtractWrapper;

import java.util.List;

public class Items {
    private  String name;
    private  String id;
    @JsonProperty("external_urls")
    private Url external_urls;
    private List<Image> images;
    private List<Events> events;
    private ExtractWrapper extractWrapper;

    public Items() {
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
    public Url getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(Url external_urls) {
        this.external_urls = external_urls;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    public ExtractWrapper getExtractWrapper() {
        return extractWrapper;
    }

    public void setExtractWrapper(ExtractWrapper extractWrapper) {
        this.extractWrapper = extractWrapper;
    }
}
