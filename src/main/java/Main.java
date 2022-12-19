import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.get;
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
                .start(8888);

        //API Endpoints
        app.routes(() -> {
           path("/v1/artists/following", () -> get(Controller::getFollowing));
        });
    }
}