package service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.seatgeek.Events;
import entity.spotify.Artists;
import entity.spotify.ArtistsWrapper;
import entity.spotify.Items;
import entity.wikipedia.Extract;
import entity.wikipedia.ExtractWrapper;
import io.javalin.http.Context;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static service.SpotifyService.mapper;

public class WikipediaService {
    /**
     * hämtar info från wikipedia
     */
    public static Extract fetchExtract (Context context) throws URISyntaxException, IOException, InterruptedException{
        String name =  context.pathParam("artist_name");
        String artistName = name.replace(' ', '_');

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://en.wikipedia.org/api/rest_v1/page/summary/" + artistName))
                .header("Content-Type", "application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(getResponse.body(), Extract.class);
    }
}
