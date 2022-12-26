import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.spotify.Artists;
import entity.spotify.Items;
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
     * Hämtar en specifik artist man sökt på
     */
    public static void searchArtist (Context context) throws URISyntaxException, IOException, InterruptedException {
        context.result(mapper.writeValueAsString(SpotifyService.searchArtist(context)));
    }

    /**
     * Hämtar en artist som man följer alla konserter
     */
    public static void getConcertsOfArtist(Context context) throws URISyntaxException, IOException, InterruptedException{
        Artists artists = SpotifyService.getFollowing(context);
        for (Items items : artists.getItems()) {
            items.setEvents(SeatgeekService.getConcertsOfArtist(items.getName()));
        }
        context.result(mapper.writeValueAsString(artists));
    }

    /**
     * Hämtar en specifik konsert med ett konsert ID
     */
    public static void getSpecificConcert(Context context) throws URISyntaxException, IOException, InterruptedException{
        context.result(mapper.writeValueAsString(SeatgeekService.getSpecificConcert(context)));
    }

    public static void getAllConcertsInCity(Context context) throws URISyntaxException, IOException, InterruptedException {
        Artists artists = SpotifyService.getFollowing(context);

        context.result(mapper.writeValueAsString(SeatgeekService.getAllConcertsInCity(context)));
    }

    //INTE FÄRDIGA METODER

    /**För att hämta alla konserter i den staden man söker på
     *
     * kan fixa: så man bara ser konserter artisterna du följer har i den staden
     */

    /**
     *hämta info från wikipedia
     */
    public static void getWikipediaExtractOfArtist (Context context) throws URISyntaxException, IOException, InterruptedException { // Jag vet inte om detta fungerar? Emilia
        context.result(mapper.writeValueAsString(WikipediaService.getWiki(context)));
    }
}