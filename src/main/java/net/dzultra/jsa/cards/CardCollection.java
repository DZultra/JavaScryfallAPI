package net.dzultra.jsa.cards;

import com.google.gson.annotations.SerializedName;

public record CardCollection(
        @SerializedName("object") String type,
        @SerializedName("data") Card[] cards
) {}
