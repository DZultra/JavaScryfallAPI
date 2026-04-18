package net.dzultra.jsa;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

/**
 * A simple client wrapper for interacting with the Scryfall API.
 * <p>
 * This class provides a preconfigured {@link HttpClient} and {@link HttpRequest.Builder}
 * with default headers such as User-Agent and Accept. It serves as a base
 * for making HTTP requests to the Scryfall API.
 */
public class ScryfallClient {

    /**
     * The HTTP client used to send requests.
     */
    public final HttpClient httpClient;

    /**
     * A preconfigured request builder with default headers.
     */
    public final HttpRequest.Builder requestBuilder;

    /**
     * The base URL for all Scryfall API endpoints.
     */
    public static final String BASE_URL = "https://api.scryfall.com";

    /**
     * The User-Agent string used for all API requests.
     */
    public final String userAgent;

    /**
     * Constructs a new {@code ScryfallClient} with the specified User-Agent.
     * <p>
     * Initializes a default {@link HttpClient} and configures the request builder
     * with the provided User-Agent and an Accept header for JSON responses.
     *
     * @param userAgent the User-Agent string to include in all requests;
     *                  should identify your application according to Scryfall API guidelines
     */
    public ScryfallClient(String userAgent) {
        this.httpClient = HttpClient.newHttpClient();
        this.userAgent = userAgent;
        this.requestBuilder = HttpRequest.newBuilder()
                .header("User-Agent", userAgent)
                .header("Accept", "application/json");
    }

    /**
     * Returns the base URL for the Scryfall API.
     *
     * @return the base API URL as a string
     */
    public String getBaseUrl() {
        return BASE_URL;
    }

    /**
     * Returns the User-Agent used by this client.
     *
     * @return the User-Agent string
     */
    public String getUserAgent() {
        return userAgent;
    }
}