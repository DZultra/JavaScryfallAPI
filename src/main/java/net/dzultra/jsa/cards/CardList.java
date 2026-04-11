package net.dzultra.jsa.cards;

import com.google.gson.annotations.SerializedName;

import java.net.URI;

public record CardList(
        @SerializedName("object") String type,
        Integer total_cards,
        Boolean has_more,
        URI next_page,
        @SerializedName("data") Card[] cards
) implements CardRecord {}
