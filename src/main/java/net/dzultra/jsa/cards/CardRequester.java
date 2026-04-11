package net.dzultra.jsa.cards;

import com.google.gson.Gson;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.cards.enums.Language;
import net.dzultra.jsa.cards.enums.OrderMode;
import net.dzultra.jsa.cards.enums.SortMode;
import net.dzultra.jsa.cards.enums.UniqueMode;
import net.dzultra.jsa.sets.MTGSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CardRequester {
    protected Gson gson = new Gson();
    public final ScryfallClient client;

    public CardRequester(ScryfallClient client) {
        this.client = client;
    }

    // ---- Cards By Query ----

    public CardList getCardsByQuery(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/search?q=" + encodedQuery);
        return CardSearchExecutor.executeCardSearch(this.client, this.gson, uri);
    }

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

    public Card getCardByName(@NotNull String name, @Nullable String set, boolean fuzzy) {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        String param = fuzzy ? "fuzzy" : "exact";
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/named?" + param + "=" + encodedName + (set != null ? "&set=" + set : ""));
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    public Card getCardByName(@NotNull String name, @Nullable MTGSet set, boolean fuzzy) {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        String param = fuzzy ? "fuzzy" : "exact";
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/named?" + param + "=" + encodedName + (set != null && set.code() != null ? "&set=" + set : ""));
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    // ---- Card Names By Query (Autocomplete) ----

    public CardNameList getCardNamesByQuery(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/autocomplete?q=" + encodedQuery);
        return CardSearchExecutor.executeCardNameListSearch(this.client, this.gson, uri);
    }

    public CardNameList getCardNamesByQuery(String query, boolean includeExtras) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/autocomplete?q=" + encodedQuery + "&include_extras=" + includeExtras);
        return CardSearchExecutor.executeCardNameListSearch(this.client, this.gson, uri);
    }

    // ---- Random Card By Optional Query ----

    public Card getRandomCard() {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/random");
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    public Card getRandomCard(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/random?q=" + encodedQuery);
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    // ---- Card Collection/List By Card Identifiers ----

    // Note: This endpoint is unfinished
//    public CardCollection getCardCollectionByIdentifiers() {
//        URI uri = URI.create(this.client.getBaseUrl() + "/cards/collection");
//        return CardSearchExecutor.executeCardCollectionSearch(this.client, this.gson, uri);
//    }

    // ---- Card By Set Code and Set Number ----

    public Card getCardBySetAndNumber(String setCode, String collectorNumber) {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/" + setCode + "/" + collectorNumber);
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    public Card getCardBySetAndNumber(String setCode, String collectorNumber, Language lang) {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/" + setCode + "/" + collectorNumber + "/" + lang.getCode());
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    public Card getCardBySetAndNumber(MTGSet set, String collectorNumber) {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/" + set.code() + "/" + collectorNumber);
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

    public Card getCardBySetAndNumber(MTGSet set, String collectorNumber, Language lang) {
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/" + set.code() + "/" + collectorNumber  + "/" + lang.getCode());
        return CardSearchExecutor.executeSingleCardSearch(this.client, this.gson, uri);
    }

}
