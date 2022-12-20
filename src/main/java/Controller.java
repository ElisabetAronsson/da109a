import com.google.gson.Gson;
import io.javalin.http.Context;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Controller {

    static final Gson gson = new Gson();

    public static void getFollowing(Context context) throws URISyntaxException, IOException, InterruptedException {

        String token = context.req().getHeader("Authorization");
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/me/following?type=artist&limit=10"))
                .header("Content-Type","application/json")
                .header("Authorization", token)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        context.result(getResponse.body());
    }

    public static void getConcerts(Context context) throws URISyntaxException, IOException, InterruptedException{
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.seatgeek.com/2/events?performers.slug=" + "sza" + "&per_page=50&client_id=MzEwOTIxMTd8MTY3MTQ1NTk5My40MDc0MjI"))
                .header("Content-Type","application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        context.result(getResponse.body());
    }
}
