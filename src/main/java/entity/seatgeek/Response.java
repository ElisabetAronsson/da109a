package entity.seatgeek;

import java.util.List;

public class Response {
    private Meta meta;
    private List<Performers> performers;

    public Response() {
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Performers> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performers> performers) {
        this.performers = performers;
    }
}
