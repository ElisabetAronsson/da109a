import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.seatgeek.Events;
import entity.seatgeek.Performers;
import entity.spotify.Artists;
import entity.spotify.Items;
import entity.wikipedia.ExtractWrapper;
import io.javalin.http.Context;
import service.SeatgeekService;
import service.SpotifyService;
import service.WikipediaService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Controller {
    static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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
        Events events = SeatgeekService.getSpecificConcert(context);
        ExtractWrapper extract = WikipediaService.fetchExtract(events);
        List<Performers> performers = events.getPerformers();
        for (Performers perf : performers){
            perf.setExtract(extract.getExtract());
        }
        context.result(mapper.writeValueAsString(events));
    }

    /**
     * Alla ens artisters konserter i en stad
     */
    /*
    public static void getAllConcertsInCity(Context context) throws URISyntaxException, IOException, InterruptedException {
        Artists artists = SpotifyService.getFollowing(context);
        for (Items items : artists.getItems()) {
            String name = items.getName();
            List<Events> event = SeatgeekService.getMyArtistsConcertsInCity(name, context);
            if (event!= null) {
                items.setEvents(event);
            }
        }
        context.result(mapper.writeValueAsString(artists));
    }
*/
}