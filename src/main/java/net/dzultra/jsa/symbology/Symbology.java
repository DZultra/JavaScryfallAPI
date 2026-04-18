package net.dzultra.jsa.symbology;

import com.google.gson.Gson;
import net.dzultra.jsa.ResponseValidator;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.exceptions.SymbologyResponseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

/**
 * Provides access to the Scryfall symbology endpoints.
 * <p>
 * This class allows retrieval and parsing of card symbol definitions and
 * mana cost representations from the Scryfall API. It uses a
 * {@link ScryfallClient} for HTTP communication and {@link Gson} for
 * JSON deserialization.
 */
public class Symbology {

    /**
     * The client used to perform HTTP requests.
     */
    protected final ScryfallClient client;

    /**
     * The {@link Gson} instance used for JSON deserialization.
     */
    protected Gson gson = new Gson();

    /**
     * The API endpoint for symbology-related requests.
     */
    public String endpoint = "/symbology/";

    /**
     * Stores the last raw JSON response received from the API.
     */
    public String response;

    /**
     * Constructs a new {@code Symbology} instance using the given client.
     *
     * @param client the {@link ScryfallClient} used to perform API requests
     */
    public Symbology(ScryfallClient client) {
        this.client = client;
    }

    /**
     * Sends a GET request to the specified URI and returns the validated response body.
     * <p>
     * The response is validated using {@link ResponseValidator#isValidSymbologyResponse(Gson, String)}.
     * If the response is invalid or an error occurs during the request,
     * a {@link SymbologyResponseException} is thrown.
     *
     * @param uri the target URI for the request
     * @return the raw JSON response as a string
     * @throws SymbologyResponseException if the response is invalid or the request fails
     */
    private String getResponse(URI uri) {
        try {
            this.response = this.client.httpClient
                    .send(this.client.requestBuilder.GET().uri(uri).build(),
                            HttpResponse.BodyHandlers.ofString())
                    .body();

            if (ResponseValidator.isValidSymbologyResponse(gson, response)) {
                return response;
            } else {
                throw new SymbologyResponseException(this);
            }
        } catch (IOException | InterruptedException e) {
            throw new SymbologyResponseException(this);
        }
    }

    /**
     * Retrieves all card symbols from the Scryfall API.
     *
     * @return a {@link SymbologyResponse} containing all available card symbols
     * @throws SymbologyResponseException if the request fails or the response is invalid
     */
    public SymbologyResponse getCardSymbols() {
        return gson.fromJson(
                this.getResponse(URI.create(this.client.getBaseUrl() + this.endpoint)),
                SymbologyResponse.class
        );
    }

    /**
     * Parses a mana cost string into its structured representation.
     * <p>
     * This method sends the given mana cost to the Scryfall API and returns
     * a parsed response describing the individual symbols and their properties.
     *
     * @param manaCost the mana cost string to parse (e.g., {@code "{2}{G}{G}"} or {@code "WUBRG"})
     * @return a {@link ManaCostResponse} representing the parsed mana cost
     * @throws SymbologyResponseException if the request fails or the response is invalid
     */
    public ManaCostResponse getManaCost(String manaCost) {
        return gson.fromJson(
                this.getResponse(URI.create(
                        this.client.getBaseUrl() + this.endpoint + "parse-mana?cost=" + manaCost
                )),
                ManaCostResponse.class
        );
    }
}