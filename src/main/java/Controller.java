import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import service.SeatgeekService;
import service.SpotifyService;
import service.WikipediaService;

import java.io.IOException;
import java.net.URISyntaxException;

public class Controller {
    static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /**
     * Hämtar vilka artister man följer på spotify
     */
    public static void getFollowing(Context context) throws URISyntaxException, IOException, InterruptedException {
        context.result(mapper.writeValueAsString(SpotifyService.getFollowing(context)));
    }

    /**
     * hämtar en lista med endast namnet på artister man följer på spotify
     */
    public static void getFollowingList(Context context) throws URISyntaxException, IOException, InterruptedException {
        context.result(mapper.writeValueAsString(SpotifyService.getFollowingList(context)));
    }

    /**
     * Hämtar en specifik artist man sökt på
     */
    public static void searchArtist (Context context) throws URISyntaxException, IOException, InterruptedException {
        context.result(mapper.writeValueAsString(SpotifyService.searchArtist(context)));
    }

    /**
     * Hämtar en artist som man följer alla konserter
     */
    public static void getConcertsOfArtist(Context context) throws URISyntaxException, IOException, InterruptedException{
        context.result(mapper.writeValueAsString(SeatgeekService.getConcertsOfArtist(context)));
    }

    /**
     * Hämtar en specifik konsert med ett konsert ID
     */
    public static void getSpecificConcert(Context context) throws URISyntaxException, IOException, InterruptedException{
        context.result(mapper.writeValueAsString(SeatgeekService.getSpecificConcert(context)));
    }


    //INTE FÄRDIGA METODER

    /**För att hämta alla konserter i den staden man söker på
     *
     * kan fixa: så man bara ser konserter artisterna du följer har i den staden
     */
    public static void getAllConcertsInCity(Context context) throws URISyntaxException, IOException, InterruptedException {
        context.result(mapper.writeValueAsString(SeatgeekService.getAllConcertsInCity(context)));
    }

    /**
     *hämta info från wikipedia
     */
    public static void getWiki (Context context) throws URISyntaxException, IOException, InterruptedException { // Jag vet inte om detta fungerar? Emilia
        context.result(mapper.writeValueAsString(WikipediaService.getWiki(context)));
    }
}