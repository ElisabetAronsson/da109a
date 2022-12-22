package service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.seatgeek.EventWrapper;
import entity.seatgeek.Events;
import entity.spotify.Example;
import entity.spotify.Items;
import io.javalin.http.Context;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.Buffer;
import java.util.List;

import static service.SpotifyService.mapper;

public class SeatgeekService {

    public static EventWrapper getConcertsOfArtist(Context context) throws URISyntaxException, IOException, InterruptedException{
        String pathId = context.pathParam("artist_id");
        String artistName = pathId.replace(' ', '+');

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.seatgeek.com/2/events?performers.slug=" + artistName + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI"))
                .header("Content-Type","application/json")
                .build();

        System.out.println("https://api.seatgeek.com/2/events?performers.slug=" + artistName + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(getResponse.body(), EventWrapper.class);
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
    public static JsonObject getAllConcertsInCity(Example followedArtists, String city) throws URISyntaxException, IOException, InterruptedException {
        JsonObject json = new JsonObject();
        city = city.replace(' ', '+');

        List<Items> items = followedArtists.getArtists().getItems();
        for (Items item: items) {
            String name = item.getName();
            if((name != null) || name != " ") {
                HttpRequest getRequest = HttpRequest.newBuilder()
                        .uri(new URI("https://api.seatgeek.com/2/events?type=concert&venue.city=" + city + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI"))
                        .header("Content-Type", "application/json")
                        .build();

                HttpClient httpClient = HttpClient.newHttpClient();
                HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
                Events event = mapper.readValue(getResponse.body(), Events.class);


                json.add(name, event); //Deep copy?
            }
        }
        System.out.println(json.toString());
        return json;
    }

}
