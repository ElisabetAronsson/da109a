package service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.seatgeek.Events;
import entity.spotify.Artists;
import entity.spotify.ArtistsWrapper;
import entity.spotify.Items;
import entity.wikipedia.ExtractWrapper;
import io.javalin.http.Context;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static service.SpotifyService.mapper;

public class WikipediaService {
    /**
     * hämtar info från wikipedia
     */
    public static ExtractWrapper fetchExtract (String artistName) throws URISyntaxException, IOException, InterruptedException {
        artistName = artistName.replace(" ","_");

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet("https://sv.wikipedia.org/api/rest_v1/page/summary/"+artistName));
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        ExtractWrapper extract = mapper.readValue(json, ExtractWrapper.class);
        System.out.println(extract.getExtract_html());
        return extract;
    }
}
