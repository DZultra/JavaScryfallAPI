package net.dzultra.jsa.cards;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a collection of cards returned by the Scryfall API.
 * <p>
 * This object is typically used when retrieving multiple cards at once,
 * such as from collection-based endpoints or bulk card lookups.
 */
public record CardCollection(
        @SerializedName("object") String type,
        @SerializedName("data") Card[] cards
) {}