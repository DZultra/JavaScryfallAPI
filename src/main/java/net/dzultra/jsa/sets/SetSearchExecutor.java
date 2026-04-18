package net.dzultra.jsa.sets;

import com.google.gson.Gson;
import net.dzultra.jsa.ResponseValidator;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.exceptions.SetSearchException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

/**
 * Service class responsible for executing Scryfall API requests related to MTG sets.
 * <p>
 * This class handles HTTP communication, response validation, and JSON deserialization.
 * It is designed to be instantiated with a {@link ScryfallClient} and {@link Gson}
 * instance for reuse across multiple requests.
 */
public class SetSearchExecutor {

    private final ScryfallClient client;
    private final Gson gson;

    /**
     * Creates a new {@code SetSearchExecutor} instance.
     *
     * @param client the Scryfall HTTP client used for requests
     * @param gson   the Gson instance used for JSON deserialization
     */
    public SetSearchExecutor(ScryfallClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    /**
     * Executes a request that returns a paginated list of MTG sets.
     *
     * @param uri the API endpoint URI
     * @return a {@link MTGSetList} containing the parsed response data
     * @throws SetSearchException if the request fails or the response is invalid
     */
    public MTGSetList executeSetListSearch(URI uri) {
        String response = sendHTTPRequest(uri);

        if (!ResponseValidator.isValidResponseDouble(gson, response, "list", "set")) {
            throw new SetSearchException(uri, response);
        }

        return parseGson(MTGSetList.class, response);
    }

    /**
     * Executes a request that returns a single MTG set.
     *
     * @param uri the API endpoint URI
     * @return a {@link MTGSet} representing the requested set
     * @throws SetSearchException if the request fails or the response is invalid
     */
    public MTGSet executeSetSearch(URI uri) {
        String response = sendHTTPRequest(uri);

        if (!ResponseValidator.isValidResponseSingle(gson, response, "set")) {
            throw new SetSearchException(uri, response);
        }

        return parseGson(MTGSet.class, response);
    }

    /**
     * Sends an HTTP GET request to the given URI.
     *
     * @param uri the target API endpoint
     * @return the raw JSON response body
     * @throws SetSearchException if the request fails due to IO or interruption
     */
    private String sendHTTPRequest(URI uri) {
        try {
            return client.httpClient.send(
                    client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new SetSearchException(uri, null);
        }
    }

    /**
     * Deserializes a JSON response into the specified type.
     *
     * @param <T>       the target type
     * @param classType the class to deserialize into
     * @param response  the raw JSON string
     * @return the parsed object
     * @throws SetSearchException if JSON parsing fails
     */
    private <T extends SetRecord> T parseGson(Class<T> classType, String response) {
        try {
            return gson.fromJson(response, classType);
        } catch (Exception e) {
            throw new SetSearchException(null, response);
        }
    }
}