package net.dzultra.jsa.cards;

import com.google.gson.annotations.SerializedName;

public record CardNameList(
        @SerializedName("object") String type,
        Integer total_values,
        @SerializedName("data") String[] card_names
) {}
