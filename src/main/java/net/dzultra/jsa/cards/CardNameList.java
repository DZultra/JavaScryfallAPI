package net.dzultra.jsa.cards;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a list of card name suggestions returned by the Scryfall API.
 * <p>
 * This response is typically used for autocomplete endpoints and contains
 * a collection of matching card names based on a partial query.
 */
public record CardNameList(
        @SerializedName("object") String type,
        Integer total_values,
        @SerializedName("data") String[] card_names
) {}