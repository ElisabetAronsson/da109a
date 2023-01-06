import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.seatgeek.Events;
import entity.spotify.Artists;
import entity.spotify.Items;
import io.javalin.http.Context;
import service.SeatgeekService;
import service.SpotifyService;
import service.WikipediaService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Controller {
    static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /**
     * Listar alla följda artister
     * @param context
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
   public static void getFollowedArtists(Context context) throws URISyntaxException, IOException, InterruptedException{
        Artists artists = SpotifyService.getFollowing(context);
        if (artists == null) {
            context.result();
        }   else {
            context.result(mapper.writeValueAsString(artists));
       }
    }

    /**
     * Listar en artists alla konserter
     * @param context
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getArtistConcerts(Context context) throws URISyntaxException, IOException, InterruptedException{
        String s = context.pathParam("artistName");
        context.json(SeatgeekService.getConcertsOfArtist(s));
    }

    /**
     * Hämtar information om en specifik koncert
     * @param context
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getArtistSpecificConcert(Context context) throws URISyntaxException, IOException, InterruptedException{
        Items items = SpotifyService.getArtist(context);
        items.setExtractWrapper(WikipediaService.fetchExtract(items.getName()));
        items.setEvents(new ArrayList<Events>());
        items.getEvents().add(SeatgeekService.getSpecificConcert(context));
        context.json(items);
    }

}