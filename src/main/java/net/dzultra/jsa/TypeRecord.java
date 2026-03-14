package net.dzultra.jsa;

import com.google.gson.annotations.SerializedName;

public record TypeRecord(
        @SerializedName("object") String type
) {}
