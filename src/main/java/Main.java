import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.path;

public class Main {
    public static void main(String[] args) {
        var app = Javalin.create( javalinConfig -> {
            javalinConfig.staticFiles.add("/public/", Location.CLASSPATH);
            javalinConfig.staticFiles.add("/public/css/", Location.CLASSPATH);
            javalinConfig.staticFiles.add("/public/html/", Location.CLASSPATH);
            javalinConfig.staticFiles.add("/public/script/", Location.CLASSPATH);
                })
                .get("/", ctx -> ctx.redirect("index.html"))
                .get("/callback", ctx -> ctx.redirect("callback.html"))
                .get("/listArtists", ctx -> ctx.redirect("listArtists.html"))
                .start(8888);

        //API Endpoints
        app.routes(() -> {

           path("/api/v1/artists/", () -> get(Controller::getFollowing));
           path("/api/v1/artists/{name}", () -> post(Controller::searchArtist));
           path("/api/v1/artists/{artist_id}/concerts", () -> get(Controller::getConcertsOfArtist));
           path("/api/v1/concerts/{concert_id}", () -> get(Controller::getSpecificConcert));

           path("/api/v1/location/{location_id}", () -> get(Controller::getAllConcertsInCity));

           path("/api/v1/wikitest/{artist_id}", () -> get(Controller::getWikipediaExtractOfArtist));
        });
    }

}