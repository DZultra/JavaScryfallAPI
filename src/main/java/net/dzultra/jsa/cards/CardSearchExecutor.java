package net.dzultra.jsa.cards;

import com.google.gson.Gson;
import net.dzultra.jsa.ResponseValidator;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.exceptions.CardCollectionException;
import net.dzultra.jsa.exceptions.CardNameException;
import net.dzultra.jsa.exceptions.CardSearchException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Internal executor responsible for performing HTTP requests to Scryfall card-related endpoints.
 * <p>
 * This class handles request execution, response validation, and JSON deserialization for:
 * <ul>
 *     <li>Card search queries</li>
 *     <li>Single card lookups</li>
 *     <li>Card name autocomplete</li>
 * </ul>
 * It acts as the low-level networking layer for {@link CardRequester}.
 */
public class CardSearchExecutor {

    /**
     * Executes a search query returning multiple cards.
     *
     * @param client the Scryfall client used for HTTP requests
     * @param gson   Gson instance for JSON parsing
     * @param uri    the request URI
     * @return a {@link CardList} containing matching cards
     * @throws CardSearchException if the request fails or response is invalid
     */
    public static CardList executeCardSearch(ScryfallClient client, Gson gson, URI uri) {
        String response;

        try {
            response = client.httpClient.send(
                    client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardSearchException(uri, null);
        }

        if (!ResponseValidator.isValidResponseDouble(gson, response, "list", "card")) {
            throw new CardSearchException(uri, response);
        }

        try {
            return gson.fromJson(response, CardList.class);
        } catch (Exception e) {
            throw new CardSearchException(uri, response);
        }
    }

    /**
     * Executes a request returning a single card.
     *
     * @param client the Scryfall client used for HTTP requests
     * @param gson   Gson instance for JSON parsing
     * @param uri    the request URI
     * @return a {@link Card} object
     * @throws CardSearchException if the request fails or response is invalid
     */
    public static Card executeSingleCardSearch(ScryfallClient client, Gson gson, URI uri) {
        String response;

        try {
            response = client.httpClient.send(
                    client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardSearchException(uri, null);
        }

        if (!ResponseValidator.isValidResponseSingle(gson, response, "card")) {
            throw new CardSearchException(uri, response);
        }

        try {
            return gson.fromJson(response, Card.class);
        } catch (Exception e) {
            System.out.println(e.getCause() + " | " + e.getMessage());
            throw new CardSearchException(uri, response);
        }
    }

    /**
     * Executes a card name autocomplete request.
     *
     * @param client the Scryfall client used for HTTP requests
     * @param gson   Gson instance for JSON parsing
     * @param uri    the request URI
     * @return a {@link CardNameList} containing suggested card names
     * @throws CardNameException if the request fails or response is invalid
     */
    public static CardNameList executeCardNameListSearch(ScryfallClient client, Gson gson, URI uri) {
        String response;

        try {
            response = client.httpClient.send(
                    client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardNameException(uri, null);
        }

        if (!ResponseValidator.isValidResponseSingle(gson, response, "catalog")) {
            throw new CardNameException(uri, response);
        }

        try {
            return gson.fromJson(response, CardNameList.class);
        } catch (Exception e) {
            System.out.println(e.getCause() + " | " + e.getMessage());
            throw new CardNameException(uri, response);
        }
    }

    // ---- Deprecated Collection Endpoint ----

    /**
     * Executes a card collection request (legacy endpoint).
     * <p>
     * This method is deprecated and not recommended for production use.
     *
     * @param client the Scryfall client used for HTTP requests
     * @param gson   Gson instance for JSON parsing
     * @param uri    the request URI
     * @return a {@link CardCollection} response
     * @throws CardCollectionException if the request fails or response is invalid
     */
    @Deprecated
    public static CardCollection executeCardCollectionSearch(ScryfallClient client, Gson gson, URI uri) {
        String body = "";

        String response;

        try {
            response = client.httpClient.send(
                    client.requestBuilder.header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardCollectionException(uri, null);
        }

        if (!ResponseValidator.isValidResponseDouble(gson, response, "list", "card")) {
            throw new CardCollectionException(uri, response);
        }

        try {
            return gson.fromJson(response, CardCollection.class);
        } catch (Exception e) {
            System.out.println(e.getCause() + " | " + e.getMessage());
            throw new CardCollectionException(uri, response);
        }
    }
}