import com.google.gson.Gson;
import io.javalin.http.Context;
import service.SpotifyService;

public class controller {

    static final Gson gson = new Gson();

    public static void getFollowing(Context context) {
        context.result(gson.toJson(SpotifyService.artistsFollowing()));
    }
}
