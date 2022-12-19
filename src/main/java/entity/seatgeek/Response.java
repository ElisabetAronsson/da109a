package entity.seatgeek;

import java.util.List;

public class Response {
    private Meta meta;
    private List<Performer> performers;

    public Response() {
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }
}
