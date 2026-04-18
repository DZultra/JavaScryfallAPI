package net.dzultra.jsa.sets;

import com.google.gson.Gson;
import net.dzultra.jsa.ScryfallClient;

import java.net.URI;
import java.util.UUID;

/**
 * High-level API service for retrieving Magic: The Gathering set data.
 * <p>
 * This class provides simplified access to Scryfall set-related endpoints,
 * abstracting away request building and execution. It delegates HTTP calls
 * and response handling to {@link SetSearchExecutor}.
 * <p>
 * It acts as the main entry point for set-related operations in the JSA library.
 */
public class SetRequester {

    /**
     * Gson instance used for JSON serialization and deserialization.
     */
    protected Gson gson = new Gson();

    /**
     * The Scryfall client responsible for HTTP communication.
     */
    public final ScryfallClient client;

    /**
     * The executor responsible for performing API requests and parsing responses.
     */
    public final SetSearchExecutor executor;

    /**
     * Constructs a new {@code SetRequester}.
     * <p>
     * Initializes the internal {@link SetSearchExecutor} using the provided client
     * and a default {@link Gson} instance.
     *
     * @param client the Scryfall client used for API communication
     */
    public SetRequester(ScryfallClient client) {
        this.client = client;
        this.executor = new SetSearchExecutor(client, gson);
    }

    /**
     * Retrieves all available Magic: The Gathering sets.
     *
     * @return a {@link MTGSetList} containing all sets returned by the API
     */
    public MTGSetList getAllSets() {
        URI uri = URI.create(this.client.getBaseUrl() + "/sets/");
        return this.executor.executeSetListSearch(uri);
    }

    /**
     * Retrieves a Magic: The Gathering set by its set code.
     *
     * @param code the set code (e.g., {@code "neo"}, {@code "khm"})
     * @return the corresponding {@link MTGSet}
     */
    public MTGSet getSetByCode(String code) {
        URI uri = URI.create(this.client.getBaseUrl() + "/sets/" + code);
        return this.executor.executeSetSearch(uri);
    }

    /**
     * Retrieves a set using its TCGPlayer identifier.
     *
     * @param id the numeric TCGPlayer ID
     * @return the corresponding {@link MTGSet}
     */
    public MTGSet getSetByTCGPlayerID(int id) {
        URI uri = URI.create(this.client.getBaseUrl() + "/sets/tcgplayer/" + id);
        return this.executor.executeSetSearch(uri);
    }

    /**
     * Retrieves a set using its unique UUID.
     *
     * @param id the UUID of the set
     * @return the corresponding {@link MTGSet}
     */
    public MTGSet getSetByUUID(UUID id) {
        URI uri = URI.create(this.client.getBaseUrl() + "/sets/" + id);
        return this.executor.executeSetSearch(uri);
    }
}