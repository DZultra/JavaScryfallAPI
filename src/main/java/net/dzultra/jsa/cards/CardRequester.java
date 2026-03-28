package net.dzultra.jsa.cards;

import com.google.gson.Gson;
import net.dzultra.jsa.DataTypeRecord;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.TypeRecord;
import net.dzultra.jsa.cards.enums.OrderMode;
import net.dzultra.jsa.cards.enums.SortMode;
import net.dzultra.jsa.cards.enums.UniqueMode;
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

    public CardSearchObject getCardsByQuery(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/search?q=" + encodedQuery);
        return CardSearchExecutor.executeCardSearch(this, this.client, this.gson, uri);
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
        return CardSearchExecutor.executeCardSearch(this, this.client, this.gson, uri);
    }

    // ---- Card By Name ----

    public Card getCardByName(@NotNull String name, @Nullable String set, boolean fuzzy) {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        String param = fuzzy ? "fuzzy" : "exact";
        URI uri = URI.create(this.client.getBaseUrl() + "/cards/named?" + param + "=" + encodedName + (set != null ? "&set=" + set : ""));
        return CardSearchExecutor.executeSingleCardSearch(this, this.client, this.gson, uri);
    }

    // ---- Helper Methods ----

    public boolean isValidResponseSingle(String response, String type) {
        TypeRecord record = gson.fromJson(response, TypeRecord.class);
        return record.type().equals(type);
    }

    public boolean isValidResponseDouble(String response, String type1, String type2) {
        DataTypeRecord record = gson.fromJson(response, DataTypeRecord.class);
        return record.type().equals(type1) && record.data()[0].type().equals(type2);
    }
}
