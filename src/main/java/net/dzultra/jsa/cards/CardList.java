package net.dzultra.jsa.cards;

import com.google.gson.annotations.SerializedName;

public record CardList(
        @SerializedName("object") String type,
        Integer total_cards,
        Boolean has_more,
        @SerializedName("data") Card[] cards
) implements CardRecord {}
