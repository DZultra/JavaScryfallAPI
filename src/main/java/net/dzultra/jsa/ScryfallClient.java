package net.dzultra.jsa;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class ScryfallClient {
    public final HttpClient httpClient;
    public final HttpRequest.Builder requestBuilder;
    public static final String BASE_URL = "https://api.scryfall.com";
    public final String userAgent;

    public ScryfallClient(String userAgent) {
        this.httpClient = HttpClient.newHttpClient();
        this.userAgent = userAgent;
        this.requestBuilder = HttpRequest.newBuilder()
                .header("User-Agent", userAgent)
                .header("Accept", "application/json");
    }

    public String getBaseUrl() {
        return BASE_URL;
    }
}
