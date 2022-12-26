package service;

import entity.seatgeek.Events;
import entity.seatgeek.Performers;
import entity.wikipedia.ExtractWrapper;

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
    public static ExtractWrapper fetchExtract (Events events) throws URISyntaxException, IOException, InterruptedException{
        String name = "";

        for (Performers perf : events.getPerformers()){
           name = perf.getPrimary();
        }
        String artistName = name.replace(' ', '_');

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://sv.wikipedia.org/api/rest_v1/page/summary/" + artistName))
                .header("Content-Type", "application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(getResponse.body(), ExtractWrapper.class);
    }
}
