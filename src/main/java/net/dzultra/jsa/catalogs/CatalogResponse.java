package net.dzultra.jsa.catalogs;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a catalog response returned by the Scryfall API.
 * <p>
 * Catalog endpoints return a flat list of string values, such as card names,
 * keywords, or other predefined datasets. This structure wraps that list along
 * with metadata about the response.
 *
 * @param type         the type of the returned object (typically {@code "catalog"})
 * @param totalValues   the total number of values contained in the catalog
 * @param data          an array of string values contained in the catalog response
 */
public record CatalogResponse(
        @SerializedName("object") String type,
        int totalValues,
        String[] data
) {}