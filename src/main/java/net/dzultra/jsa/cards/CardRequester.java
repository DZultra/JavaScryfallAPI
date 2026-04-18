package net.dzultra.jsa.cards;

import com.google.gson.Gson;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.cards.enums.*;
import net.dzultra.jsa.sets.MTGSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * High-level service for querying Magic: The Gathering card data from the Scryfall API.
 * <p>
 * This class provides multiple convenience methods for retrieving cards by search query,
 * name lookup, set information, autocomplete suggestions, random cards, and identifiers.
 * It delegates request execution and response parsing to {@link CardSearchExecutor}.
 */
public class CardRequester {

    /**
     * Gson instance used for JSON deserialization.
     */
    protected Gson gson = new Gson();

    /**
     * The Scryfall client used for HTTP communication.
     */
    public final ScryfallClient client;

    /**
     * Constructs a new {@code CardRequester}.
     *
     * @param client the Scryfall client used for API requests
     */
    public CardRequester(ScryfallClient client) {
        this.client = client;
    }

    // ---- Cards By Query ----

    /**
     * Searches for cards using a Scryfall query string.
     *
     * @param query the search query (Scryfall syntax)
     * @return a {@link CardList} containing matching cards
     */
    public CardList getCardsByQuery(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/search?q=" + encodedQuery);
        return CardSearchExecutor.executeCardSearch(this.client, this.gson, uri);
    }

    /**
     * Searches for cards using a query string with additional search options.
     *
     * @param query       the search query (Scryfall syntax)
     * @param uniqueMode  optional uniqueness filtering mode
     * @param orderMode   optional ordering mode
     * @param sortMode    optional sort direction
     * @return a {@link CardList} containing matching cards
     */
    public CardList getCardsByQuery(
            String query,
            @Nullable UniqueMode uniqueMode,
            @Nullable OrderMode orderMode,
            @Nullable SortMode sortMode
    ) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        StringBuilder url = new StringBuilder(this.client.getBaseUrl() + "/cards/search?q=" + encodedQuery);

        if (uniqueMode != null) url.append("&unique=").append(uniqueMode.getValue());
        if (orderMode != null) url.append("&order=").append(orderMode.getValue());
        if (sortMode != null) url.append("&dir=").append(sortMode.getValue());

        URI uri = URI.create(url.toString());
        return CardSearchExecutor.executeCardSearch(this.client, this.gson, uri);
    }

    // ---- Card By Name ----

    /**
     * Retrieves a single card by name using exact or fuzzy matching.
     *
     * @param name  the card name
     * @param set   optional set code restriction; may be {@code null}
     * @param fuzzy whether to use fuzzy matching instead of exact matching
     * @return the matching {@link Card}
     */
    public Card getCardByName(@NotNull String name, @Nullable String set, boolean fuzzy) {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        String param = fuzzy ? "fuzzy" : "exact";

        URI uri = URI.create(
                this.client.getBaseUrl() +
                        "/cards/named?" + param + "=" + encodedName +
                        (set != null ? "&set=" + set : "")
        );

        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    /**
     * Retrieves a single card by name with optional set filtering.
     *
     * @param name  the card name
     * @param set   optional MTG set; may be {@code null}
     * @param fuzzy whether to use fuzzy matching instead of exact matching
     * @return the matching {@link Card}
     */
    public Card getCardByName(@NotNull String name, @Nullable MTGSet set, boolean fuzzy) {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        String param = fuzzy ? "fuzzy" : "exact";

        URI uri = URI.create(
                this.client.getBaseUrl() +
                        "/cards/named?" + param + "=" + encodedName +
                        (set != null && set.code() != null ? "&set=" + set : "")
        );

        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    // ---- Autocomplete ----

    /**
     * Retrieves card name suggestions based on a partial query.
     *
     * @param query partial card name input
     * @return a {@link CardNameList} containing matching suggestions
     */
    public CardNameList getCardNamesByQuery(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/autocomplete?q=" + encodedQuery);
        return CardSearchExecutor.executeCardNameListSearch(this.client, this.gson, uri);
    }

    /**
     * Retrieves card name suggestions with optional extra results included.
     *
     * @param query           partial card name input
     * @param includeExtras   whether to include additional non-standard results
     * @return a {@link CardNameList} containing matching suggestions
     */
    public CardNameList getCardNamesByQuery(String query, boolean includeExtras) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(
                this.client.getBaseUrl() +
                        "/cards/autocomplete?q=" + encodedQuery +
                        "&include_extras=" + includeExtras
        );

        return CardSearchExecutor.executeCardNameListSearch(this.client, this.gson, uri);
    }

    // ---- Random Card ----

    /**
     * Retrieves a completely random card.
     *
     * @return a random {@link Card}
     */
    public Card getRandomCard() {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/random");
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    /**
     * Retrieves a random card matching a search query.
     *
     * @param query optional search query filter (Scryfall syntax); may be {@code null} or empty for no filtering
     * @return a random matching {@link Card}
     */
    public Card getRandomCard(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/random?q=" + encodedQuery);
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    // ---- Card By Set & Number ----

    /**
     * Retrieves a card by set code and collector number.
     *
     * @param setCode         the set code
     * @param collectorNumber  the collector number within the set
     * @return the matching {@link Card}
     */
    public Card getCardBySetAndNumber(String setCode, String collectorNumber) {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/" + setCode + "/" + collectorNumber);
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    /**
     * Retrieves a localized card by set, collector number, and language.
     *
     * @param setCode         the set code
     * @param collectorNumber  the collector number
     * @param lang            the card language
     * @return the matching {@link Card}
     */
    public Card getCardBySetAndNumber(String setCode, String collectorNumber, Language lang) {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/" + setCode + "/" + collectorNumber + "/" + lang.getCode());
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    /**
     * Retrieves a card by set object and collector number.
     *
     * @param set              the MTG set
     * @param collectorNumber  the collector number
     * @return the matching {@link Card}
     */
    public Card getCardBySetAndNumber(MTGSet set, String collectorNumber) {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/" + set.code() + "/" + collectorNumber);
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    /**
     * Retrieves a localized card by set object, collector number, and language.
     *
     * @param set              the MTG set
     * @param collectorNumber  the collector number
     * @param lang            the card language
     * @return the matching {@link Card}
     */
    public Card getCardBySetAndNumber(MTGSet set, String collectorNumber, Language lang) {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/" + set.code() + "/" + collectorNumber + "/" + lang.getCode());
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    // ---- Card By ID ----

    /**
     * Retrieves a card by its unique identifier.
     *
     * @param id      the card identifier
     * @param idType  the type of identifier used
     * @return the matching {@link Card}
     */
    public Card getCardById(String id, CardIDType idType) {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/" + idType.getPathSegment() + id);
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }
}