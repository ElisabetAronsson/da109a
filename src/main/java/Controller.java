import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.spotify.Example;
import io.javalin.http.Context;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Controller {

    static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static void getFollowing(Context context) throws URISyntaxException, IOException, InterruptedException {

        String token = context.req().getHeader("Authorization");
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/me/following?type=artist&limit=10"))
                .header("Content-Type","application/json")
                .header("Authorization", token)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Example itemsList = mapper.readValue(getResponse.body(), Example.class);
        context.result(mapper.writeValueAsString(itemsList));
    }

    public static void fetchConcerts(Context context) throws URISyntaxException, IOException, InterruptedException{
        //hämtar koncerter från event api:t
    }

    public static void getWiki (Context context) throws URISyntaxException, IOException, InterruptedException { // Jag vet inte om detta fungerar? Emilia
        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(new URI ("https://sv.wikipedia.org/api/rest_v1/page/summary/"+ context))
            .header("Content-Type","application/json")
            .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            Example itemsList = mapper.readValue(getResponse.body(), Example.class);
            context.result(mapper.writeValueAsString(itemsList));
    }


    public static void fetchArtistConcert(Context context) throws URISyntaxException, IOException, InterruptedException{
        

        //hämtar en specifik koncert av en artist
    }

   



}
