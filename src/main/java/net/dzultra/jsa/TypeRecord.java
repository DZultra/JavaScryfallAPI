package net.dzultra.jsa;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a minimal Scryfall API object containing only a type field.
 * <p>
 * This record is primarily used for lightweight validation of API responses,
 * where only the top-level {@code object} type needs to be inspected without
 * fully deserializing the complete response structure.
 *
 * @param type the type of the returned object (e.g., {@code "card"}, {@code "list"}, {@code "error"})
 */
public record TypeRecord(
        @SerializedName("object") String type
) {}