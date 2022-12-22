package service;

import entity.seatgeek.EventWrapper;
import entity.seatgeek.Events;
import io.javalin.http.Context;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static service.SpotifyService.mapper;

public class SeatgeekService {

    public static List<Events> getConcertsOfArtist(String artistName) throws URISyntaxException, IOException, InterruptedException{
        artistName = artistName.replace(' ', '-');

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.seatgeek.com/2/events?performers.slug=" + artistName + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI"))
                .header("Content-Type","application/json")
                .build();

        System.out.println("https://api.seatgeek.com/2/events?performers.slug=" + artistName + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(getResponse.body(), EventWrapper.class).getEvents();
    }

    public static EventWrapper getSpecificConcert(Context context) throws URISyntaxException, IOException, InterruptedException{
        String concertId = context.pathParam("concert_id");

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.seatgeek.com/2/events?id=" + concertId + "&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI"))
                .header("Content-Type","application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(getResponse.body(), EventWrapper.class);
    }

    /**För att hämta alla konserter i den staden man söker på
     *
     * kan fixa: så man bara ser konserter artisterna du följer har i den staden
     */
    public static Events getAllConcertsInCity(Context context) throws URISyntaxException, IOException, InterruptedException {
        String pathId = context.pathParam("city");
        String city = pathId.replace(' ', '+');

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.seatgeek.com/2/events?type=concert&venue.city=" + city + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI"))
                .header("Content-Type","application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(getResponse.body(), Events.class);
    }

}
