package net.dzultra.jsa.sets;

import com.google.gson.annotations.SerializedName;

public record MTGSetList(
        @SerializedName("object") String type,
        boolean has_more,
        @SerializedName("data") MTGSet[] sets
) implements SetRecord {}
