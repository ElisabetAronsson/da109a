package service;

import entity.spotify.Example;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class SpotifyService {
    public static List<Example> artistsFollowing() {
        List<Example> artists = new ArrayList<>();
        artists.add(new Example("Artist 1"));
        artists.add(new Example("Artist 2"));
        return artists;
    }
}
