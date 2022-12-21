package service;

import entity.spotify.Example;
import entity.wikipedia.SummaryWrapper;
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
    public static SummaryWrapper getWiki (Context context) throws URISyntaxException, IOException, InterruptedException { // Jag vet inte om detta fungerar? Emilia
        String pathId = context.pathParam("artist_id");
        String artistName = pathId.replace(' ', '_');

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://sv.wikipedia.org/api/rest_v1/page/summary/" + artistName))
                .header("Content-Type", "application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(getResponse.body(), SummaryWrapper.class);
    }
}
