package net.dzultra.jsa;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a generic Scryfall API response containing a typed object
 * and an array of nested {@link TypeRecord} elements.
 * <p>
 * This structure is commonly used for list-based API responses, where the
 * top-level object describes the response type and the {@code data} field
 * contains the actual payload entries.
 *
 * @param type the type of the returned object (typically {@code "list"} or similar)
 * @param data an array of {@link TypeRecord} elements representing the response data
 */
public record DataTypeRecord(
        @SerializedName("object") String type,
        TypeRecord[] data
) {}