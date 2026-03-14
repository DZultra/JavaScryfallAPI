package net.dzultra.jsa.catalogs;

import com.google.gson.annotations.SerializedName;

public record CatalogResponse(
        @SerializedName("object") String type,
        int totalValues,
        String[] data
) {}
