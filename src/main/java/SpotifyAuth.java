import java.net.http.HttpRequest;

public class SpotifyAuth {
    private String client_id;
    private String response_type;
    private String redirect_uri;
    private String mainURI;

    // scope som behövs : user-follow-read

    public SpotifyAuth(String redirect_uri){
        mainURI = "https://api.spotify.com/v1";
        client_id = "ee0bdca0269146eaa8345787f079f9d2";
        response_type = "code";
        this.redirect_uri = redirect_uri;
        connectToSpotify();
    }

    private void connectToSpotify() {
       // get("/authorize")

    }


}
