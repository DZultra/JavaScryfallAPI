package net.dzultra.jsa.bulk_data;

import com.google.gson.Gson;
import net.dzultra.jsa.DataTypeRecord;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.TypeRecord;
import net.dzultra.jsa.exceptions.BulkDataResponseException;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

/**
 * Provides access to Scryfall bulk data endpoints.
 * <p>
 * Bulk data endpoints allow retrieval of large datasets such as card databases,
 * rulings, and other full-scale API resources. This class handles request execution,
 * response validation, and JSON deserialization for bulk data operations.
 */
public class BulkData {

    /**
     * The Scryfall client used for HTTP communication.
     */
    protected final ScryfallClient client;

    /**
     * Gson instance used for JSON deserialization.
     */
    protected Gson gson = new Gson();

    /**
     * Base endpoint for bulk data API requests.
     */
    public String endpoint = "/bulk-data/";

    /**
     * Stores the last raw JSON response received.
     */
    public String response;

    /**
     * Constructs a new {@code BulkData} handler.
     *
     * @param client the Scryfall client used for API communication
     */
    public BulkData(ScryfallClient client) {
        this.client = client;
    }

    /**
     * Sends an HTTP request and returns the raw response if valid.
     *
     * @param uri the target endpoint URI
     * @return the raw JSON response string
     * @throws BulkDataResponseException if the request fails or response is invalid
     */
    private String getResponse(URI uri) {
        try {
            this.response = this.client.httpClient
                    .send(this.client.requestBuilder.GET().uri(uri).build(),
                            HttpResponse.BodyHandlers.ofString())
                    .body();

            if (isValidBulkDataResponse(response)) {
                return response;
            } else {
                throw new BulkDataResponseException(this);
            }
        } catch (IOException | InterruptedException e) {
            throw new BulkDataResponseException(this);
        }
    }

    /**
     * Retrieves the full bulk data listing.
     *
     * @return a {@link BulkDataResponse} containing all bulk data entries
     */
    public BulkDataResponse getBulkData() {
        return gson.fromJson(this.getResponse(createURI("")), BulkDataResponse.class);
    }

    /**
     * Retrieves a specific bulk data object by type.
     *
     * @param type the bulk data type identifier
     * @return the corresponding {@link BulkDataResponse.BulkDataObjectRecord}
     */
    public BulkDataResponse.BulkDataObjectRecord getBulkData(BulkDataType type) {
        return gson.fromJson(
                this.getResponse(createURI(type.getTypeEndpoint())),
                BulkDataResponse.BulkDataObjectRecord.class
        );
    }

    /**
     * Retrieves a specific bulk data object by UUID.
     *
     * @param uuid the unique identifier of the bulk data object
     * @return the corresponding {@link BulkDataResponse.BulkDataObjectRecord}
     */
    public BulkDataResponse.BulkDataObjectRecord getBulkData(String uuid) {
        return gson.fromJson(
                this.getResponse(createURI(uuid)),
                BulkDataResponse.BulkDataObjectRecord.class
        );
    }

    /**
     * Validates whether the response is a valid bulk data response.
     *
     * @param response the raw JSON response string
     * @return {@code true} if the response matches expected bulk data formats
     */
    public boolean isValidBulkDataResponse(String response) {
        return (gson.fromJson(response, TypeRecord.class).type().equals("list")
                && gson.fromJson(response, DataTypeRecord.class).data()[0].type().equals("bulk_data"))
                || gson.fromJson(response, TypeRecord.class).type().equals("bulk_data");
    }

    /**
     * Builds a full URI for a bulk data endpoint.
     *
     * @param additionalEndpoint optional additional path segment
     * @return the constructed {@link URI}
     */
    private URI createURI(@Nullable String additionalEndpoint) {
        this.endpoint += additionalEndpoint;
        return URI.create(this.client.getBaseUrl() + this.endpoint);
    }
}