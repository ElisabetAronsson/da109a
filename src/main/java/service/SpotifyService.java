package service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.spotify.Example;
import entity.spotify.Items;
import io.javalin.http.Context;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class SpotifyService {
    static final String BASE_URL = "https://api.spotify.com/v1/";
    static final int LIMIT = 10;
    static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static Example getFollowing(Context context) throws URISyntaxException, IOException, InterruptedException {
        String token = context.req().getHeader("Authorization");
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "me/following?type=artist&limit=" + LIMIT))
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(getResponse.body(), Example.class);
    }

    public static Example searchArtist (Context context) throws URISyntaxException, IOException, InterruptedException {
        String searchParameter = context.pathParam("name").replaceAll(" ", "%20");
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "search?q=artist:" + searchParameter + "&type=artist&limit=1"))
                .header("Content-Type", "application/json")
                .header("Authorization", context.req().getHeader("Authorization"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(postResponse.body(), Example.class);
    }


}
