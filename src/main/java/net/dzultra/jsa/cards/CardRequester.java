package net.dzultra.jsa.cards;

import com.google.gson.Gson;
import net.dzultra.jsa.DataTypeRecord;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.TypeRecord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class CardRequester {
    protected Gson gson = new Gson();
    public final ScryfallClient client;

    public CardRequester(ScryfallClient client) {
        this.client = client;
    }

    public CardSearchObject getCardsByQuery(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/search?q=" + encodedQuery);
        return executeCardSearch(uri);
    }

    public CardSearchObject getCardsByQuery(
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
        return executeCardSearch(uri);
    }

    private CardSearchObject executeCardSearch(URI uri) {
        String response;

        try {
            response = this.client.httpClient.send(
                    this.client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardSearchException(uri, null);
        }

        if (!isValidResponseDouble(response, "list", "card")) throw new CardSearchException(uri, response);

        try {
            return gson.fromJson(response, CardSearchObject.class);
        } catch (Exception e) {
            throw new CardSearchException(uri, response);
        }
    }

    public Card getCardByName(@NotNull String name, boolean fuzzy) {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        String param = fuzzy ? "fuzzy" : "exact";
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/named?" + param + "=" + encodedName);
        return executeSingleCard(uri);
    }

    private Card executeSingleCard(URI uri) {
        String response;

        try {
            response = this.client.httpClient.send(
                    this.client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardSearchException(uri, null);
        }

        if (!isValidResponseSingle(response, "card")) {
            throw new CardSearchException(uri, response);
        }

        try {
            return gson.fromJson(response, Card.class);
        } catch (Exception e) {
            System.out.println(e.getCause() + " | " + e.getMessage());
            throw new CardSearchException(uri, response);
        }
    }

    public boolean isValidResponseSingle(String response, String type) {
        TypeRecord record = gson.fromJson(response, TypeRecord.class);
        return record.type().equals(type);
    }

    public boolean isValidResponseDouble(String response, String type1, String type2) {
        DataTypeRecord record = gson.fromJson(response, DataTypeRecord.class);
        return record.type().equals(type1) && record.data()[0].type().equals(type2);
    }
}
