package net.dzultra.jsa.cards;

import com.google.gson.annotations.SerializedName;

import java.net.URI;

/**
 * Represents a paginated list of cards returned by the Scryfall API.
 * <p>
 * This response is typically returned from search endpoints and contains
 * metadata about pagination as well as the actual list of card objects.
 */
public record CardList(
        @SerializedName("object") String type,
        Integer total_cards,
        Boolean has_more,
        URI next_page,
        @SerializedName("data") Card[] cards
) implements CardRecord {}