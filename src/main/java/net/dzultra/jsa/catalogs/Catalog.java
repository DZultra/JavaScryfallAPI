package net.dzultra.jsa.catalogs;

import com.google.gson.Gson;
import net.dzultra.jsa.ResponseValidator;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.exceptions.CatalogResponseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

/**
 * Represents a Scryfall catalog request and its resulting response.
 * <p>
 * A catalog is a predefined dataset provided by the Scryfall API, such as card names,
 * types, or keyword lists. This class performs the HTTP request immediately upon
 * construction and stores the raw response for later processing.
 */
public class Catalog {

    /**
     * Gson instance used for JSON deserialization.
     */
    protected Gson gson = new Gson();

    /**
     * The Scryfall client used to perform HTTP requests.
     */
    protected final ScryfallClient client;

    /**
     * The full URI used to fetch this catalog.
     */
    public final URI uri;

    /**
     * The catalog type defining which endpoint is being queried.
     */
    private final CatalogType catalogType;

    /**
     * The raw JSON response returned by the API.
     */
    private final String response;

    /**
     * Creates and executes a catalog request immediately.
     * <p>
     * The request is sent during construction, and the response is stored internally.
     * If the request fails, a {@link CatalogResponseException} is thrown.
     *
     * @param client      the Scryfall client used for HTTP communication
     * @param catalogType the type of catalog to request
     * @throws CatalogResponseException if the request fails or cannot be completed
     */
    public Catalog(ScryfallClient client, CatalogType catalogType) {
        this.client = client;
        this.catalogType = catalogType;
        this.uri = URI.create(this.client.getBaseUrl() + this.catalogType.getEndpoint());

        try {
            this.response = this.client.httpClient.send(
                    this.client.requestBuilder.GET().uri(this.uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CatalogResponseException(this);
        }
    }

    /**
     * Returns the raw JSON response from the catalog request.
     *
     * @return the API response as a JSON string
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Parses the catalog response into a structured {@link CatalogResponse}.
     * <p>
     * Validates the response before deserialization to ensure it matches the expected
     * catalog format.
     *
     * @return the parsed {@link CatalogResponse}
     * @throws CatalogResponseException if the response is invalid or cannot be parsed
     */
    public CatalogResponse getCatalog() {
        if (ResponseValidator.isValidResponseSingle(gson, this.getResponse(), "catalog")) {
            return gson.fromJson(this.getResponse(), CatalogResponse.class);
        } else {
            throw new CatalogResponseException(this);
        }
    }

    /**
     * Returns the type of catalog that was requested.
     *
     * @return the {@link CatalogType} associated with this request
     */
    public CatalogType getCatalogType() {
        return this.catalogType;
    }
}