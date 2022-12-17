import io.javalin.Javalin;

public class Runner {

    public static void main(string[] args) {
        Javalin app= Javalin.create(config -> {
            config.staticFiles.add("/static", Location.CLASSPATH);
            config.plugins.enableCors(cors -> {
                cors.add (it -> {
                    it.anyHost();
                });
            });

            JavalinPebble.init();
        })

        .get("/", ctx ->{getTokens(ctx);})
        .start(5000);
    }
}