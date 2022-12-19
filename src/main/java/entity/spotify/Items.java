package entity.spotify;

public class Items {
    private final String name;
    private final String id;

    public Items(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
