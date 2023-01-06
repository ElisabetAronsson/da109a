package service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.spotify.Artists;
import entity.spotify.Items;
import io.javalin.http.Context;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SpotifyService {
    static final String BASE_URL = "https://api.spotify.com/v1/";
    static final int LIMIT = 50;
    static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static Artists getFollowing(Context context) throws URISyntaxException, IOException, InterruptedException {
        String token;
        if (context.req().getHeader("Authorization") == null) {
            token = "";
        } else {
            token = context.req().getHeader("Authorization");
        }

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "me/following?type=artist&limit=" + LIMIT))
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (getResponse.statusCode() == 200) {
            JsonNode root = mapper.readTree(getResponse.body());
            return mapper.treeToValue(root.path("artists"), Artists.class);
        } else {
            context.result(getResponse.body());
            context.status(getResponse.statusCode());
            return null;
        }
    }

    public static Items getArtists(Context context) throws IOException, InterruptedException {
        String token;
        if (context.req().getHeader("Authorization") == null) {
            token = "";
        } else {
            token = context.req().getHeader("Authorization");
        }
        String id = context.pathParam("id");
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "artists/" + id))
                .header("Content-Type", "application/json")
                .header("Authorization", context.req().getHeader("Authorization"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (getResponse.statusCode() == 200)  {
            return mapper.readValue(getResponse.body(), Items.class);
        } else {
            return null;
        }
    }
}
