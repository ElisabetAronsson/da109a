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

           path("api/v1/artists/", () -> get(Controller::getFollowing));
           path("api/v1/artists/{id}", () -> get(Controller::getConcerts)); //Samma som getConcertsOfArtist utan object-mapper för test
           path("/v1/api/artists/", () -> get(Controller::getFollowing));
           path("api/v1/artists/{id}/concerts", () -> get(Controller::getConcertsOfArtist));
           path("api/v1/artists/{artist_id}/concerts/{concert_id}", () -> get(Controller::getSpecificConcert));
           path("/v1/api/artists/{name}", () -> post(Controller::searchArtist));

           path("api/v1/concerts", () -> get(Controller::getSavedArtistsConcerts));
           path("api/v1/concerts/{city}", () -> get(Controller::getAllConcertsInCity));
        });
    }

}