import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.seatgeek.EventWrapper;
import entity.seatgeek.Events;
import entity.seatgeek.InfoObjWrapper;
import entity.spotify.Artists;
import entity.spotify.Items;
import entity.wikipedia.ExtractWrapper;
import io.javalin.http.Context;
import service.SeatgeekService;
import service.SpotifyService;
import service.WikipediaService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
            ExtractWrapper extractWrapper = WikipediaService.fetchExtract(items.getName());
            items.setExtractWrapper(extractWrapper);
            System.out.println(items.getExtractWrapper().getExtract_html());
        }

        context.result(mapper.writeValueAsString(artists));
    }

    /**
     * Hämtar en specifik konsert med ett konsert ID
     */
    public static void getSpecificConcert(Context context) throws URISyntaxException, IOException, InterruptedException{
      //  ExtractWrapper extract = WikipediaService.fetchExtract(context);
        Events event = SeatgeekService.getSpecificConcert(context);

        InfoObjWrapper infoObjWrapper = new InfoObjWrapper();
      //  infoObjWrapper.setExtractWrapper(extract);

        List<Events> eventsList = new ArrayList<>();
        eventsList.add(event);
        EventWrapper eventWrapper = new EventWrapper();
        eventWrapper.setEvents(eventsList);
        infoObjWrapper.setEventWrapper(eventWrapper);

        context.result(mapper.writeValueAsString(infoObjWrapper));
        //context.json(mapper.writeValueAsString(event));
    }

    public static void getFollowedArtists(Context context) throws URISyntaxException, IOException, InterruptedException{
        Artists artists = SpotifyService.getFollowing(context);
        context.result(mapper.writeValueAsString(artists));
    }

    public static void getArtistConcerts(Context context) throws URISyntaxException, IOException, InterruptedException{
        String s = context.pathParam("artistName");
        //SeatgeekService.getConcertsOfArtist(s);
        context.json(SeatgeekService.getConcertsOfArtist(s));

    }

    public static void getArtistSpecificConcert(Context context) throws URISyntaxException, IOException, InterruptedException{
        Items items = SpotifyService.getArtists(context);
        items.setExtractWrapper(WikipediaService.fetchExtract(items.getName()));
        items.setEvents(new ArrayList<Events>());
        Events e = SeatgeekService.getSpecificConcert(context);
        items.getEvents().add(SeatgeekService.getSpecificConcert(context));
        context.json(items);

    }

}