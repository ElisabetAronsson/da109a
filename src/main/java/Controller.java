import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import entity.seatgeek.Event;
import entity.spotify.Artists;
import entity.spotify.Example;
import entity.spotify.Items;
import io.javalin.http.Context;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    static final String BASE_URL = "https://api.spotify.com/v1/";
    static final int LIMIT = 10;
    static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static void getFollowing(Context context) throws URISyntaxException, IOException, InterruptedException {
        String token = context.req().getHeader("Authorization");
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "me/following?type=artist&limit=" + LIMIT))
                .header("Content-Type","application/json")
                .header("Authorization", token)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Example itemsList = mapper.readValue(getResponse.body(), Example.class);
        context.result(mapper.writeValueAsString(itemsList));

        List<Items> itemsList1 = itemsList.getArtists().getItems();
        for (Items item: itemsList1){
           String name = item.getName();

        }
    }

    public static void getConcerts(Context context) throws URISyntaxException, IOException, InterruptedException{
        String pathId = context.pathParam("id");
        String artistName = pathId.replace(' ', '+');

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.seatgeek.com/2/events?performers.slug=" + artistName + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI"))
                .header("Content-Type","application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        context.result(getResponse.body());
    }

    public static void getConcertsOfArtist(Context context) throws URISyntaxException, IOException, InterruptedException{
        String pathId = context.pathParam("id");
        String artistName = pathId.replace(' ', '+');

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.seatgeek.com/2/events?performers.slug=" + artistName + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI"))
                .header("Content-Type","application/json")
                .build();

        System.out.println("https://api.seatgeek.com/2/events?performers.slug=" + artistName + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Event concerts = mapper.readValue(getResponse.body(), Event.class);
        context.result(mapper.writeValueAsString(concerts));
    }

    public static void getSpecificConcert(Context context) throws URISyntaxException, IOException, InterruptedException{
        String concertId = context.pathParam("concert_id");

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.seatgeek.com/2/events/" + concertId + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI"))
                .header("Content-Type","application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Event concert = mapper.readValue(getResponse.body(), Event.class);
        context.result(mapper.writeValueAsString(concert));
    }

    public static void searchArtist(Context context) throws URISyntaxException, IOException, InterruptedException {
        String searchParameter = context.pathParam("name").replaceAll(" ","%20");
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"search?q=artist:" + searchParameter +"&type=artist&limit=1"))
                .header("Content-Type","application/json")
                .header("Authorization", context.req().getHeader("Authorization"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        Example itemsList = mapper.readValue(postResponse.body(), Example.class);
        context.result(mapper.writeValueAsString(itemsList));
    }

    public static void getSavedArtistsConcerts(Context context) throws IOException, InterruptedException { //Hur hämtar jag de specifika "Objekten"?
        String token = context.req().getHeader("Authorization");
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "me/following?type=artist&limit=" + LIMIT))
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Example itemsList = mapper.readValue(getResponse.body(), Example.class);

        //Jag vill hämta ut de olika artisterna för att hämta items i artisterna för att hämta namnen
        //Sedan vill jag för göra en ett json objekt med varje artists alla koncerter i...
        /*for (Artists artist: itemsList.getArtists()) {

        } */
    }

    public static void getAllConcertsInCity(Context context) throws URISyntaxException, IOException, InterruptedException {
        String pathId = context.pathParam("city");
        String city = pathId.replace(' ', '+');

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.seatgeek.com/2/events?type=concert&venue.city=" + city + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI"))
                .header("Content-Type","application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Event concert = mapper.readValue(getResponse.body(), Event.class);
        context.result(mapper.writeValueAsString(concert));
    }
}
